import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.applet.Applet;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("votesResults.fxml"));
        Parent root = loader.load();

        VotesResultsController controller = loader.getController();
        controller.initGUI();

        primaryStage.setTitle("!!POLLS ARE IN!!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main() {launch();}
}
