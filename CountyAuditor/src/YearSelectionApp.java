import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application for setting election cycle year
 */
public class YearSelectionApp extends Application {
    /**
     * Main method calls launch
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to start JavaFX application
     *
     * @param primaryStage primary application stage
     * @throws Exception for parent use
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("electionYearSelection.fxml"));
        Parent root = loader.load();

        YearSelectionController controller = loader.getController();

        primaryStage.setTitle("Candidate Entry");
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
