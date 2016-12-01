import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jasonryan on 12/1/16.
 */
public class VoterBallotController {
    @FXML
    private ComboBox presidentDropdown;

    @FXML
    private ComboBox usSenateDropdown;

    @FXML
    private ComboBox usHouseDropdown;

    @FXML
    private ComboBox governorDropdown;

    @FXML
    private ComboBox stateSenateDropdown;

    @FXML
    private ComboBox stateHouseDropdown;


    public void initCandidates() {
        VoterBallotModel.initFederal();
        Map<String,ArrayList<Candidate>> federalCandidates = VoterBallotModel.getFederalCandidates();

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

        //VoterBallotModel.initState();
        //VoterBallotModel.initCounty();
    }



    @FXML
    private void submitVote(ActionEvent event) {

    }
}
