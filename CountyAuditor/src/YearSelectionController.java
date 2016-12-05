import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for Year Selection application
 */
public class YearSelectionController {
    /**
     * TextField for entering year
     */
    @FXML private TextField yearField;

    /**
     * Button for setting year
     */
    @FXML private Button setButton;

    /**
     * Text to inform user if input is invalid
     */
    @FXML private Text invalidText;

    /**
     * Sets year for election cycle
     * @param event ActionEvent calling method
     */
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

            } else {
                invalidText.setFill(Color.RED);
                invalidText.setText(isValidText);
            }
        }
    }
}
