package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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

    public void initialize(URL location, ResourceBundle resources) {
        startButton.setOnAction(event -> startGame(userInput.getText(), pieceInput.getText(), event));
    }

    public void startGame(String userInput, String pieceInput, ActionEvent event) {
        int userNum = Integer.parseInt(userInput);
        int pieceNum = Integer.parseInt(pieceInput);


        Button tmpButton = (Button) event.getSource();
        Stage oldStage = (Stage) tmpButton.getScene().getWindow();
        oldStage.close();
        showMainWindow(userNum, pieceNum);

    }
    public void showMainWindow(int userInput, int pieceInput) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/mainWindow.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("윷놀이 게임 (" + userInput + "명, " + pieceInput + "개의 말)");
            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setUserNum(userInput);
            mainWindowController.setPieceNum(pieceInput);

            newStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
