import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VotesResultsModel.getElectionCycleFromDatabase();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("votesResults.fxml"));
        Parent root = loader.load();

        VotesResultsController controller = loader.getController();
        controller.initGUI();

        primaryStage.setTitle("!!POLLS ARE IN!!");
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }
    public static void main(String args[]) {launch(args);}
}
