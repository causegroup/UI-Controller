package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        start : show SettingWindow
        SettingWindow : get two input number. # of players and # of gamepieces that a player have.
        */
        Parent root = FXMLLoader.load(getClass().getResource("/resources/View/settingWindow.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Game Setting - 유저 수 / 말 수");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
