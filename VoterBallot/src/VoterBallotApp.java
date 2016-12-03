/**
 * Created by jasonryan on 12/1/16.
 */

import com.sun.scenario.effect.impl.prism.PrImage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoterBallotApp extends Application {

    public static Stage classStage;// = new Stage();

    public String zipCode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("HERE123456");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterBallot.fxml"));
        Parent root = loader.load();
        classStage = primaryStage;
        VoterBallotController controller = loader.getController();
        //controller.initCandidates();
        controller.setVoterProperties("12345");


        primaryStage.setTitle("Ballot");
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }

    public void setZipCode(String zip){
        this.zipCode = zip;
    }

}
