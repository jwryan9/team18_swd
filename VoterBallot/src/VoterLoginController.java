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
    
    @FXML private Text validVoterText;

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

                if(isRegisteredVoter == true && hasVoted == false){

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
                            Thread.sleep(2000);
                        }catch (InterruptedException e){System.out.println("print error thread sleep");};

                        //VoterBallotController ballController = loader.getController();
                        //ballController.setVoterProperties(zipCode);

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


                    /*
                    try {
                        VoterBallotApp newBallot = new VoterBallotApp();
                        newBallot.start(VoterBallotApp.classStage);
                        newBallot.setZipCode(zipCode);

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.close();
                    }catch (Exception e){
                        System.err.println("cannot open ballot");
                        System.err.println(e.getLocalizedMessage());

                    }
                    */
                }
                else {
                    validVoterText.setFill(Color.RED);
                    if(isRegisteredVoter == false) {
                        validVoterText.setText("ERROR: VOTER NOT REGISTERED");
                    }
                    else if(hasVoted == true){
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
