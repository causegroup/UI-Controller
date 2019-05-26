package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class MainWindowController implements Initializable {
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

    @FXML private Circle one;
    @FXML private Circle two;
    @FXML private Circle three;
    @FXML private Circle four;
    @FXML private Circle five;
    @FXML private Circle six;
    @FXML private Circle seven;
    @FXML private Circle eight;
    @FXML private Circle nine;
    @FXML private Circle ten;
    @FXML private Circle eleven;
    @FXML private Circle twelve;
    @FXML private Circle thirteen;
    @FXML private Circle fourteen;
    @FXML private Circle fifteen;
    @FXML private Circle sixteen;
    @FXML private Circle seventeen;
    @FXML private Circle eighteen;
    @FXML private Circle nineteen;
    @FXML private Circle twenty;
    @FXML private Circle twentyOne;
    @FXML private Circle twentyTwo;
    @FXML private Circle twentyThree;
    @FXML private Circle twentyFour;
    @FXML private Circle twentyFive;
    @FXML private Circle twentySix;
    @FXML private Circle twentySeven;
    @FXML private Circle twentyEight;
    @FXML private Circle twentyNine;
    @FXML private Circle[] circles = new Circle[29];

    /*
    * userNum : # of players.
    * pieceNum : # of gamepieces that a player have.
    * yutNum : thrown result in this turn.
    * */
    private int userNum;
    private int pieceNum;
    private int yutNum;

    public void initialize(URL location, ResourceBundle resources) {
        // initialize MainWindow.

        setCircles();

        setGameBoard();

        randomButton.setOnAction(event -> randomResult(new Random().nextInt(6)+1));
        selectButton.setOnAction(event -> selectResult());
        Image i = new Image("resources/images/player1.png");
        setResult("resources/images/default.jpg");
        //Testcase for pointer of circles[index] and change circle image.
        circles[1].setFill(new ImagePattern(i));
        circles[23].setFill(new ImagePattern(i));
        circles[15].setFill(new ImagePattern(i));
        circles[28].setFill(new ImagePattern(i));
    }

    public void randomResult(int yutNum) {
       // set this.yutNum, call setResult.
        this.yutNum = yutNum;
        System.out.println(yutNum);
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
            case 6:
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/selectYutWindow.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene((Pane)loader.load()));
            newStage.setTitle("선택하여 윷 던지기");
            newStage.show();

        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public int getUserNum() {
        return this.userNum;
    }
    public int getPieceNum() {
        return this.pieceNum;
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
        this.circles[tmpNum] = one; tmpNum++;
        this.circles[tmpNum] = two; tmpNum++;
        this.circles[tmpNum] = three; tmpNum++;
        this.circles[tmpNum] = four; tmpNum++;
        this.circles[tmpNum] = five; tmpNum++;
        this.circles[tmpNum] = six; tmpNum++;
        this.circles[tmpNum] = seven; tmpNum++;
        this.circles[tmpNum] = eight; tmpNum++;
        this.circles[tmpNum] = nine; tmpNum++;
        this.circles[tmpNum] = ten; tmpNum++;
        this.circles[tmpNum] = eleven; tmpNum++;
        this.circles[tmpNum] = twelve; tmpNum++;
        this.circles[tmpNum] = thirteen; tmpNum++;
        this.circles[tmpNum] = fourteen; tmpNum++;
        this.circles[tmpNum] = fifteen; tmpNum++;
        this.circles[tmpNum] = sixteen; tmpNum++;
        this.circles[tmpNum] = seventeen; tmpNum++;
        this.circles[tmpNum] = eighteen; tmpNum++;
        this.circles[tmpNum] = nineteen; tmpNum++;
        this.circles[tmpNum] = twenty; tmpNum++;
        this.circles[tmpNum] = twentyOne; tmpNum++;
        this.circles[tmpNum] = twentyTwo; tmpNum++;
        this.circles[tmpNum] = twentyThree; tmpNum++;
        this.circles[tmpNum] = twentyFour; tmpNum++;
        this.circles[tmpNum] = twentyFive; tmpNum++;
        this.circles[tmpNum] = twentySix; tmpNum++;
        this.circles[tmpNum] = twentySeven; tmpNum++;
        this.circles[tmpNum] = twentyEight; tmpNum++;
        this.circles[tmpNum] = twentyNine;
    }

    public void setGameBoard() {
        // Should initialize circles[] state : # of gamepieces, who has that gamepiece.

    }
}
