import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
        String encryptedSSN;
        boolean validInput = VoterLoginModel.validateInput(ssn, zipCode);
        boolean isRegisteredVoter = false;


        try {
            RunEncryptor myEncryptor = new RunEncryptor(ssn); // create an object of type RunEncryptor
            encryptedSSN = myEncryptor.encodeMessage(); // encrypt the message
            System.out.println("encryptedSSN: " + encryptedSSN);

            if(validInput == true){
                System.out.println("VALID INPUT");
                isRegisteredVoter = VoterLoginModel.checkVoterRegistration(encryptedSSN, zipCode);
                if(isRegisteredVoter){
                    System.out.println("Congrats, you're registered!");
                }
                else{
                    System.out.println("You aren't registered!");

                }
            }
            else{
                System.out.println("ERROR: INVALID INPUT");
            }

        } catch (IOException e){
            System.out.println("IOEXCEPTION");
        }


    }
}
