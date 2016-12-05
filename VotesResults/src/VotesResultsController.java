import com.sun.corba.se.spi.monitoring.LongMonitoredAttributeBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeEvent;

//import javax.swing.la
//import javax.swing.event.ChangeListener;

import java.awt.*;
//import java.awt.Label;
//import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.*;

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
    private Text federalYearBox;
    /**
     * TextField to enter year of state polls
     */
    @FXML
    private Text stateYearBox;
    /**
     * TextField to enter year of county polls
     */
    @FXML
    private Text countyYearBox;

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

    Map<String,Integer> results;

    private final ToggleGroup federalRadioGroup = new ToggleGroup();
    private final ToggleGroup stateRadioGroup = new ToggleGroup();
    private final ToggleGroup countyRadioGroup = new ToggleGroup();
    private VotesResultsModel vrm = new VotesResultsModel();

    public void initGUI() {

        federalBarChart.setAnimated(false);
        stateBarChart.setAnimated(false);
        countyBarChart.setAnimated(false);

        countyBarButton.setToggleGroup(countyRadioGroup);
        countyPieButton.setToggleGroup(countyRadioGroup);
        countyBarButton.setSelected(true);

        stateBarButton.setToggleGroup(stateRadioGroup);
        statePieButton.setToggleGroup(stateRadioGroup);
        stateBarButton.setSelected(true);

        federalBarButton.setToggleGroup(federalRadioGroup);
        federalPieButton.setToggleGroup(federalRadioGroup);
        federalBarButton.setSelected(true);

        federalOffice.getItems().removeAll();
        federalOffice.getItems().addAll("US President");

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
        stateOffice.getItems().addAll("US Senate", "US House", "Governor", "State Senate", "State House");

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

        countyOffice.getItems().removeAll();
        countyOffice.getItems().addAll("County Judge", "County Sheriff");

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
        Double electionYear = Double.parseDouble(VotesResultsModel.getElectionYear());

        federalYearSlider.setMax(electionYear);
        federalYearSlider.setMin(electionYear - 8);
        federalYearSlider.setMajorTickUnit(4);
        federalYearSlider.setValue(electionYear);
        federalYearSlider.setMinorTickCount(1);
        federalYearSlider.setShowTickMarks(true);
        federalYearSlider.setSnapToTicks(true);
        federalYearBox.setText(VotesResultsModel.getElectionYear());


        stateYearSlider.setMax(electionYear);
        stateYearSlider.setMin(electionYear - 8);
        stateYearSlider.setMajorTickUnit(4);
        stateYearSlider.setValue(electionYear);
        stateYearSlider.setMinorTickCount(1);
        stateYearSlider.setShowTickMarks(true);
        stateYearSlider.setSnapToTicks(true);
        stateYearBox.setText(VotesResultsModel.getElectionYear());


        countyYearSlider.setMax(electionYear);
        countyYearSlider.setMin(electionYear - 8);
        countyYearSlider.setMajorTickUnit(4);
        countyYearSlider.setValue(electionYear);
        countyYearSlider.setMinorTickCount(1);
        countyYearSlider.setShowTickMarks(true);
        countyYearSlider.setSnapToTicks(true);
        countyYearBox.setText(VotesResultsModel.getElectionYear());


        federalYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            fedYear = (int)federalYearSlider.getValue();
            federalYearBox.setText(Integer.toString(fedYear));
        });
        stateYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            staYear = (int)stateYearSlider.getValue();
            stateYearBox.setText(Integer.toString(staYear));

        });
        countyYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            couYear = (int)countyYearSlider.getValue();

            countyYearBox.setText(Integer.toString(couYear));
        });
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

    @FXML
    private void changeEventHandler(ActionEvent event) {

        System.out.println(federalYearSlider.getValue());
        if(event.getSource()==federalOffice) {
            fedOffice = federalOffice.getValue().toString();

        } else if(event.getSource()==stateChoice) {
            sta = stateChoice.getValue().toString();
            vrm.stateResults(sta, String.valueOf(staYear));
            System.out.println("Here");
            try{
                Thread.sleep(1000);
            }catch(Exception e){};
        } else if(event.getSource()==stateOffice) {
            staOffice = stateOffice.getValue().toString();

        } else if(event.getSource()==countyState) {
            couState = countyState.getValue().toString();
//          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//          UPDATE THE CHOICES LISTED IN THE COUNTYCHOICE COMBOBOX
            updateCountyChoice(countyState.getValue().toString());
//          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } else if(event.getSource()==countyChoice) {
            cou = countyChoice.getValue().toString();
            vrm.initCounty(cou, countyState.getValue().toString(), String.valueOf(couYear));
        } else if(event.getSource()==countyOffice) {
            couOffice = countyOffice.getValue().toString();
            try{
                Thread.sleep(1000);
            }catch(Exception e){};
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

    @FXML
    private void updateGUI(){
        if(federal.isSelected()&&(fedOffice!=null)) {
            if(federalBarButton.isSelected()) {
                federalBarChart.setVisible(true);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(false);
            }
            else if(federalPieButton.isSelected()) {
                federalBarChart.setVisible(false);
                federalLineChart.setVisible(false);
                federalPieChart.setVisible(true);
            }
            getSeries(fedOffice,fedYear);
//          reset series in each chart
        } else if(state.isSelected()&&(staOffice!=null)) {
            if(stateBarButton.isSelected()) {
                stateBarChart.setVisible(true);
                stateLineChart.setVisible(false);
                statePieChart.setVisible(false);
            }
            else if(statePieButton.isSelected()) {
                stateBarChart.setVisible(false);
                stateLineChart.setVisible(false);
                statePieChart.setVisible(true);
            }
            getSeries(staOffice,staYear);
//          reset series in each chart
        } else if(county.isSelected()&&(couOffice!=null)) {
            if(countyBarButton.isSelected()) {
                countyBarChart.setVisible(true);
                countyLineChart.setVisible(false);
                countyPieChart.setVisible(false);
            }
            else if(countyPieButton.isSelected()) {
                countyBarChart.setVisible(false);
                countyLineChart.setVisible(false);
                countyPieChart.setVisible(true);
            }
            getSeries(couOffice,couYear);
//          reset series in each chart
        }
    }

    private void getSeries(String office, int year) {
        Map<String, Integer > newResults = new HashMap<>();
        System.out.println("results" + results);
        // given an office and a year, find the series of information in terms of names (x-axis) and respective number
        // of votes (y-axis)
        switch (office){
            case "US President":
                newResults = VotesResultsModel.getPresidentialResults();
                break;
            case "US Senate":
                newResults = vrm.getUsSenateResults();
                break;
            case "US House":
                newResults = vrm.getUsHouseResults();
                break;
            case "State Senate":
                newResults = vrm.getStateSenateResults();
                break;
            case "State House":
                newResults = vrm.getStateHouseResults();
                break;
            case "Governor":
                newResults = vrm.getGovernorResults();
                break;
            case "County Judge":
                newResults = vrm.getCountyJudgeResults();
                break;
            case "County Sheriff":
                newResults = vrm.getCountySheriffResults();
                break;

        }

        //VotesResultsModel.stateResults("IL");
        results = newResults;
        addDataToPlots(newResults);
        // Also reset the slider to hold the range of the year over which we have polls from the voters
    }
    private void updateCountyChoice(String state) {
        // when a state is chosen from the countyState combobox, update the available counties to choose in the
        // countychoice combobox.
        countyChoice.getItems().clear();
        TreeSet<String> countyArrayList = ZipCode.parseState(state, "zipcodes.csv");



        for (String aCounty : countyArrayList){
            countyChoice.getItems().add(aCounty);
            System.out.println(aCounty);

        }

    }

    private void addDataToPlots(Map<String,Integer> results){
        federalPieChart.getData().clear();
        federalBarChart.getData().clear();
        statePieChart.getData().clear();
        stateBarChart.getData().clear();
        countyBarChart.getData().clear();
        countyPieChart.getData().clear();

        //results = VotesResultsModel.getPresidentialResults();
        System.out.println("Result keys" + results.keySet());
        System.out.println("Result values" + results.values());


        XYChart.Series series1 = new XYChart.Series();

        if (federal.isSelected()) {
            for (String key : results.keySet()) {
                String name = key;
                Integer votes = results.get(key);
                federalPieChart.getData().add(new PieChart.Data(name, votes));
                series1.getData().add(new XYChart.Data(name, votes));
            }
            federalBarChart.getData().add(series1);
        }
        else if (state.isSelected()){
            for (String key : results.keySet()) {
                String name = key;
                Integer votes = results.get(key);
                statePieChart.getData().add(new PieChart.Data(name, votes));
                series1.getData().add(new XYChart.Data(name, votes));

            }
            stateBarChart.getData().add(series1);
        }
        else{
            for (String key : results.keySet()) {
                String name = key;
                Integer votes = results.get(key);
                countyPieChart.getData().add(new PieChart.Data(name, votes));
                series1.getData().add(new XYChart.Data(name, votes));

            }
            countyBarChart.getData().add(series1);
        }

    }

}