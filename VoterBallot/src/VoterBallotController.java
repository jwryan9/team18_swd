import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by jasonryan on 12/1/16.
 */
public class VoterBallotController {
    /**
     * ComboBox for President selection
     */
    @FXML private ComboBox presidentDropdown;

    /**
     * ComboBox for US Senate selection
     */
    @FXML private ComboBox usSenateDropdown;

    /**
     * ComboBox for US House selection
     */
    @FXML private ComboBox usHouseDropdown;

    /**
     * ComboBox for Governor selection
     */
    @FXML private ComboBox governorDropdown;

    /**
     * ComboBox for State Senate selection
     */
    @FXML private ComboBox stateSenateDropdown;

    /**
     * ComboBox for State House selection
     */
    @FXML private ComboBox stateHouseDropdown;

    /**
     * ComboBox for County Judge selection
     */
    @FXML private ComboBox countyJudgeDropdown;

    /**
     * ComboBox for County Sheriff selection
     */
    @FXML private ComboBox countySheriffDropdown;

    /**
     * Text for President selection
     */
    @FXML private Text presidentSelection;

    /**
     * Text for US Senate selection
     */
    @FXML private Text usSenateSelection;

    /**
     * Text for US House selection
     */
    @FXML private Text usHouseSelection;

    /**
     * Text for Governor selection
     */
    @FXML private Text governorSelection;

    /**
     * Text for State Senate selection
     */
    @FXML private Text stateSenateSelection;

    /**
     * Text for State House selection
     */
    @FXML private Text stateHouseSelection;

    /**
     * Text for County Judge selection
     */
    @FXML private Text countyJudgeSelection;

    /**
     * Text for County Sheriff selection
     */
    @FXML private Text countySheriffSelection;

    /**
     * Button for submitting ballot
     */
    @FXML private Button submitButton;

    /**
     * Map holds voter selections
     */
    private Map<String, String> selections;

    private String voterZip;

    private String voterState;

    private String voterCounty;


    public void setVoterProperties(String zip){
        this.voterZip = zip;
        System.out.println("Zip in ballot controller: " + voterZip);

        //ZipCode.parseZip(this.voterZip, "zipcodefile.csv");
        this.voterState = "IL";
        this.voterCounty = "DuPage";
        System.out.println("Zip in ballot controller: " + voterZip + " state: " + voterState);


    }
    /**
     * Initializes ComboBox options of candidates for each office.
     *
     * @throws InterruptedException Thrown if thread is interrupted gathering candidates from database
     */
    public void initialize() throws InterruptedException {
        VoterBallotModel.initFederal();
        Thread.sleep(1000);
        System.out.println("Zip in ballot controller 3: " + voterZip + " state 3: " + voterState);

        Map<String, ArrayList<Candidate>> federalCandidates = VoterBallotModel.getFederalCandidates();// VoterBallotModel.getFederalCandidates();
        while (federalCandidates.keySet().size() < 3) {
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

    /**
     * Sets selections for summary tab based on ComboBox selections
     *
     * @param event ActionEvent calling method
     */
    @FXML private void setSelections(ActionEvent event) {//throws NullPointerException {
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

    /**
     * Fills HashMap with voter selections for tallying.
     * Closes ballot window after submission.
     *
     * @param event ActionEvent calling method
     */
    @FXML private void submitVote(ActionEvent event) {
        final int numOffices = 8;

        String presidentName = presidentSelection.getText().split(" \\(")[0];
        String usSenateName = usSenateSelection.getText().split(" \\(")[0];
        String usHouseName = usHouseSelection.getText().split(" \\(")[0];
        String governorName = governorSelection.getText().split(" \\(")[0];
        String stateSenateName = stateSenateSelection.getText().split(" \\(")[0];
        String stateHouseName = stateHouseSelection.getText().split(" \\(")[0];
        String countyJudgeName = countyJudgeSelection.getText().split(" \\(")[0];
        String countySheriffName = countySheriffSelection.getText().split(" \\(")[0];

        selections = new HashMap<>(numOffices);

        selections.put("US President", String.valueOf(presidentName.hashCode()));
        selections.put("US Senate", String.valueOf(usSenateName.hashCode()));
        selections.put("US House", String.valueOf(usHouseName.hashCode()));
        selections.put("Governor", String.valueOf(governorName.hashCode()));
        selections.put("State Senate", String.valueOf(stateSenateName.hashCode()));
        selections.put("State House", String.valueOf(stateHouseName.hashCode()));
        selections.put("County Judge", String.valueOf(countyJudgeName.hashCode()));
        selections.put("County Sheriff", String.valueOf(countySheriffName.hashCode()));

        System.out.println("Zip in ballot controller 2: " + voterZip + " state 2: " + voterState);

        VoteProcessor.addBallot(selections, voterZip, voterCounty, voterState);

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
