import com.sun.corba.se.spi.monitoring.LongMonitoredAttributeBase;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;

//import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsController {
    //                                                      Tabs

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

    //                                                      ComboBoxes

    /**
     * ComboBox to choose chart to view federal results in
     */
    @FXML
    private ComboBox federalChartChoice;
    /**
     * ComboBox to choose federal representative
     */
    @FXML
    private ComboBox federalOffice;
    /**
     * ComboBox to choose chart to view state results in
     */
    @FXML
    private ComboBox stateChartChoice;
    /**
     * ComboBox to choose state
     */
    @FXML
    private ComboBox stateChoice;
    /**
     * ComboBox to choose state representative
     */
    @FXML
    private ComboBox stateOffice;
    /**
     * ComboBox to choose chart to view county results in
     */
    @FXML
    private ComboBox countyChartChoice;
    /**
     * ComboBox to choose state for county
     */
    @FXML
    private ComboBox countyState;
    /**
     * ComboBox to choose county
     */
    @FXML
    private ComboBox countyChoice;
    /**
     * ComboBox to choose county representative
     */
    @FXML
    private ComboBox countyOffice;

    //                                                        TextFields

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

    //                                                        Sliders

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

    //                                                          Charts

    /**
     * Bar chart to view the results of the federal polls
     */
    @FXML
    private StackedBarChart<String, Integer> federalBarChart;
    @FXML
    private CategoryAxis federalBarChartXAxis;
    @FXML
    private NumberAxis federalBarChartYAxis;
    @FXML
    private XYChart.Series<String, Integer> federalSeries;
    /**
     * Bar chart to view the results of the state polls
     */
    @FXML
    private StackedBarChart<String,Integer> stateBarChart;
    @FXML
    private CategoryAxis stateBarChartXAxis;
    @FXML
    private NumberAxis stateBarChartYAxis;
    @FXML
    private XYChart.Series<String, Integer> stateSeries;
    /**
     * Bar chart to view the results of the county polls
     */
    @FXML
    private StackedBarChart<String, Integer> countyBarChart;
    @FXML
    private CategoryAxis countyBarChartXAxis;
    @FXML
    private NumberAxis countyBarChartYAxis;
    @FXML
    private XYChart.Series<String, Integer> countrySeries;
    /**
     * Pie chart to view the results of the federal polls
     */
    @FXML
    private PieChart federalPieChart;
    /**
     * Pie chart to view the results of the state polls
     */
    @FXML
    private PieChart statePieChart;
    /**
     * Pie chart to view the results of the county polls
     */
    @FXML
    private PieChart countyPieChart;
    /**
     * Line chart to view the results of the federal polls
     */
    @FXML
    private LineChart federalLineChart;
    @FXML
    private CategoryAxis federalLineChartXAxis;
    @FXML
    private NumberAxis federalLineChartYAxis;
    /**
     * Line chart to view the results of the state polls
     */
    @FXML
    private LineChart stateLineChart;
    @FXML
    private CategoryAxis stateLineChartXAxis;
    @FXML
    private NumberAxis stateLineChartYAxis;
    /**
     * Line chart to view the results of the county polls
     */
    @FXML
    private LineChart countyLineChart;
    @FXML
    private CategoryAxis countyLineChartXAxis;
    @FXML
    private NumberAxis countyLineChartYAxis;

    //                                                          RadioButtons

    @FXML
    private RadioButton federalBarButton;
    @FXML
    private RadioButton federalLineButton;
    @FXML
    private RadioButton federalPieButton;
    @FXML
    private RadioButton stateBarButton;
    @FXML
    private RadioButton stateLineButton;
    @FXML
    private RadioButton statePieButton;
    @FXML
    private RadioButton countyBarButton;
    @FXML
    private RadioButton countyLineButton;
    @FXML
    private RadioButton countyPieButton;

    //                                                          Labels

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

    //                                      Variables to hold the values of the controls

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

        stateOffice.getItems().removeAll();
        stateOffice.getItems().addAll("Governor", "State Senate", "State House");

        countyState.getItems().removeAll();
        countyState.getItems().addAll("AL", "AK", "AZ", "AR", "CA",
                                      "CO", "CT", "DE", "FL", "GA",
                                      "HI", "ID", "IL", "IN", "IA",
                                      "KS", "KY", "LA", "ME", "MD",
                                      "MA", "MI", "MN", "MS", "MO",
                                      "MT", "NE", "NV", "NH", "NJ",
                                      "NM", "NY", "NC", "ND", "OH",
                                      "OK", "OR", "PA", "RI", "SC",
                                      "SD", "TN", "TX", "UT", "VT",
                                      "VA", "WA", "WV", "WI", "WY");

       // countyChoice.getItems().removeAll();
       // countyChoice.getItems().addAll();

       // countyOffice.getItems().removeAll();
       // countyOffice.getItems().addAll("County Judge", "County Sheriff");

        // set x-axis labels
        federalBarChartXAxis.setLabel("Candidate");
        federalLineChartXAxis.setLabel("Candidate");
        stateBarChartXAxis.setLabel("Candidates");
        stateLineChartXAxis.setLabel("Candidates");
        countyBarChartXAxis.setLabel("Candidate");
        countyLineChartXAxis.setLabel("Candidate");
        // set y-axis labels
        federalBarChartYAxis.setLabel("Votes");
        federalLineChartYAxis.setLabel("Votes");
        stateBarChartYAxis.setLabel("Votes");
        stateLineChartYAxis.setLabel("Votes");
        countyBarChartYAxis.setLabel("Votes");
        countyLineChartYAxis.setLabel("Votes");

        //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //  SET UP THE RANGES FOR EACH OF THE SLIDERS
        //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }


    @FXML
    private void actionEventHandler(ActionEvent event) {
        if(event.getSource()==federalYearBox) {
            federalError.setText("");
            try {
                fedYear = Integer.parseInt(federalYearBox.getText());
                federalYearSlider.adjustValue(fedYear);
            } catch (InputMismatchException ime) {
                federalError.setText("Invalid year");
            }
        } else if(event.getSource()==stateYearBox) {
            stateError.setText("");
            try {
                staYear = Integer.parseInt(stateYearBox.getText());
                countyYearSlider.adjustValue(staYear);
            } catch (InputMismatchException ime) {
                stateError.setText("Invalid year");
            }
        } else if(event.getSource()==countyYearBox) {
            countyError.setText("");
            try {
                couYear = Integer.parseInt(countyYearBox.getText());
                countyYearSlider.adjustValue(couYear);
            } catch (InputMismatchException ime) {
                countyError.setText("Invalid Year");
            }
        }
        updateGUI();
    }

    private void changeEventHandler(ChangeEvent event) {
        if(event.getSource()==federalOffice) {
            fedOffice = federalOffice.getValue().toString();
        } else if(event.getSource()==stateChoice) {
            sta = stateChoice.getValue().toString();
        } else if(event.getSource()==stateOffice) {
            staOffice = stateOffice.getValue().toString();
        } else if(event.getSource()==countyState) {
            couState = countyState.getValue().toString();
//          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//          UPDATE THE CHOICES LISTED IN THE COUNTYCHOICE COMBOBOX
//          updateCountyChoice();
//          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } else if(event.getSource()==countyChoice) {
            cou = countyChoice.getValue().toString();
        } else if(event.getSource()==countyOffice) {
            couOffice = countyOffice.getValue().toString();
        } else if(event.getSource()==federalYearSlider) {
            fedYear = (int)Math.round(federalYearSlider.getValue());
            federalYearBox.setText(Integer.toString(fedYear));
        } else if(event.getSource()==stateYearSlider) {
            staYear = (int)Math.round(stateYearSlider.getValue());
            stateYearBox.setText(Integer.toString(staYear));
        } else if(event.getSource()==countyYearSlider) {
            couYear = (int)Math.round(countyYearSlider.getValue());
            countyYearBox.setText(Integer.toString(couYear));
        }
        updateGUI();
    }

    private void updateGUI(){
        if(federal.isSelected()&&(fedOffice!=null&&fedYear!=0&&fedSlider!=0)) {
            if(federalBarButton.isSelected()) {
                federalBarChart.setVisible(true);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(false);
            } else if(federalLineButton.isSelected()) {
                federalBarChart.setVisible(false);
                federalLineChart.setVisible(true);
                federalPieChart.setVisible(false);
            } else if(federalPieButton.isSelected()) {
                federalBarChart.setVisible(false);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(true);
            }
            getSeries(fedOffice,fedYear);
//          reset series in each chart
        } else if(state.isSelected()&&(staOffice!=null&&staYear!=0&&staSlider!=0)) {
            if(stateBarButton.isSelected()) {
                stateBarChart.setVisible(true);
                stateLineChart.setVisible(false);
                statePieChart.setVisible(false);
            } else if(stateLineButton.isSelected()) {
                stateBarChart.setVisible(false);
                stateLineChart.setVisible(true);
                statePieChart.setVisible(false);
            } else if(statePieButton.isSelected()) {
                stateBarChart.setVisible(false);
                stateLineChart.setVisible(false);
                statePieChart.setVisible(true);
            }
            getSeries(staOffice,staYear);
//          reset series in each chart
        } else if(county.isSelected()&&(couOffice!=null&&couYear!=0&&couSlider!=0)) {
            if(countyBarButton.isSelected()) {
                federalBarChart.setVisible(true);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(false);
            } else if(countyLineButton.isSelected()) {
                federalBarChart.setVisible(false);
                federalLineChart.setVisible(true);
                federalPieChart.setVisible(false);
            } else if(countyPieButton.isSelected()) {
                federalBarChart.setVisible(false);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(true);
            }
            getSeries(couOffice,couYear);
//          reset series in each chart
        }
    }
    private void getSeries(String office, int year) {
        // given an office and a year, find the series of information in terms of names (x-axis) and respective number
        // of votes (y-axis)
        // Also reset the slider to hold the range of the year over which we have polls from the voters
    }
    private void updateCountyChoice() {
        // when a state is chosen from the countyState combobox, update the available counties to choose in the
        // countychoice combobox.
    }
}