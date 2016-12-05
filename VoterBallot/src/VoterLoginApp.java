import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class for running voter login application.
 * Extends Application.
 */
public class VoterLoginApp extends Application {

    /**
     * JavaFX stage
     */
    public static Stage classStage;

    /**
     * Main method, launches application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Override method to start application
     *
     * @param primaryStage JavaFX stage
     * @throws Exception for parent
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        VoterBallotModel.getElectionCycle();
        try {
            Thread.sleep(500);
        }catch (Exception e){}
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
        primaryStage.getIcons().add(new Image("file:Resources/american-flag-small.png"));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }
}
