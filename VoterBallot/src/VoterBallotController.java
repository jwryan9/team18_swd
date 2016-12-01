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


    public void initCandidates() {
        initFederal();
        initState();
        initCounty();
    }

    private void initFederal() {

    }

    private void initState() {

    }

    private void initCounty() {

    }

    @FXML
    private void submitVote(ActionEvent event) {

    }
}
