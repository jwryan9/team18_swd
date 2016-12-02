/**
 * Created by jasonryan on 12/1/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoterRegistrationApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterRegistration.fxml"));
        Parent root = loader.load();

        VoterRegistrationController controller = loader.getController();
        //controller.initCandidates();

        primaryStage.setTitle("Voter Registration");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
