package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sun.net.www.content.image.png;


public class MainWindowController implements Initializable, Observer {
    /*
    Initialize fxml objects
    * Button : random -> random result / select -> able to select result
    * Circle : object for show node.
    * Each circle should be coupled with a node that is corresponding to each position.
    * Circle[] circles : array for manage the whole circles.
    * */
    @FXML private Button randomButton;
    @FXML private Button selectButton;
    @FXML private ImageView result;

    @FXML private Circle c1;
    @FXML private Circle c2;
    @FXML private Circle c3;
    @FXML private Circle c4;
    @FXML private Circle c5;
    @FXML private Circle c6;
    @FXML private Circle c7;
    @FXML private Circle c8;
    @FXML private Circle c9;
    @FXML private Circle c10;
    @FXML private Circle c11;
    @FXML private Circle c12;
    @FXML private Circle c13;
    @FXML private Circle c14;
    @FXML private Circle c15;
    @FXML private Circle c16;
    @FXML private Circle c17;
    @FXML private Circle c18;
    @FXML private Circle c19;
    @FXML private Circle c20;
    @FXML private Circle c21;
    @FXML private Circle c22;
    @FXML private Circle c23;
    @FXML private Circle c24;
    @FXML private Circle c25;
    @FXML private Circle c26;
    @FXML private Circle c27;
    @FXML private Circle c28;
    @FXML private Circle c29;
    @FXML private Circle c30;

    @FXML private HBox player1;
    @FXML private HBox player2;
    @FXML private HBox player3;
    @FXML private HBox player4;

    @FXML private Label p1;
    @FXML private Label p2;
    @FXML private Label p3;
    @FXML private Label p4;
    @FXML private Label showTurn;
    @FXML private Label yutResult;

    @FXML private Circle[] circles = new Circle[30];
    @FXML private HBox[] hBoxes = new HBox[4];
    @FXML private Label[] playerLabels = new Label[4];
    @FXML private ImageView[][] node0 = new ImageView[4][5];

    /*
    * userNum : # of players.
    * pieceNum : # of gamepieces that a player have.
    * yutNum : thrown result in this turn.
    * */
    private int userNum;
    private int pieceNum;
    private int yutNum;
    private boolean initialization;

    GameModel gameModel = new GameModel();

    Observable observable;

    int turn;
    GameBoard gameBoard = new GameBoard();
    Phase phase;
    ArrayList<Integer> yutNums = new ArrayList<Integer>();
    ArrayList<Player> players = new ArrayList<Player>();
    String[][] pieceUrls = new String[4][5];


    public void initialize(URL location, ResourceBundle resources) {
        // initialize MainWindow.
        this.initialization = true;
        setPlayerLabels();
        setHBoxes();
        setCircles();
        setPieceUrls();

        gameModel.init(userNum, pieceNum);
        gameModel.start();


        randomButton.setOnAction(event -> randomResult());
        selectButton.setOnAction(event -> selectResult());

        setResult("resources/images/default.jpg");
        //Testcase for pointer of circles[index] and change circle image.
    }

    public void update(Observable obs, Object arg) {
        if(obs instanceof GameModel) {
            GameModel tmpGameModel = (GameModel) obs;
            this.gameBoard = tmpGameModel.getGameBoard();
            this.turn = tmpGameModel.getTurn() + 1;
            this.phase = tmpGameModel.getPhase();
            this.yutNums = tmpGameModel.getYutNums();
            this.players = tmpGameModel.getPlayers();

            setTurn();
            setYutResult();
            setPlayers();
            setGameBoard();
        }
    }
    public void setPlayerLabels() {
        playerLabels[0] = p1;
        playerLabels[1] = p2;
        playerLabels[2] = p3;
        playerLabels[3] = p4;
    }
    public void setHBoxes() {
        hBoxes[0] = player1;
        hBoxes[1] = player2;
        hBoxes[2] = player3;
        hBoxes[3] = player4;
    }
    public void setPieceUrls() {
        pieceUrls[0] = new String[] {"resources/images/player1.png", "resources/images/player1-2.png", "resources/images/player1-3.png", "resources/images/player1-4.png", "resources/images/player1-5.png"};
        pieceUrls[1] = new String[] {"resources/images/player2.png", "resources/images/player2-2.png", "resources/images/player2-3.png", "resources/images/player2-4.png", "resources/images/player2-5.png"};
        pieceUrls[2] = new String[] {"resources/images/player3.png", "resources/images/player3-2.png", "resources/images/player3-3.png", "resources/images/player3-4.png", "resources/images/player3-5.png"};
        pieceUrls[3] = new String[] {"resources/images/player4.png", "resources/images/player4-2.png", "resources/images/player4-3.png", "resources/images/player4-4.png", "resources/images/player4-5.png"};
    }


    public void randomResult() {
       // set this.yutNum, call setResult.

        this.yutNum = gameModel.randomYutClickEvent();

        switch(this.yutNum){
            case 1:
                setResult("resources/images/doe.jpg");
                break;
            case 2:
                setResult("resources/images/gae.jpg");
                break;
            case 3:
                setResult("resources/images/girl.jpg");
                break;
            case 4:
                setResult("resources/images/yut.jpg");
                break;
            case 5:
                setResult("resources/images/mo.jpg");
                break;
            case -1:
                setResult("resources/images/backdoe.jpg");
                break;
        }

    }
    public void setResult(String result) {
        // change thrown result image.
        Image image = new Image(result);
        this.result.setImage(image);

    }

    public void selectResult() {
        // show new window : SelectYutWindow
        try {
            SelectYutWindowController selectYutWindowController = new SelectYutWindowController(this.gameModel);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/selectYutWindow.fxml"));
            loader.setController(selectYutWindowController);
            Stage newStage = new Stage();
            newStage.setScene(new Scene((Pane)loader.load()));
            newStage.setTitle("선택하여 윷 던지기");
            newStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void setObservable() {
        this.observable = this.gameModel;
        observable.addObserver(this);
    }
    public int getYutNum() {
        return this.yutNum;
    }
    public void setUserNum(int inputNum) {
        this.userNum = inputNum;
    }
    public void setPieceNum(int inputNum) {
        this.pieceNum = inputNum;
    }
    public void setYutNum(int inputNum) {
        this.yutNum = inputNum;
    }

    public void setCircles() {
        // initialize circles[]
        int tmpNum = 0;
        this.circles[tmpNum] = c1; tmpNum++;
        this.circles[tmpNum] = c2; tmpNum++;
        this.circles[tmpNum] = c3; tmpNum++;
        this.circles[tmpNum] = c4; tmpNum++;
        this.circles[tmpNum] = c5; tmpNum++;
        this.circles[tmpNum] = c6; tmpNum++;
        this.circles[tmpNum] = c7; tmpNum++;
        this.circles[tmpNum] = c8; tmpNum++;
        this.circles[tmpNum] = c9; tmpNum++;
        this.circles[tmpNum] = c10; tmpNum++;
        this.circles[tmpNum] = c11; tmpNum++;
        this.circles[tmpNum] = c12; tmpNum++;
        this.circles[tmpNum] = c13; tmpNum++;
        this.circles[tmpNum] = c14; tmpNum++;
        this.circles[tmpNum] = c15; tmpNum++;
        this.circles[tmpNum] = c16; tmpNum++;
        this.circles[tmpNum] = c17; tmpNum++;
        this.circles[tmpNum] = c18; tmpNum++;
        this.circles[tmpNum] = c19; tmpNum++;
        this.circles[tmpNum] = c20; tmpNum++;
        this.circles[tmpNum] = c21; tmpNum++;
        this.circles[tmpNum] = c22; tmpNum++;
        this.circles[tmpNum] = c23; tmpNum++;
        this.circles[tmpNum] = c24; tmpNum++;
        this.circles[tmpNum] = c25; tmpNum++;
        this.circles[tmpNum] = c26; tmpNum++;
        this.circles[tmpNum] = c27; tmpNum++;
        this.circles[tmpNum] = c28; tmpNum++;
        this.circles[tmpNum] = c29; tmpNum++;
        this.circles[tmpNum] = c30;
    }

    public void setPlayers() {
        for(int i = 0; i < this.userNum; i++) {
            for(int j =0; j < this.pieceNum; j++) {
                ImageView tmp = new ImageView();
                Image tmpImg;
                tmpImg = new Image(pieceUrls[i][0]);
                tmp.setImage(tmpImg);
                tmp.setFitHeight(100);
                tmp.setFitWidth(69.6);
                node0[i][j] = tmp;


                Node tmpNode = players.get(i).getGamePieceById(j).getNode();

                if(!initialization) {
                    hBoxes[i].getChildren().remove(j);
                }
                if(tmpNode.nodeID == 30) {
                    //do nothing
                }
                else if(tmpNode.nodeID == 0) {
                    hBoxes[i].getChildren().add(j, tmp);
                }
                else {
                    tmp.setOpacity(0.5);
                    hBoxes[i].getChildren().add(j, tmp);
                }
            }

            playerLabels[i].setText("Player " + (i+1));
        }
        this.initialization = false;
    }
    public void setTurn() {
        this.showTurn.setText("플레이어" + this.turn + "의 턴입니다.\n"
                + "현재 페이즈 : " + this.phase);
    }
    public void setYutResult() {
        this.yutResult.setText("결과 : " + this.yutNums);

    }
    public void setGameBoard() {
        // Should initialize circles[] state : # of gamepieces, who has that gamepiece.
        switch(this.phase) {
            case THROW_YUT_PHASE:
                showNodes();
                break;
            case CHOOSE_PIECE_PHASE:
                showNodes();
                showPieces();
                break;
            case MOVE_PIECE_PHASE:
                showNodes();
                showMovableNodes();
                break;
        }
    }

    public void showNodes() {
        for(int i = 1; i < 30; i++) {
            try {
                int tmpPlayerID = gameBoard.nodes[i].gamePiecesOn.get(0).owner.getPlayerID();
                int tmpPieceNum = gameBoard.nodes[i].gamePiecesOn.size();
                if(tmpPieceNum > 0){
                    Image tmp = new Image(pieceUrls[tmpPlayerID][tmpPieceNum-1]);
                    circles[i - 1].setFill(new ImagePattern(tmp));
                }
                else{
                    System.out.println("nodeID: " + i);
                }
            } catch(IndexOutOfBoundsException e) {
                circles[i-1].setFill(null);
                //System.out.println("exception: nodeID: " + i);
            }
        }
    }
    public void showPieces() {
        for(int i = 0; i < pieceNum; i++) {
            GamePiece tmp = players.get(this.turn - 1).getGamePieceById(i);
            if (tmp.getNode().nodeID == 0) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.5);
                hBoxes[turn-1].setEffect(colorAdjust);
                hBoxes[turn-1].setOnMouseClicked(event -> cleanOutside(tmp));
            } else if (tmp.getNode().nodeID > 29) {
                //do nothing
            } else {
                circles[tmp.getNode().nodeID-1].setStroke(Color.BLUE);
                circles[tmp.getNode().nodeID-1].setOnMouseClicked(event -> cleanCircle(tmp.getNode()));
            }
        }
    }
    public void cleanOutside(GamePiece input) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        hBoxes[turn-1].setOnMouseClicked(null);
        hBoxes[turn-1].setEffect(colorAdjust);

        gameModel.pieceOutsideBoardClickEvent(input);
    }
    public void cleanCircle(Node input) {
        for(int i = 0; i < 30; i++) {
            circles[i].setOnMouseClicked(null);
            circles[i].setStroke(Color.BLACK);
        }
        gameModel.nodeClickEvent(input);
    }

    public void showMovableNodes() {
        try {
            ArrayList<Node> movable = gameModel.getMovableNodes();
            for(int j=0; j<movable.size(); j++) {
                if(movable.get(j).nodeID == 0) {
                    System.out.println("error");
                }
                else {
                    Node tmp = gameBoard.nodes[movable.get(j).nodeID];
                    circles[movable.get(j).nodeID - 1].setStroke(Color.RED);
                    circles[movable.get(j).nodeID - 1].setOnMouseClicked(event -> cleanCircle(tmp));
                }

            }

        } catch (IndexOutOfBoundsException e) {
            //do nothing
        }


    }

}
