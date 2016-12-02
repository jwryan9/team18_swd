import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by jasonryan on 12/1/16.
 */
public class VoterLoginController {

    @FXML private PasswordField ssnField;

    @FXML private TextField zipField;

    @FXML private Button loginButton;

    @FXML
    private void login(ActionEvent event) {
        String ssn = ssnField.getText();
        String zipCode = zipField.getText();

        if(zipCode.length() == 5 && zipCode.matches("\\d+") &&
                ssn.length() == 9 && ssn.matches("\\d+"))
        {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid voter information.");
        }

    }
}
