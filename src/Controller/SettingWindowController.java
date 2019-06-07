package Controller;

import Model.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingWindowController implements Initializable {
    /*
    * Initialize fxml objects.
    * startButton : show MainWindow when it is clicked.
    * TextField : get input number.
    * */
    @FXML private Button startButton;
    @FXML private TextField userInput;
    @FXML private TextField pieceInput;
    @FXML private Label fail;

    private int userNum;
    private int pieceNum;


    public void initialize(URL location, ResourceBundle resources) {
        startButton.setOnAction(event -> startGame(userInput.getText(), pieceInput.getText(), event));
    }

    public void startGame(String userInput, String pieceInput, ActionEvent event) {

        saveNum(userInput, pieceInput);

        if(userInputResult() && pieceInputResult()) {
            Button tmpButton = (Button) event.getSource();
            Stage oldStage = (Stage) tmpButton.getScene().getWindow();
            oldStage.close();
            showMainWindow();
        }
        else {
            fail.setText("잘못된 입력입니다. 다시 입력해주세요.");
        }



    }
    public void showMainWindow() {
        try {
            MainWindowController mainWindowController = new MainWindowController();
            mainWindowController.setUserNum(userNum);
            mainWindowController.setPieceNum(pieceNum);
            mainWindowController.setObservable();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/mainWindow.fxml"));
            loader.setController(mainWindowController);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("윷놀이 게임 (" + this.userNum + "명, " + this.pieceNum + "개의 말)");

            newStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveNum(String userInput, String pieceInput) {
        try {
            this.userNum = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            this.userNum = -1;
        }
        try {
            this.pieceNum = Integer.parseInt(pieceInput);
        } catch (NumberFormatException e) {
            this.pieceNum = -1;
        }


    }
    public boolean userInputResult() {
        if(this.userNum > 1 && this.userNum < 5)
            return true;
        else
            return false;
    }

    public boolean pieceInputResult() {
        if(this.pieceNum > 1 && this.pieceNum < 6)
            return true;
        else
            return false;
    }
}
