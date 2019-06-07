package Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {
    ArrayList<Player> players = new ArrayList<Player>();
    Yut yut = new Yut();
    int turn;
    ArrayList<GamePiece> selectedPieces = new ArrayList<GamePiece>();
    GameBoard gameBoard = new GameBoard();
    Player winner = null;

    public GameBoard getGameBoard() { return this.gameBoard; }
    public int getTurn(){
        return turn;
    }
    public Phase getPhase(){
        return players.get(turn).phase;
    }
    public ArrayList<Node> getMovableNodes(){
        if(players.get(turn).phase != Phase.MOVE_PIECE_PHASE){
            return new ArrayList<Node>();
        }
        try {
            return gameBoard.movableNodes(selectedPieces.get(0).getNode(),players.get(turn).yutNums);
        }catch (Exception e){
            return new ArrayList<Node>();
        }
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Integer> getYutNums(){
        if(players.get(turn).yutNums.size() > 0)
            return  players.get(turn).yutNums;
        else
            return new ArrayList<Integer>();
    }

    public void createPlayer(int playerNum) {
        for(int i = 0; i < playerNum; i++) {
            players.add(new Player(i));
        }
    }

    public void createPiece (int pieceNum){
        for (Player player: this.players) {
            player.createPiece(pieceNum);
        }
    }

    public void init(int playerNum, int pieceNum){
        createPlayer(playerNum);
        createPiece(pieceNum);
        turn = 0;
        for(Player player: players){
            for(GamePiece piece : player.gamePieces){
                piece.setNode(gameBoard.nodes[0]);
            }
        }
    }
    public void start(){

        players.get(turn).startTurn();
        dataChanged();
    }

    /*윷 던지는 버튼이 클릭되었을 때*/
    public int randomYutClickEvent(){
        int select = yut.throwYut();
        selectYutClickEvent(select);
        return select;
    }

    public void selectYutClickEvent(int select){
        Player currentPlayer = players.get(turn);
        boolean cannotMoveFlag = true;
        if(currentPlayer.phase == Phase.THROW_YUT_PHASE && currentPlayer.throwCnt > 0) {
            int yutNum = select;
            currentPlayer.yutNums.add(yutNum);
            if (yutNum < 4) {
                currentPlayer.phase = Phase.CHOOSE_PIECE_PHASE;
                currentPlayer.throwCnt--;
            }
            if (yutNum == -1 && currentPlayer.yutNums.size() == 0) {
                for (GamePiece piece : currentPlayer.gamePieces) {
                    if (piece.getNode().nodeID != 0)
                        cannotMoveFlag = false;
                }
                if (cannotMoveFlag) {
                    changeTurn();
                }
            }
        }
        dataChanged();
    }
    /*아직 보드에 올라가지 않은 게임말이 클릭되었을 때*/
    public void pieceOutsideBoardClickEvent(GamePiece gamePiece){
        if(gamePiece.getNode() != gameBoard.nodes[0])
            return;
        Player currentPlayer = gamePiece.owner;
        if(currentPlayer.playerID == turn){
            if(currentPlayer.phase == Phase.CHOOSE_PIECE_PHASE) {
                selectedPieces = new ArrayList<GamePiece>();
                selectedPieces.add(gamePiece);
                currentPlayer.phase = Phase.MOVE_PIECE_PHASE;
            }
            /*선택을 취소하고 싶은 경우*/
            else if (currentPlayer.phase == Phase.MOVE_PIECE_PHASE && selectedPieces.get(0) == gamePiece){
                selectedPieces = null;
                currentPlayer.phase = Phase.CHOOSE_PIECE_PHASE;
            }
        }
        dataChanged();
    }


    /*게임판의 노드 1~29번째가 클릭되었을 때*/
    public void nodeClickEvent(Node node) {
        Player currentPlayer = players.get(turn);

        /*움직일 말을 고르는 단계일 때*/
        if(currentPlayer.phase == Phase.CHOOSE_PIECE_PHASE){
            /*빈 노드를 선택한 경우*/
            if(node.getGamePiecesOn() == null || node.getGamePiecesOn().size() < 1){
                return;
            }
            /*자기 말이 있는 노드를 선택한 경우*/
            else if(node.getGamePiecesOn().get(0).owner == currentPlayer) {
                selectedPieces = node.getGamePiecesOn();
                currentPlayer.phase = Phase.MOVE_PIECE_PHASE;
            }
            else {
                /*do nothing*/
                return;
            }
        }

        /*말이 움직이는 단계일 때*/
        else if(currentPlayer.phase == Phase.MOVE_PIECE_PHASE){
            /*이동할 수 있는 칸인 경우*/
            if(gameBoard.movableNodes(selectedPieces.get(0).getNode(),currentPlayer.yutNums).contains(node)){
                /*역으로 윷 숫자 계산*/
                int selectedYutNum = gameBoard.reCalculateYutNum(selectedPieces.get(0).getNode(), node);
                currentPlayer.yutNums.remove(currentPlayer.yutNums.indexOf(selectedYutNum));

                /*완주하는 경우*/
                if(node.nodeID == 30){
                    Node nodeBefore = selectedPieces.get(0).getNode();
                    for(GamePiece piece : selectedPieces){
                        piece.setNode(node);
                    }
                    nodeBefore.gamePiecesOn.clear();
                }

                /*그냥 이동하는 경우*/
                /*piece.move 했더니 concurrencyError 에러 발생함*/
                if(node.getGamePiecesOn() == null || node.getGamePiecesOn().size() < 1){
                    /*그냥 이동하는 경우*/
                    Node nodeBefore = selectedPieces.get(0).getNode();
                    for(GamePiece piece : selectedPieces){
                        node.getGamePiecesOn().add(piece);
                        piece.setNode(node);
                    }
                    nodeBefore.getGamePiecesOn().clear();
                }
                /*업는 경우*/
                else if(node.getGamePiecesOn().get(0).owner == currentPlayer){
                    Node nodeBefore = selectedPieces.get(0).getNode();
                    for(GamePiece piece : selectedPieces){
                        node.getGamePiecesOn().add(piece);
                        piece.setNode(node);
                    }
                    nodeBefore.getGamePiecesOn().clear();
                }
                /*상대의 말을 잡은 경우*/
                else if(node.getGamePiecesOn().get(0).owner != currentPlayer){
                    ArrayList<GamePiece> caughtPieces = node.getGamePiecesOn();
                    for(GamePiece caughtPiece : caughtPieces){
                        caughtPiece.setNode(gameBoard.nodes[0]);
                    }
                    node.getGamePiecesOn().clear();

                    Node nodeBefore = selectedPieces.get(0).getNode();
                    for(GamePiece piece : selectedPieces){
                        node.getGamePiecesOn().add(piece);
                        piece.setNode(node);
                    }
                    nodeBefore.getGamePiecesOn().clear();
                    currentPlayer.throwCnt++;
                }

                /*움직이고 나서 할 행동*/
                selectedPieces = null;
                if(currentPlayer.throwCnt == 0 && currentPlayer.yutNums.size() == 0){
                    if(currentPlayer.isWin()){
                        /*누군가 승리함*/
                        winner = currentPlayer;
                        return;
                    }
                    changeTurn();
                }
                else if (currentPlayer.throwCnt > 0 && currentPlayer.yutNums.size() == 0){
                    currentPlayer.phase = Phase.THROW_YUT_PHASE;
                }
                else if (currentPlayer.throwCnt == 0 && currentPlayer.yutNums.size() > 0){
                    currentPlayer.phase = Phase.CHOOSE_PIECE_PHASE;
                }
            }
            /*이동할 수 없는 칸인 경우 또는 자기 자신을 한번 더 클릭한 경우*/
            else {
                currentPlayer.phase = Phase.CHOOSE_PIECE_PHASE;
                selectedPieces = null;
            }

        }
       dataChanged();
    }

    public void dataChanged(){
        setChanged();
        notifyObservers();
    }
    private void changeTurn(){
        players.get(turn).endTurn();
        turn = (turn + 1) % players.size();
        players.get(turn).startTurn();
    }

    /*안씀*/
    void makeOneStep() {
        setChanged();
        notifyObservers();
    } // end of makeOneStep method
} // end of Model class










