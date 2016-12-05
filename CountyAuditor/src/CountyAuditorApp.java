
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;

/**
 * Class to run application for adding candidates to the database.
 */
public class CountyAuditorApp extends Application {

    /**
     * Method to start the javaFX program.
     *
     * @param primaryStage      the primary window.
     * @throws Exception        throws exception for Parent use.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        CountyAuditorModel.getElectionCycle();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("countyAuditor.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("countyAuditor.fxml"));
        Parent root = loader.load();

        CountyAuditorController controller = loader.getController();
        controller.initGUI();

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

    /**
     * Main method to launch the application.
     */
    public static void main() {
        launch();
    }
}
