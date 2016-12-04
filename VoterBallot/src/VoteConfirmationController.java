import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by jasonryan on 12/4/16.
 */
public class VoteConfirmationController {
    @FXML Button okayButton;

    @FXML private void buttonPress(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("voterLogin.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Voter Login");
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest(
                    e -> {
                        Platform.exit();
                        System.exit(0);
                    }
            );

            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            System.err.println("cannot open voter login");
        }
    }
}
