/**
 * Created by jasonryan on 12/1/16.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoterLoginApp extends Application {

    public static Stage classStage;// = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VoterBallotModel.getElectionCycle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterLogin.fxml"));
        Parent root = loader.load();
        classStage = primaryStage;

        VoterLoginController controller = loader.getController();
        VoterLoginModel.getAlreadyVotedFromDatabase();
        VoterLoginModel.getVotersFromDatabase();
        primaryStage.setTitle("Ballot Login");
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }
}
