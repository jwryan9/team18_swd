import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for voter login application
 */
public class VoterLoginController {

    /**
     * PasswordField for user social security number
     */
    @FXML private PasswordField ssnField;

    /**
     * TextField for user zip code
     */
    @FXML private TextField zipField;

    /**
     * Button to login to ballot
     */
    @FXML private Button loginButton;

    /**
     * Text for displaying invalid login information
     */
    @FXML private Text validVoterText;

    /**
     * Checks if voter provides valid input and if voter is a registered voter.
     * Open's voter's ballot.
     *
     * @param event ActionEvent source of event
     */
    @FXML private void login(ActionEvent event) {
        String ssn = ssnField.getText();
        String zipCode = zipField.getText();
        String encryptedSSN;
        boolean isRegisteredVoter;
        boolean hasVoted;

        String validInput = VoterLoginModel.validateInput(ssn, zipCode);

        try {

            if(validInput.isEmpty()){
                System.out.println("VALID INPUT");

                RunEncryptor myEncryptor = new RunEncryptor(ssn); // create an object of type RunEncryptor
                encryptedSSN = myEncryptor.encodeMessage(); // encrypt the message
                System.out.println("encryptedSSN: " + encryptedSSN);

                isRegisteredVoter=VoterLoginModel.checkVoterRegistrationQuery(encryptedSSN,zipCode);
                hasVoted = VoterLoginModel.checkAlreadyVoted(encryptedSSN);

                System.out.println("has voted: " + hasVoted);

                if(isRegisteredVoter && !hasVoted){

                    System.out.println();
                    System.out.println("Registered Voter Found");
                    System.out.println("Opening Ballot");

                    validVoterText.setFill(Color.BLACK);
                    validVoterText.setText("Registered voter found");

                    Parent root;
                    try {
                        VoterBallotController ballController1 = new VoterBallotController();
                        ballController1.setVoterProperties(zipCode, encryptedSSN);


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("voterBallot.fxml"));

                        loader.setController(ballController1);

                        root = loader.load();

                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                            System.err.println("print error thread sleep");
                        }

                        Stage stage = new Stage();
                        stage.setTitle("Ballot");
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
                }
                else {
                    validVoterText.setFill(Color.RED);
                    if(!isRegisteredVoter) {
                        validVoterText.setText("ERROR: VOTER NOT REGISTERED");
                    }
                    else if(hasVoted){
                        validVoterText.setText("ERROR: VOTER HAS ALREADY VOTED");
                    }
                    System.out.println("Registered Voter Not Found");
                }


            }
            else{
                validVoterText.setFill(Color.RED);
                validVoterText.setText(validInput);
                System.out.println("ERROR: INVALID INPUT");
            }

        } catch (IOException e){
            System.out.println("IOEXCEPTION");
        }
    }

}
