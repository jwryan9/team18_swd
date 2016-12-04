import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by jasonryan on 12/3/16.
 */
public class YearSelectionController {
    @FXML private TextField yearField;

    @FXML private Button setButton;

    @FXML private Text invalidText;

    @FXML private void setYear(ActionEvent event) {
        int year = -1;
        try {
            year = Integer.parseInt(yearField.getText());
        } catch (NumberFormatException ex) {
            year = -1;
            System.err.println("Number Format Exception");
        } finally {
            String isValidText = YearSelectionModel.validateYear(year);

            if(isValidText.isEmpty()) {
                YearSelectionModel.setYear(year);
                invalidText.setText("");
                System.out.println("Year set");

                Parent root;
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("countyAuditor.fxml"));

                    root = loader.load();

                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){System.out.println("print error thread sleep");};

                    //VoterBallotController ballController = loader.getController();
                    //ballController.setVoterProperties(zipCode);

                    Stage stage = new Stage();
                    stage.setTitle("Add Candidate");
                    stage.setScene(new Scene(root));

                    stage.setOnCloseRequest(
                            e -> {
                                Platform.exit();
                                System.exit(0);
                            }
                    );

                    stage.show();

                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    System.err.println("cannot open ballot");
                    StackTraceElement[] stackTraceElements = e.getStackTrace();
                    for(StackTraceElement a:stackTraceElements){
                        System.err.println(a);

                    }

                }

            } else {
                invalidText.setFill(Color.RED);
                invalidText.setText(isValidText);
            }
        }
    }
}
