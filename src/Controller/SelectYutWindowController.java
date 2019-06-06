package Controller;

import Model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SelectYutWindowController implements Initializable {
    /*
    * Initialize fxml objects.
    * ImageView : Menu for select result.
    * Button : close the window when it is clicked.
    * yutNum : selected result.
    * */
    @FXML private Button selectButton;
    @FXML private ImageView backdoe;
    @FXML private ImageView doe;
    @FXML private ImageView gae;
    @FXML private ImageView girl;
    @FXML private ImageView yut;
    @FXML private ImageView mo;

    private int yutNum;
    private GameModel gameModel;

    public SelectYutWindowController (GameModel input) {
        this.gameModel = input;
    }

    public void initialize(URL location, ResourceBundle resources) {
       setDefaultOpacity();
        backdoe.setOnMouseClicked(event -> setYutNum(-1, event));
        doe.setOnMouseClicked(event -> setYutNum(1, event));
        gae.setOnMouseClicked(event -> setYutNum(2, event));
        girl.setOnMouseClicked(event -> setYutNum(3, event));
        yut.setOnMouseClicked(event -> setYutNum(4, event));
        mo.setOnMouseClicked(event -> setYutNum(5, event));
        selectButton.setOnAction(event -> closeWindow(event));
    }

    public int getYutNum() {
        return this.yutNum;
    }
    public void setYutNum(int num, MouseEvent event) {
        // show selected result thicker. set this.yutNum to selected result.
        ImageView tmpImageView = (ImageView) event.getSource();
        switch(tmpImageView.getId()) {
            case "backdoe":
                setDefaultOpacity();
                backdoe.setOpacity(1.0);
                break;
            case "doe":
                setDefaultOpacity();
                doe.setOpacity(1.0);
                break;
            case "gae":
                setDefaultOpacity();
                gae.setOpacity(1.0);
                break;
            case "girl":
                setDefaultOpacity();
                girl.setOpacity(1.0);
                break;
            case "yut":
                setDefaultOpacity();
                yut.setOpacity(1.0);
                break;
            case "mo":
                setDefaultOpacity();
                mo.setOpacity(1.0);
                break;

        }
        this.yutNum = num;

        //System.out.println(this.yutNum);
    }
    public void closeWindow(ActionEvent event) {
        // close SelectYutWindow.
        gameModel.selectYutClickEvent(this.yutNum);
        Button tmpButton = (Button) event.getSource();
        Stage oldStage = (Stage) tmpButton.getScene().getWindow();
        oldStage.close();
    }
    public void setDefaultOpacity() {
        // each menu is not selected yet.
        this.backdoe.setOpacity(0.5);
        this.doe.setOpacity(0.5);
        this.gae.setOpacity(0.5);
        this.girl.setOpacity(0.5);
        this.yut.setOpacity(0.5);
        this.mo.setOpacity(0.5);
    }
}
