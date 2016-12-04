import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
            } else {
                invalidText.setFill(Color.RED);
                invalidText.setText(isValidText);
            }
        }
    }
}
