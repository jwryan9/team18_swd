import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;

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



    @FXML private void setSelections(ActionEvent event) throws NullPointerException {
        presidentSelection.setText(presidentDropdown.getValue().toString());
        usSenateSelection.setText(usSenateDropdown.getValue().toString());
        usHouseSelection.setText(usHouseDropdown.getValue().toString());

        governorSelection.setText(governorDropdown.getValue().toString());
        stateSenateSelection.setText(stateSenateDropdown.getValue().toString());
        stateHouseSelection.setText(stateHouseDropdown.getValue().toString());

        countyJudgeSelection.setText(countyJudgeDropdown.getValue().toString());
        countySheriffSelection.setText(countySheriffDropdown.getValue().toString());
    }

    @FXML private void submitVote(ActionEvent event) {

    }
}
