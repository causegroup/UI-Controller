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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML private Circle[] circles = new Circle[29];


    /*
    * userNum : # of players.
    * pieceNum : # of gamepieces that a player have.
    * yutNum : thrown result in this turn.
    * */
    private int userNum;
    private int pieceNum;
    private int yutNum;

    GameModel gameModel = new GameModel();

    Observable observable;

    int turn;
    GameBoard gameBoard = new GameBoard();
    Phase phase;
    ArrayList<Integer> yutNums = new ArrayList<Integer>();

    public void initialize(URL location, ResourceBundle resources) {
        // initialize MainWindow.
        gameModel.init(userNum, pieceNum);
        gameModel.start();


        setTurn();
        setPlayers();
        setCircles();
        setYutResult();

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
            setTurn();
            setYutResult();
        }
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
        System.out.println(this.getYutNum());
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
        this.circles[tmpNum] = c29;
    }

    public void setPlayers() {

        for(int i = 0; i < this.userNum; i++) {
            for(int j =0; j < this.pieceNum; j++) {
                ImageView tmp = new ImageView();
                Image tmpImg;
                tmp.setFitHeight(100);
                tmp.setFitWidth(69.6);
                switch(i) {
                    case 0:
                        tmpImg = new Image("resources/images/player1.png");
                        tmp.setImage(tmpImg);
                        player1.getChildren().addAll(tmp);
                        p1.setText("Player 1");
                        break;
                    case 1:
                        tmpImg = new Image("resources/images/player2.png");
                        tmp.setImage(tmpImg);
                        player2.getChildren().addAll(tmp);
                        p2.setText("Player 2");
                        break;
                    case 2:
                        tmpImg = new Image("resources/images/player3.png");
                        tmp.setImage(tmpImg);
                        player3.getChildren().addAll(tmp);
                        p3.setText("Player 3");
                        break;
                    case 3:
                        tmpImg = new Image("resources/images/player4.png");
                        tmp.setImage(tmpImg);
                        player4.getChildren().addAll(tmp);
                        p4.setText("Player 4");
                        break;
                }
            }
        }
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

    }
}
