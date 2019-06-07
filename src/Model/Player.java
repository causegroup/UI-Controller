package Model;

import java.util.ArrayList;

public class Player{
    Phase phase;
    int playerID;
    ArrayList<Integer> yutNums = new ArrayList<Integer>();
    ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
    int throwCnt;

    Player(int playerID){
        this.playerID = playerID;
        throwCnt = 0;
    }
    public int getPlayerID() { return this.playerID; }
    public void createPiece(int cnt){
        for(int pieceID = 0; pieceID < cnt; pieceID++) {
            gamePieces.add(new GamePiece(this, pieceID));
        }
    }

    public GamePiece getGamePieceById(int pieceID) {
        return gamePieces.get(pieceID);
    }

    public void startTurn(){
        phase = Phase.THROW_YUT_PHASE;
        throwCnt = 1;
    }
    public void endTurn(){
        yutNums.clear();
    }

    public boolean isWin(){
        for(GamePiece gamePiece : gamePieces){
            if(gamePiece.getNode().nodeID != 30)
                return false;
        }
        return true;
    }

}