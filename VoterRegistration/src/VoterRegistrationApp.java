import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application for registering voters.
 * Extends Application.
 */
public class VoterRegistrationApp extends Application {

    /**
     * Main method, launches application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts application, shows stage to user
     *
     * @param primaryStage JavaFX stage
     * @throws Exception for parent use
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterRegistration.fxml"));
        Parent root = loader.load();

        VoterRegistrationController controller = loader.getController();
        controller.initStates();

        primaryStage.setTitle("Voter Registration");
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
