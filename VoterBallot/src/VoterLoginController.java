import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jasonryan on 12/1/16.
 */
public class VoterLoginController {

    @FXML private PasswordField ssnField;

    @FXML private TextField zipField;

    @FXML private Button loginButton;
    
    @FXML private Text loginFailText;

    @FXML private void login(ActionEvent event) {
        String ssn = ssnField.getText();
        String zipCode = zipField.getText();
        String encryptedSSN;
        boolean isRegisteredVoter;

        boolean validInput = VoterLoginModel.validateInput(ssn, zipCode);


        try {

            if(validInput == true){
                System.out.println("VALID INPUT");

                RunEncryptor myEncryptor = new RunEncryptor(ssn); // create an object of type RunEncryptor
                encryptedSSN = myEncryptor.encodeMessage(); // encrypt the message
                System.out.println("encryptedSSN: " + encryptedSSN);

                isRegisteredVoter=VoterLoginModel.checkVoterRegistrationQuery(encryptedSSN,zipCode);

                if(isRegisteredVoter == true){
                    System.out.println();
                    System.out.println("Registered Voter Found");
                    System.out.println("Opening Ballot");

                    loginFailText.setFill(Color.BLACK);
                    loginFailText.setText("Registered Voter Found");
                    try {
                        VoterBallotApp newBallot = new VoterBallotApp();
                        newBallot.start(VoterBallotApp.classStage);

                    }catch (Exception e){
                        System.err.println("cannot open ballot");
                    }
                }
                else {
                    loginFailText.setFill(Color.RED);
                    loginFailText.setText("ERROR: VOTER NOT REGISTERED");
                    System.out.println("Registered Voter Not Found");
                }


            }
            else{
                loginFailText.setFill(Color.RED);
                loginFailText.setText("ERROR: INVALID INPUT");
                System.out.println("ERROR: INVALID INPUT");
            }

        } catch (IOException e){
            System.out.println("IOEXCEPTION");
        }
    }
}
