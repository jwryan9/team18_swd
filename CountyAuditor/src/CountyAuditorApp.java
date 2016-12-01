/**
 * Created by jasonryan on 11/30/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountyAuditorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("countyAuditor.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("countyAuditor.fxml"));
        Parent root = loader.load();

        CountyAuditorController controller = loader.getController();
        controller.initGUI();

        primaryStage.setTitle("Candidate Entry");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
