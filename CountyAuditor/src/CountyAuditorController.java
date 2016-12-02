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
    private TextField nameField;

    /**
     * TextField for candidate hometown
     */
    @FXML
    private TextField cityField;

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

    private static Map<String,String[]> officeOptions = new HashMap<>();

    /**
     * Initializes values for comboBoxes and instantiates CandidateMap
     */
    public void initGUI() {


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
                                         "Governor", "State Senate", "State House",
                                         "County Judge", "County Sheriff");

        partyDropdown.getItems().removeAll();
        partyDropdown.getItems().setAll("Democrat", "Republican", "Green", "Tea", "Other");

        officeOptions.put("Federal",new String[]{"US President", "US Senate", "US House"});
        officeOptions.put("State",new String[]{"Governor", "State Senate", "State House"});
        officeOptions.put("County",new String[]{"County Judge", "County Sheriff"});

    }

    /**
     * Processes click of add candidate button, adds candidate to database
     *
     * @param event ActionEvent item relating to add button
     */
    @FXML
    private void processAdd(ActionEvent event) {
        String name = nameField.getText();
        String county = cityField.getText();
        String state = stateDropdown.getSelectionModel().getSelectedItem().toString();
        String office = officeDropdown.getSelectionModel().getSelectedItem().toString();
        String party = partyDropdown.getSelectionModel().getSelectedItem().toString();
        int id = name.hashCode();
        System.out.println("ID " + id);
        System.out.println("Name " + name + " County " + county + " State " + state);

        String level = "Federal";
        for(String key:officeOptions.keySet()){
            for(String value:officeOptions.get(key)){
                if(office == value){
                    level = key;
                }
            }
        }
        System.out.println("Level Key:" + level);
        boolean validInput = CountyAuditorModel.checkInput(name,county);

        if(validInput == true){
            System.out.println("VALID INPUT");

            Candidate newCandidate = new Candidate(name, county, state, office, party);
            //CandidateMap.put(id,newCandidate);
            CountyAuditorModel.exportCandidate(id, newCandidate, level);
        }
        else{
            System.out.println("ERROR: INVALID INPUT");
        }
    }
}
