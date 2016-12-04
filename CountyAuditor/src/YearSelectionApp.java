import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jasonryan on 12/3/16.
 */
public class YearSelectionApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

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
