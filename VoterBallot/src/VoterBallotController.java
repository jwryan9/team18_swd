import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jasonryan on 12/1/16.
 */
public class VoterBallotController {
    @FXML private ComboBox presidentDropdown;

    @FXML private ComboBox usSenateDropdown;

    @FXML private ComboBox usHouseDropdown;

    @FXML private ComboBox governorDropdown;

    @FXML private ComboBox stateSenateDropdown;

    @FXML private ComboBox stateHouseDropdown;

    @FXML private ComboBox countyJudgeDropdown;

    @FXML private ComboBox countySheriffDropdown;

    @FXML private Text presidentSelection;

    @FXML private Text usSenateSelection;

    @FXML private Text usHouseSelection;

    @FXML private Text governorSelection;

    @FXML private Text stateSenateSelection;

    @FXML private Text stateHouseSelection;

    @FXML private Text countyJudgeSelection;

    @FXML private Text countySheriffSelection;

    @FXML private Button submitButton;

    public void initCandidates() throws InterruptedException {
        VoterBallotModel.initFederal();

        Map<String,ArrayList<Candidate>> federalCandidates = VoterBallotModel.getFederalCandidates();// VoterBallotModel.getFederalCandidates();

        while(federalCandidates.keySet().size()<3){
            federalCandidates = VoterBallotModel.getFederalCandidates();
        }
        System.out.println("feds keyset:" + federalCandidates.keySet());

        presidentDropdown.getItems().add("");
        usSenateDropdown.getItems().add("");
        usHouseDropdown.getItems().add("");

        for (Candidate nextCandidate : federalCandidates.get("US President")) {
            presidentDropdown.getItems().add(nextCandidate.getName() + " (" + nextCandidate.getParty() + ")");

        }
        for (Candidate nextCandidate : federalCandidates.get("US Senate")) {
            usSenateDropdown.getItems().add(nextCandidate.getName() + " (" + nextCandidate.getParty() + ")");
        }

        for (Candidate nextCandidate : federalCandidates.get("US House")) {
            usHouseDropdown.getItems().add(nextCandidate.getName() + " (" + nextCandidate.getParty() + ")");
        }
    }



    @FXML private void setSelections(ActionEvent event) throws NullPointerException {
        ComboBox a = (ComboBox) event.getSource();
        System.out.println(a.getId());
        switch (a.getId()) {
            case "presidentDropdown":
                presidentSelection.setText(presidentDropdown.getValue().toString());
                break;
            case "usSenateDropdown":
                usSenateSelection.setText(usSenateDropdown.getValue().toString());
                break;
            case "usHouseDropdown":
                usHouseSelection.setText(usHouseDropdown.getValue().toString());
                break;
            case "governorDropdown":
                governorSelection.setText(governorDropdown.getValue().toString());
                break;
            case "stateSenateDropdown":
                stateSenateSelection.setText(stateSenateDropdown.getValue().toString());
                break;
            case "stateHouseDropdown":
                stateHouseSelection.setText(stateHouseDropdown.getValue().toString());
                break;
            case "countyJudgeDropdown":
                countyJudgeSelection.setText(countyJudgeDropdown.getValue().toString());
                break;
            case "countySheriffSelection":
                countySheriffSelection.setText(countySheriffDropdown.getValue().toString());
                break;
        }
    }

    @FXML private void submitVote(ActionEvent event) {
        try {
            VoterLoginApp newLogin = new VoterLoginApp();
            newLogin.start(VoterLoginApp.classStage);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Cannot open login.");
        }
    }
}
