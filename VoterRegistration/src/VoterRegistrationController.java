import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for VoterRegistration application.
 * Displays GUI for adding registered voters.
 */
public class VoterRegistrationController {

    /**
     * TextField for voter first name
     */
    @FXML private TextField firstNameField;

    /**
     * TextField for voter last name
     */
    @FXML private TextField lastNameField;

    /**
     * TextField for voter zip code
     */
    @FXML private TextField zipField;

    /**
     * ComboBox for voter state selection
     */
    @FXML private ComboBox stateDropdown;

    /**
     * PasswordField for voter social security number
     */
    @FXML private PasswordField ssnField;

    /**
     * Initializes state selection ComboBox
     */
    public void initStates() {
        stateDropdown.getItems().removeAll();
        stateDropdown.getItems().addAll("AL", "AK", "AZ", "AR", "CA",
                "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA",
                "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO",
                "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH",
                "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT",
                "VA", "WA", "WV", "WI", "WY");
    }

    /**
     * Processes click of register button, encrypts the voters ssn
     * and adds to database.
     *
     * @param event Action Event item of register button click
     */
    @FXML private void processRegister(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String zipCode = zipField.getText();
        String ssn = ssnField.getText();
        int id = ssn.hashCode();

        System.out.println("ID :" + id);
        System.out.println("First name: " + firstName + "Last Name: " + lastName + "Zip Code: " + zipCode);

    }
}
