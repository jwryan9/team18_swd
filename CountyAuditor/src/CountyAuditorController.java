import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.awt.*;

/**
 * Created by jasonryan on 11/30/16.
 */
public class CountyAuditorController {
    @FXML
    private javafx.scene.control.TextField nameField;
    @FXML
    private javafx.scene.control.TextField cityField;
    @FXML
    private ComboBox stateDropdown;
    @FXML
    private ComboBox officeDropdown;
    @FXML
    private ComboBox partyDropdown;

    public void setComboBoxes() {
        stateDropdown.getItems().removeAll();
        stateDropdown.getItems().setAll("IL", "IA", "FL");

        officeDropdown.getItems().removeAll();
        officeDropdown.getItems().setAll("U.S. President", "Senator", "County Sheriff");

        partyDropdown.getItems().removeAll();
        partyDropdown.getItems().setAll("Democrat", "Republican", "Green", "Tea");
    }

    @FXML
    private void processAdd(ActiveEvent event) {

    }
}
