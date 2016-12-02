import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.xml.ws.FaultAction;
import java.io.IOException;

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

    @FXML private Text registerValidText;

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
        String encryptedSSN = "";

        boolean validInput = VoterRegistrationModel.checkInput(ssn, zipCode);
        System.out.println("ssn :" + ssn);
        System.out.println("First name: " + firstName + "Last Name: " + lastName + "Zip Code: " + zipCode);



        try {
            RunEncryptor myEncryptor = new RunEncryptor(ssn); // create an object of type RunEncryptor
            encryptedSSN = myEncryptor.encodeMessage(); // encrypt the message
            System.out.println("encryptedSSN: " + encryptedSSN);

            if(validInput == true){
                System.out.println("VALID INPUT");

                Voter newVoter = new Voter(encryptedSSN, (firstName+ " " + lastName), zipCode);
                VoterRegistrationModel.exportVoter(encryptedSSN, newVoter);

                registerValidText.setFill(Color.BLACK);
                registerValidText.setText("Registration Information Added");
            }
            else{
                registerValidText.setFill(Color.RED);
                registerValidText.setText("ERROR: INVALID INPUT");
                System.out.println("ERROR: INVALID INPUT");
            }

        } catch (IOException e){
            System.out.println("IOEXCEPTION");
        }


    }


}
