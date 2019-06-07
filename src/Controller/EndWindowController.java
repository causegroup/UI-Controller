package Controller;


import Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
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
    @FXML private Button reGameButton;
    @FXML private Button quitButton;


    public void initialize(URL location, ResourceBundle resources) {
        reGameButton.setOnAction(event -> reGame(event));
        quitButton.setOnAction(event -> quit(event));
    }

    public void reGame(ActionEvent event) {
        try {
            Button tmp = (Button) event.getSource();
            Stage oldStage = (Stage) tmp.getScene().getWindow();
            oldStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/settingWindow.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void quit(ActionEvent event) {
        Platform.exit();
    }
}
