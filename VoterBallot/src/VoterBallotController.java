import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

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

    public void initCandidates() {
        initFederal();
        initState();
        initCounty();
    }

    private void initFederal() {
        presidentDropdown.getItems().removeAll();
        presidentDropdown.getItems().setAll("TestPres1", "TestPres2");

        usSenateDropdown.getItems().removeAll();
        usSenateDropdown.getItems().setAll("TestUSSenate1", "TestUSSenate2");

        usHouseDropdown.getItems().removeAll();
        usHouseDropdown.getItems().setAll("TestUSHouse1", "TestUSHouse2");

        presidentSelection.setText("");
        usSenateSelection.setText("");
        usHouseSelection.setText("");
    }

    private void initState() {
        governorDropdown.getItems().removeAll();
        governorDropdown.getItems().setAll("TestGov1", "TestGov2");

        stateSenateDropdown.getItems().removeAll();
        stateSenateDropdown.getItems().setAll("TestStateSenate1", "TestStateSenate2");

        stateHouseDropdown.getItems().removeAll();
        stateHouseDropdown.getItems().setAll("TestStateHouse1", "TestStateHouse2");

        stateSenateSelection.setText("");
        stateHouseSelection.setText("");
    }

    private void initCounty() {
        countyJudgeDropdown.getItems().removeAll();
        countyJudgeDropdown.getItems().setAll("TestJudge1", "TestJudge2");

        countySheriffDropdown.getItems().removeAll();
        countySheriffDropdown.getItems().setAll("TestSheriff1", "TestSheriff2");

        countyJudgeSelection.setText("");
        countySheriffSelection.setText("");
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
