import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sun.jvm.hotspot.debugger.win32.coff.COFFLineNumber;

import javax.swing.*;
import java.awt.*;
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
    @FXML private TextField nameField;

    /**
     * TextField for candidate hometown
     */
    @FXML private TextField zipField;

    /**
     * ComboBox for candidate state selection
     */
    @FXML private ComboBox stateDropdown;

    /**
     * ComboBox for candidate office selection
     */
    @FXML private ComboBox officeDropdown;

    /**
     * ComboBox for candidate party affiliation selection
     */
    @FXML private ComboBox partyDropdown;

    @FXML private Text entryValidText;

    private static Map<String,String[]> officeOptions = new HashMap<>();

    /**
     * Initializes values for comboBoxes and instantiates CandidateMap.
     */
    public void initGUI() {


        stateDropdown.getItems().removeAll();
        stateDropdown.getItems().addAll("AL", "AK", "AZ", "AR", "CA",
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
        officeDropdown.getItems().addAll("US President", "US Senate", "US House",
                                         "Governor", "State Senate", "State House",
                                         "County Judge", "County Sheriff");

        partyDropdown.getItems().removeAll();
        partyDropdown.getItems().setAll("Democrat", "Republican", "Green", "Tea", "Other");

        officeOptions.put("Federal",new String[]{"US President", "US Senate", "US House"});
        officeOptions.put("State",new String[]{"Governor", "State Senate", "State House"});
        officeOptions.put("County",new String[]{"County Judge", "County Sheriff"});

    }

    /**
     * Processes click of add candidate button, adds candidate to database.
     *
     * @param event ActionEvent item of add button click
     */
    @FXML private void processAdd(ActionEvent event) {
        String name = nameField.getText();
        String county = zipField.getText();
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
        String validInput = CountyAuditorModel.checkInput(name,county);

        if(validInput.isEmpty()){
            System.out.println("VALID INPUT");

            Candidate newCandidate = new Candidate(name, county, state, office, party);
            //CandidateMap.put(id,newCandidate);
            CountyAuditorModel.exportCandidate(id, newCandidate, level);

            entryValidText.setFill(Color.BLACK);
            entryValidText.setText("Candidate Added");
            ImageIcon icon = new ImageIcon("countryflag.png");
            showMessage("Added Candidate Successfully",icon);
            resetGUI();
        }
        else{
            entryValidText.setFill(Color.RED);
            entryValidText.setText(validInput);
            System.out.println("ERROR: INVALID INPUT");
        }
    }

    /**
     * Method to reset the fields in the county auditor app after a candidate has been entered.
     */
    private void resetGUI() {
        nameField.setText("");
        zipField.setText("");

        stateDropdown.getSelectionModel().clearSelection();
        officeDropdown.getSelectionModel().clearSelection();
        partyDropdown.getSelectionModel().clearSelection();

        entryValidText.setText("");
    }

    /**
     * Method to show the dialog box after submitting a candidate.
     * @param message   the message to display.
     * @param icon      the icon to display.
     */
    private void showMessage(String message, ImageIcon icon) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                int input = JOptionPane.showOptionDialog(null, message, "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, null, null);
            }
        });
    }
}
