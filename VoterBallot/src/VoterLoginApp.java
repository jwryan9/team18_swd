/**
 * Created by jasonryan on 12/1/16.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class VoterLoginApp extends Application {

    public static Stage classStage;// = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VoterBallotModel.getElectionCycle();
        try {
            Thread.sleep(500);
        }catch (Exception e){};
        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterLogin.fxml"));
        Parent root = loader.load();
        classStage = primaryStage;

        VoterLoginController controller = loader.getController();
        VoterLoginModel.getVotersFromDatabase();
        VoterLoginModel.getAlreadyVotedFromDatabase();

        System.out.println(VoterBallotModel.getElectionYear());
        System.out.println(VoterLoginModel.getAlreadyVotedString());

        primaryStage.setTitle("Ballot Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:american-flag-small.png"));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }
}
