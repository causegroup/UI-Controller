package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import Controller. *;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class testFX {
    @Before
    public void setUp () throws Exception {
    }

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param primaryStage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage primaryStage) throws IOException, TimeoutException {

        Parent root = FXMLLoader.load(Main.class.getClass().getResource("/resources/View/settingWindow.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Game Setting - 유저 수 / 말 수");
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.toFront();
    }



    @Test
    void can_execute_win_result(FxRobot robot) {
        int numOfUser = 2;
        int numOfPiece = 2;

        // 유저의 수를 입력합니다.
        TextField userinput = robot.lookup("#userInput").queryAs(TextField.class);
        assertNotNull(userinput);
        robot.doubleClickOn(userinput);
        robot.write(Integer.toString(numOfUser));

        // 게임말의 수를 입력합니다.
        TextField pieceinput = robot.lookup("#pieceInput").queryAs(TextField.class);
        robot.doubleClickOn(pieceinput);
        robot.write(Integer.toString(numOfPiece));
        // 게임시작버튼 클릭.
        robot.clickOn("#startButton");

        // 인덱스와 유저의 ID를 일치시키기위해 배열 + 1
        HBox[] player = new HBox[numOfUser + 1];

        // player HBOX를 가져온다
        player[1] = robot.lookup("#player1").queryAs(HBox.class);
        player[2] = robot.lookup("#player2").queryAs(HBox.class);


        // 플레이어의 피스를 선언
        Node[][] piece = new Node[numOfUser+1][numOfPiece+1];
        for (int user = 1; user <= numOfUser; user++) {
            for (Node node: player[user].getChildren()) {
                piece[user][player[user].getChildren().indexOf(node)+1] = node;
            }
        }


        robot.clickOn("#selectButton");
        robot.clickOn("#mo");
        robot.clickOn("#yutSelectButton");

        robot.clickOn("#selectButton");
        robot.clickOn("#mo");
        robot.clickOn("#yutSelectButton");

        robot.clickOn("#selectButton");
        robot.clickOn("#mo");
        robot.clickOn("#yutSelectButton");

        robot.clickOn("#selectButton");
        robot.clickOn("#girl");
        robot.clickOn("#yutSelectButton");

        //첫번째 피스 이동
        // 플레이어의 피스를 선언
        for (int user = 1; user <= numOfUser; user++) {
            for (Node node: player[user].getChildren()) {
                piece[user][player[user].getChildren().indexOf(node)+1] = node;
            }
        }
        robot.clickOn(piece[1][1]);
        robot.clickOn("#c5");

        //두번째 피스 이동해서 첫번째에 업힌다.
        // 플레이어의 피스를 선언
        for (int user = 1; user <= numOfUser; user++) {
            for (Node node: player[user].getChildren()) {
                piece[user][player[user].getChildren().indexOf(node)+1] = node;
            }
        }
        robot.clickOn(piece[1][2]);
        robot.clickOn("#c5");

        //엎힌말 중앙으로 이동
        robot.clickOn("#c5");
        robot.clickOn("#c25");

        //중앙에서 도착으로 이동
        robot.clickOn("#c25");
        robot.clickOn("#c30");

    }
    /*
    @Test
    void can_catch_the_two_piece(FxRobot robot) {
        int numOfUser = 2;
        int numOfPiece = 2;

        // 유저의 수를 입력합니다.
        TextField userinput = robot.lookup("#userInput").queryAs(TextField.class);
        assertNotNull(userinput);
        robot.doubleClickOn(userinput);
        robot.write(Integer.toString(numOfUser));

        // 게임말의 수를 입력합니다.
        TextField pieceinput = robot.lookup("#pieceInput").queryAs(TextField.class);
        robot.doubleClickOn(pieceinput);
        robot.write(Integer.toString(numOfPiece));
        // 게임시작버튼 클릭.
        robot.clickOn("#startButton");

        // 인덱스와 유저의 ID를 일치시키기위해 배열 + 1
        HBox[] player = new HBox[numOfUser + 1];

        // player HBOX를 가져온다
        player[1] = robot.lookup("#player1").queryAs(HBox.class);
        player[2] = robot.lookup("#player2").queryAs(HBox.class);


        // 플레이어의 피스를 선언
        Node[][] piece = new Node[numOfUser+1][numOfPiece+1];
        for (int user = 1; user <= numOfUser; user++) {
            for (Node node: player[user].getChildren()) {
                piece[user][player[user].getChildren().indexOf(node)+1] = node;
            }
        }

        //첫번째피스이동
        robot.clickOn("#selectButton");
        robot.clickOn("#girl");
        robot.clickOn(piece[1][1]);
        robot.clickOn("#c3");

        //두번째피스이동
        robot.clickOn("#selectButton");
        robot.clickOn("#gae");
        robot.clickOn(piece[2][1]);
        robot.clickOn("#c2");

        //세번째피스를 첫번째에 업는다.
        robot.clickOn("#selectButton");
        robot.clickOn("#girl");
        robot.clickOn(piece[1][2]);
        robot.clickOn("#c3");

        //두번째로 엎은 말을 캐치
        robot.clickOn("#selectButton");
        robot.clickOn("#doe");
        robot.clickOn("#c2");
        robot.clickOn("#c3");

    }

    @Test
    void can_back_do_moving(FxRobot robot) {
        int numOfUser = 2;
        int numOfPiece = 2;

        // 유저의 수를 입력합니다.
        TextField userinput = robot.lookup("#userInput").queryAs(TextField.class);
        assertNotNull(userinput);
        robot.doubleClickOn(userinput);
        robot.write(Integer.toString(numOfUser));

        // 게임말의 수를 입력합니다.
        TextField pieceinput = robot.lookup("#pieceInput").queryAs(TextField.class);
        robot.doubleClickOn(pieceinput);
        robot.write(Integer.toString(numOfPiece));
        // 게임시작버튼 클릭.
        robot.clickOn("#startButton");

        // 인덱스와 유저의 ID를 일치시키기위해 배열 + 1
        HBox[] player = new HBox[numOfUser + 1];

        // player HBOX를 가져온다
        player[1] = robot.lookup("#player1").queryAs(HBox.class);
        player[2] = robot.lookup("#player2").queryAs(HBox.class);


        // 플레이어의 피스를 선언
        Node[][] piece = new Node[numOfUser+1][numOfPiece+1];
        for (int user = 1; user <= numOfUser; user++) {
            for (Node node: player[user].getChildren()) {
                piece[user][player[user].getChildren().indexOf(node)+1] = node;
            }
        }

        //첫번째피스이동
        robot.clickOn("#selectButton");
        robot.clickOn("#gae");
        robot.clickOn(piece[1][1]);
        robot.clickOn("#c2");

        //두번째피스이동
        robot.clickOn("#selectButton");
        robot.clickOn("#doe");
        robot.clickOn(piece[2][1]);
        robot.clickOn("#c1");

        //첫번째피스 백도로 두번째 피스를 잡는다.
        robot.clickOn("#selectButton");
        robot.clickOn("#backdoe");
        robot.clickOn("#c2");
        robot.clickOn("#c1");
    }

*/





    /**
     * @param  - Will be injected by the test runner.
     */
    /*@Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        // when:
        robot.clickOn(".button");

        // then:
        FxAssert.verifyThat(button, LabeledMatchers.hasText("clicked!"));
        // or (lookup by css id):
        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("clicked!"));
        // or (lookup by css class):
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("clicked!"));
    }*/

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();

    }
}
