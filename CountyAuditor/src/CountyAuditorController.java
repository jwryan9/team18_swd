import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for CountyAuditor application.
 * Displays GUI for adding candidates running for various
 * elected offices.
 */
public class CountyAuditorController {
    /**
     * TextField for candidate name
     */
    @FXML
    private javafx.scene.control.TextField nameField;

    /**
     * TextField for candidate hometown
     */
    @FXML
    private javafx.scene.control.TextField cityField;

    /**
     * ComboBox for candidate state selection
     */
    @FXML
    private ComboBox stateDropdown;

    /**
     * ComboBox for candidate office selection
     */
    @FXML
    private ComboBox officeDropdown;

    /**
     * ComboBox for candidate party affiliation selection
     */
    @FXML
    private ComboBox partyDropdown;

    private Map<Integer,Candidate> CandidateMap;

    /**
     * Initializes values for comboBoxes and instantiates CandidateMap
     */
    public void initGUI() {

        CandidateMap = new HashMap<>();

        stateDropdown.getItems().removeAll();
        stateDropdown.getItems().setAll("AL", "AK", "AZ", "AR", "CA",
                                        "CO", "CT", "DE", "FL", "GA",
                                        "HI", "ID", "IL", "IN", "IA",
                                        "KS", "KY", "LA", "ME", "MD",
                                        "MA", "MI", "MN", "MS", "MO",
                                        "MT", "NE", "NV", "NH", "NJ",
                                        "NM", "NY", "NC", "ND", "OH",
                                        "OK", "OR", "PA", "RI", "SC",
                                        "SD", "TN", "TX", "UT", "VT",
                                        "VA", "WA", "WV", "WI", "WY");

        officeDropdown.getItems().removeAll();
        officeDropdown.getItems().setAll("US President", "US Senate", "US House",
                                        "State Senate", "State House", "Governor",
                                        "County Judge", "County Sheriff");

        partyDropdown.getItems().removeAll();
        partyDropdown.getItems().setAll("Democrat", "Republican", "Green", "Tea", "Other");
    }

    /**
     * Processes click of add candidate button, adds candidate to database
     *
     * @param event ActionEvent item relating to add button
     */
    @FXML
    private void processAdd(ActionEvent event) {

        String name = nameField.getText();
        String city = cityField.getText();
        String state = stateDropdown.getSelectionModel().getSelectedItem().toString();
        String office = officeDropdown.getSelectionModel().getSelectedItem().toString();
        String party = partyDropdown.getSelectionModel().getSelectedItem().toString();
        int id = 3;

        System.out.println("Name" + name + " City" + city + " State" + state);

        boolean validInput = CountyAuditorModel.checkInput(name,city);

        if(validInput == true){
            System.out.println("VALID INPUT");

            Candidate newCandidate = new Candidate(id, name, city, state, office, party);
            CandidateMap.put(id,newCandidate);
            CountyAuditorModel.exportCandidate(id, newCandidate);
        }
        else{
            System.out.println("ERROR: INVALID INPUT");
        }
    }
}
