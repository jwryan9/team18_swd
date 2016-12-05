import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for voter ballot application
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

    /**
     * Voter's zip code
     */
    private String voterZip;

    /**
     * Voter's state of residence
     */
    private String voterState;

    /**
     * Voter's county of residence
     */
    private String voterCounty;

    /**
     * Voter's encrypted ssn, used for data base identification
     */
    private String encryptedSSN;


    /**
     * Sets attributes of voter for generating the correct ballot
     * @param zip voter's zip code
     * @param encryptedSSN voter's encrypted social security number
     */
    public void setVoterProperties(String zip, String encryptedSSN){
        this.encryptedSSN = encryptedSSN;
        this.voterZip = zip;
        System.out.println("Zip in ballot controller: " + voterZip);

        String[] zipArray = ZipCode.parseZip(this.voterZip, "Resouces/zipcodes.csv");
        if(zipArray != null) {
            this.voterCounty = zipArray[0];
            this.voterState = zipArray[1];
        }
        System.out.println("Zip in ballot controller: " + voterZip + " county: " + voterCounty + " state: " + voterState);


    }
    /**
     * Initializes ComboBox options of candidates for each office.
     *
     * @throws InterruptedException Thrown if thread is interrupted gathering candidates from database
     */
    public void initialize() throws InterruptedException {
        VoterBallotModel vlm = new VoterBallotModel();
        VoterBallotModel.initFederal();

        vlm.initState(this.voterState);
        vlm.initCounty(this.voterCounty, this.voterState);

        Thread.sleep(1000);

        Map<String, ArrayList<Candidate>> federalCandidates = VoterBallotModel.getFederalCandidates();// VoterBallotModel.getFederalCandidates();
        while (federalCandidates.keySet().size() < 3) {
            federalCandidates = VoterBallotModel.getFederalCandidates();
        }

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

        Map<String,Candidate> stateSenateCandidates = vlm.getStateSenateCandidates();
        Map<String,Candidate> stateHouseCandidates = vlm.getStateHouseCandidates();
        Map<String,Candidate> governorCandidates = vlm.getGovernorCandidates();

        stateSenateDropdown.getItems().add("");
        stateHouseDropdown.getItems().add("");
        governorDropdown.getItems().add("");

        for (String nextCandidate : stateSenateCandidates.keySet()) {
            stateSenateDropdown.getItems().add(stateSenateCandidates.get(nextCandidate).getName() + " (" + stateSenateCandidates.get(nextCandidate).getParty() + ")");

        }
        for (String nextCandidate : stateHouseCandidates.keySet()) {
            stateHouseDropdown.getItems().add(stateHouseCandidates.get(nextCandidate).getName() + " (" + stateHouseCandidates.get(nextCandidate).getParty() + ")");
        }

        for (String nextCandidate : governorCandidates.keySet()) {
            governorDropdown.getItems().add(governorCandidates.get(nextCandidate).getName() + " (" + governorCandidates.get(nextCandidate).getParty() + ")");
        }

        Map<String,Candidate> countyJudgeCandidates = vlm.getCountyJudgeCandidates();
        Map<String,Candidate> countySheriffCandidates = vlm.getCountySheriffCandidates();

        System.out.println("judge candi count: " + countyJudgeCandidates.size());
        System.out.println("sheriff candi count: " + countySheriffCandidates.size());
        System.out.println("judge candidates: " + countyJudgeCandidates);
        System.out.println("sheriff candidates: " + countySheriffCandidates);

        countyJudgeDropdown.getItems().add("");
        countySheriffDropdown.getItems().add("");

        for (String nextCandidate : countyJudgeCandidates.keySet()) {
            countyJudgeDropdown.getItems().add(countyJudgeCandidates.get(nextCandidate).getName() + " (" + countyJudgeCandidates.get(nextCandidate).getParty() + ")");

        }

        for (String nextCandidate : countySheriffCandidates.keySet()) {
            countySheriffDropdown.getItems().add(countySheriffCandidates.get(nextCandidate).getName() + " (" + countySheriffCandidates.get(nextCandidate).getParty() + ")");
        }
    }

    /**
     * Sets selections for summary tab based on ComboBox selections
     *
     * @param event ActionEvent calling method
     */
    @FXML private void setSelections(ActionEvent event) {
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
            case "countySheriffDropdown":
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

        VoterLoginModel.markVoterAsHasVoted(this.encryptedSSN);

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

        selections.put("US President", String.valueOf(presidentName));
        selections.put("US Senate", String.valueOf(usSenateName));
        selections.put("US House", String.valueOf(usHouseName));
        selections.put("Governor", String.valueOf(governorName));
        selections.put("State Senate", String.valueOf(stateSenateName));
        selections.put("State House", String.valueOf(stateHouseName));
        selections.put("County Judge", String.valueOf(countyJudgeName));
        selections.put("County Sheriff", String.valueOf(countySheriffName));

        VoteProcessor.addBallot(selections, voterZip, voterCounty, voterState);

        openLogin();

    }

    /**
     * Opens new login window for next voter.
     */
    private void openLogin(){
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
