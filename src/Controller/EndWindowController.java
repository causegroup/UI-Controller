package Controller;


import Model.*;
import javafx.application.Platform;
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

public class EndWindowController implements Initializable {
    @FXML private Label winnerLabel;
    @FXML private Button reGameButton;
    @FXML private Button quitButtion;

    private String winnerMsg;

    public EndWindowController(String input) {
        this.winnerMsg = input;
    }

    public void initialize(URL location, ResourceBundle resources) {
        winnerLabel.setText(winnerMsg);
        reGameButton.setOnAction(event -> reGame());
        quitButtion.setOnAction(event -> quit());
    }
    public void reGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/settingWindow.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void quit() {
        Platform.exit();
    }
}
