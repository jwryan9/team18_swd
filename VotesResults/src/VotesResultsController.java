import com.sun.corba.se.spi.monitoring.LongMonitoredAttributeBase;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

//import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsController {
    // Declare instance variables
    /**
     * Tab for federal results
     */
    @FXML
    private Tab federal;
    /**
     * Tab for state results
     */
    @FXML
    private Tab state;
    /**
     * Tab for county results
     */
    @FXML
    private Tab county;
    /**
     * ChoiceBox to choose federal representative
     */
    @FXML
    private ComboBox federalOffice;
    /**
     * ChoiceBox to choose state
     */
    @FXML
    private ComboBox stateChoice;
    /**
     * ChoiceBox to choose state representative
     */
    @FXML
    private ComboBox stateOffice;
    /**
     * ChoiceBox to choose state for county
     */
    @FXML
    private ComboBox countyState;
    /**
     * ChoiceBox to choose county
     */
    @FXML
    private ComboBox countyChoice;
    /**
     * ChoiceBox to choose county representative
     */
    @FXML
    private ComboBox countyOffice;
    /**
     * TextField to enter year of federal polls
     */
    @FXML
    private TextField federalYearBox;
    /**
     * TextField to enter year of state polls
     */
    @FXML
    private TextField stateYearBox;
    /**
     * TextField to enter year of county polls
     */
    @FXML
    private TextField countyYearBox;
    /**
     * Slider to select the year of federal polls
     */
    @FXML
    private Slider federalYearSlider;
    /**
     * Slider to select the year of state polls
     */
    @FXML
    private Slider stateYearSlider;
    /**
     * Slider to select the year of county polls
     */
    @FXML
    private Slider countyYearSlider;
    /**
     * Chart to view the results of the federal polls
     */
    @FXML
    private StackedBarChart federalChart;
    /**
     * Chart to view the results of the state polls
     */
    @FXML
    private StackedBarChart stateChart;
    /**
     * Chart to view the results of the county polls
     */
    @FXML
    private StackedBarChart countyChart;
    /**
     * Label to display brief user federal information input error message
     */
    @FXML
    private Label federalError;
    /**
     * Label to display brief user state information input error message
     */
    @FXML
    private Label stateError;
    /**
     * Label to display brief user county information input error message
     */
    @FXML
    private Label countyError;

    //  Variables to hold the values of the controls

    String fedOffice = "";
    String sta = "";
    String staOffice = "";
    String couState = "";
    String cou = "";
    String couOffice = "";
    int fedYear = 0;
    int staYear = 0;
    int couYear = 0;
    int fedSlider = 0;
    int staSlider = 0;
    int couSlider = 0;

    public void initGUI() {

        federalOffice.getItems().removeAll();
        federalOffice.getItems().addAll("US President", "US Senate", "US House");

        stateChoice.getItems().removeAll();
        stateChoice.getItems().addAll("AL", "AK", "AZ", "AR", "CA",
                                      "CO", "CT", "DE", "FL", "GA",
                                      "HI", "ID", "IL", "IN", "IA",
                                      "KS", "KY", "LA", "ME", "MD",
                                      "MA", "MI", "MN", "MS", "MO",
                                      "MT", "NE", "NV", "NH", "NJ",
                                      "NM", "NY", "NC", "ND", "OH",
                                      "OK", "OR", "PA", "RI", "SC",
                                      "SD", "TN", "TX", "UT", "VT",
                                      "VA", "WA", "WV", "WI", "WY");

//        stateOffice.getItems().removeAll();
//        stateOffice.getItems().addAll("Governor", "State Senate", "State House");
//
//        countyState.getItems().removeAll();
//        countyState.getItems().addAll("AL", "AK", "AZ", "AR", "CA",
//                "CO", "CT", "DE", "FL", "GA",
//                "HI", "ID", "IL", "IN", "IA",
//                "KS", "KY", "LA", "ME", "MD",
//                "MA", "MI", "MN", "MS", "MO",
//                "MT", "NE", "NV", "NH", "NJ",
//                "NM", "NY", "NC", "ND", "OH",
//                "OK", "OR", "PA", "RI", "SC",
//                "SD", "TN", "TX", "UT", "VT",
//                "VA", "WA", "WV", "WI", "WY");

       // countyChoice.getItems().removeAll();
       // countyChoice.getItems().addAll();

       // countyOffice.getItems().removeAll();
       // countyOffice.getItems().addAll("County Judge", "County Sheriff");
    }


    @FXML
    private void actionEventHandler(ActionEvent event) {
        if(event.getSource()==federalYearBox) {
            federalError.setText("");
            try {
                fedYear = Integer.parseInt(federalYearBox.getText());
            } catch (InputMismatchException ime) {
                federalError.setText("Invalid year");
            }
        } else if(event.getSource()==stateYearBox) {
            stateError.setText("");
            try {
                fedYear = Integer.parseInt(stateYearBox.getText());
            } catch (InputMismatchException ime) {
                stateError.setText("Invalid year");
            }
        } else if(event.getSource()==countyYearBox) {
            countyError.setText("");
            try {
                fedYear = Integer.parseInt(countyYearBox.getText());
            } catch (InputMismatchException ime) {
                countyError.setText("Invalid Year");
            }
        }
        updateGUI();
    }
    /*
    private void changeEventHandler(ChangeEvent event) {

    }
    */
    private void updateGUI(){
        if(federal.isSelected()&&(fedOffice!=null&&fedYear!=0&&fedSlider!=0)) {
            //
        } else if(state.isSelected()) {
            //
        } else if(county.isSelected()) {
            //
        }
    }
}