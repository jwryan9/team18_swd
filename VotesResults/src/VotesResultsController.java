import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeSet;

//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeEvent;
//import javax.swing.la
//import javax.swing.event.ChangeListener;
//import java.awt.Label;
//import java.awt.event.ActionEvent;

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
    /**
     * X-Axis for federal polls bar chart
     */
    @FXML
    private CategoryAxis federalBarChartXAxis;
    /**
     * Y-Axis for federal polls bar chart
     */
    @FXML
    private NumberAxis federalBarChartYAxis;
    /**
     * Bar chart to view the results of the Electoral College votes
     */
    @FXML
    private StackedBarChart<String, Integer> electoralBarChart;
    /**
     * X-Axis for electoral college votes
     */
    @FXML
    CategoryAxis electoralCollegeBarChartXAxis;
    /**
     * Y-Axis for electoral college votes
     */
    @FXML
    NumberAxis electoralCollegeBarChartYAxis;
    /**
     *
     */
    @FXML
    private XYChart.Series<String, Integer> federalSeries;
    /**
     * Bar chart to view the results of the state polls
     */
    @FXML
    private StackedBarChart<String,Integer> stateBarChart;
    /**
     * X-Axis for state election results
     */
    @FXML
    private CategoryAxis stateBarChartXAxis;
    /**
     * Y-Axis for state election results
     */
    @FXML
    private NumberAxis stateBarChartYAxis;
    /**
     * Data series for state election results
     */
    @FXML
    private XYChart.Series<String, Integer> stateSeries;
    /**
     * Bar chart to view the results of the county polls
     */
    @FXML
    private StackedBarChart<String, Integer> countyBarChart;
    /**
     * X-Axis for county election results
     */
    @FXML
    private CategoryAxis countyBarChartXAxis;
    /**
     * Y-Axis for county election results
     */
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

    //                                                          RadioButtons

    /**
     * federal tab button to select bar chart
     */
    @FXML
    private RadioButton federalBarButton;
    /**
     * federal tab button to select pie chart
     */
    @FXML
    private RadioButton federalPieButton;
    /**
     * state tab button to select bar chart
     */
    @FXML
    private RadioButton stateBarButton;
    /**
     * state tab button to select pie chart
     */
    @FXML
    private RadioButton statePieButton;
    /**
     * county tab button to select bar chart
     */
    @FXML
    private RadioButton countyBarButton;
    /**
     * county tab button to select pie chart
     */
    @FXML
    private RadioButton countyPieButton;
    /**
     * federal tab button to select electoral college bar chart
     */
    @FXML
    private RadioButton electoralCollegeButton;

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

    /**
     * string to hold the text from the federal office drop-down
     */
    String fedOffice = "";
    /**
     * string to hold the text from the state choice drop-down
     */
    String sta = "";
    /**
     * string to hold the text from the state office drop-down
     */
    String staOffice = "";
    /**
     * string to hold the text from the county state choice drop-down
     */
    String couState = "";
    /**
     * string to hold the text from the county choice drop-down
     */
    String cou = "";
    /**
     * string to hold the text from the county office drop-down
     */
    String couOffice = "";
    /**
     * integer to hold the year chosen from the federal textfield
     */
    int fedYear = 0;
    /**
     * integer to hold the year chosen from the state textfield
     */
    int staYear = 0;
    /**
     * integer to hold the year chosen from the county textfield
     */
    int couYear = 0;
    /**
     * integer to hold the year chosen from the federal slider
     */
    int fedSlider = 0;
    /**
     * integer to hold the year chosen from the state slider
     */
    int staSlider = 0;
    /**
     * integer to hold the year chosen from the county slider
     */
    int couSlider = 0;

    /**
     * map to hold the results from the elections
     */
    Map<String,Integer> results;

    /**
     * create a togglegroup for the radiobuttons in the federal tab
     */
    private final ToggleGroup federalRadioGroup = new ToggleGroup();
    /**
     * create a togglegroup for the radiobuttons in the federal tab
     */
    private final ToggleGroup stateRadioGroup = new ToggleGroup();
    /**
     * create a togglegroup for the radiobuttons in the federal tab
     */
    private final ToggleGroup countyRadioGroup = new ToggleGroup();
    /**
     * create a model for this GUI
     */
    private VotesResultsModel vrm = new VotesResultsModel();

    /**
     * Initialize the GUI and its components/controls
     */
    public void initGUI() {
        couYear = Integer.parseInt(VotesResultsModel.getElectionYear());
        staYear = Integer.parseInt(VotesResultsModel.getElectionYear());
        fedYear = Integer.parseInt(VotesResultsModel.getElectionYear());
        String[] stateOptions = {"AL", "AK", "AZ", "AR", "CA",
                "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA",
                "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO",
                "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH",
                "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT",
                "VA", "WA", "WV", "WI", "WY"};
        String[] stateOptions2 = { "IL", "IN", "IA"};
        for (String state: stateOptions) {
            ElectoralCollegeModel.getWinnerOfEachState(String.valueOf(fedYear), state);

        }

        Map<String,Integer> newResults = ElectoralCollegeModel.getEcByCandidate();

System.out.println("EC SIZE: " +newResults.size());

        electoralBarChart.setVisible(false);

        federalBarChart.setAnimated(false);
        electoralBarChart.setAnimated(false);
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
        electoralCollegeButton.setToggleGroup(federalRadioGroup);
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
        electoralCollegeBarChartXAxis.setLabel("Candidate");
        stateBarChartXAxis.setLabel("Candidates");
        countyBarChartXAxis.setLabel("Candidate");
        // set y-axis labels
        federalBarChartYAxis.setLabel("Votes");
        electoralCollegeBarChartYAxis.setLabel("Electoral College Votes");
        stateBarChartYAxis.setLabel("Votes");
        countyBarChartYAxis.setLabel("Votes");
        electoralCollegeBarChartYAxis.setTickUnit(20);
        electoralCollegeBarChartYAxis.setAutoRanging(false);

        //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //  SET UP THE RANGES FOR EACH OF THE SLIDERS
        //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Double electionYear = Double.parseDouble(VotesResultsModel.getElectionYear());

        federalYearSlider.setMax(electionYear);
        federalYearSlider.setMin(electionYear - 8);
        federalYearSlider.setMajorTickUnit(4);
        federalYearSlider.setValue(electionYear);
        federalYearSlider.setMinorTickCount(0);
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

        final ListView<String> startLog = new ListView<>();
        final ListView<String> endLog   = new ListView<>();

        federalYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            fedYear = (int)federalYearSlider.getValue();
            federalYearBox.setText(Integer.toString(fedYear));
            //changeEventHandler(new ActionEvent("e",null));
        });
        federalYearSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    startLog.getItems().add("a");
                } else {
                    //changeEventHandler(federalOffice.);
                    System.out.println("Im here");
                    fedYear = (int)federalYearSlider.getValue();
                    VotesResultsModel.getPresidentPopularVoteFromDatabase(String.valueOf(fedYear));
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){}
                    updateGUI();


                }
            }
        });

        stateYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            staYear = (int)stateYearSlider.getValue();
            stateYearBox.setText(Integer.toString(staYear));

        });
        stateYearSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    startLog.getItems().add("a");
                } else {
                    System.out.println("Im here");
                    staYear = (int)stateYearSlider.getValue();
                    vrm.stateResults(stateChoice.getSelectionModel().getSelectedItem().toString(),String.valueOf(staYear));
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){}
                    updateGUI();


                }
            }
        });

        countyYearSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            couYear = (int)countyYearSlider.getValue();
            countyYearBox.setText(Integer.toString(couYear));
        });
        countyYearSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    startLog.getItems().add("a");

                } else {
                    //changeEventHandler(federalOffice.);
                    System.out.println("Im here");
                    couYear = (int)countyYearSlider.getValue();
                    vrm.initCounty(countyChoice.getSelectionModel().getSelectedItem().toString(),countyState.getSelectionModel().getSelectedItem().toString(),String.valueOf(couYear));
                    System.out.println("here 1");
                    try{
                        Thread.sleep(2000);
                    }catch(Exception e){}
                    System.out.print("here 2");
                    updateGUI();



                }
            }
        });
    }


    /**
     * handle anytime an actionevent is generated by a GUI component
     * @param event
     */
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

    /**
     * Handle the users changes to the sliders and comboboxes
     * @param event
     */
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
            }catch(Exception e){}
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

    /**
     * update the results displayed by the GUI
     */
    @FXML
    private void updateGUI(){
        if(federal.isSelected()&&(fedOffice!=null)) {
            if(federalBarButton.isSelected()) {
                federalBarChart.setVisible(true);
                electoralBarChart.setVisible(false);
                federalPieChart.setVisible(false);
            }
            else if(federalPieButton.isSelected()) {
                federalBarChart.setVisible(false);
                electoralBarChart.setVisible(false);
                federalPieChart.setVisible(true);
            }
            else if(electoralCollegeButton.isSelected()) {
                federalBarChart.setVisible(false);
                electoralBarChart.setVisible(true);
                federalPieChart.setVisible(false);
            }
            getSeries(fedOffice,fedYear);
//          reset series in each chart
        } else if(state.isSelected()&&(staOffice!=null)) {
            if(stateBarButton.isSelected()) {
                stateBarChart.setVisible(true);
                statePieChart.setVisible(false);
            }
            else if(statePieButton.isSelected()) {
                stateBarChart.setVisible(false);
                statePieChart.setVisible(true);
            }
            getSeries(staOffice,staYear);
        } else if(county.isSelected()&&(couOffice!=null)) {
            if(countyBarButton.isSelected()) {
                countyBarChart.setVisible(true);
                countyPieChart.setVisible(false);
            }
            else if(countyPieButton.isSelected()) {
                countyBarChart.setVisible(false);
                countyPieChart.setVisible(true);
            }
            getSeries(couOffice,couYear);
        }
    }

    /**
     * Get some series of data given the office and year of that data
     * @param office
     * @param year
     */
    private void getSeries(String office, int year) {
        Map<String, Integer > newResults = new HashMap<>();
        System.out.println("results" + results);
        // given an office and a year, find the series of information in terms of names (x-axis) and respective number
        // of votes (y-axis)


            switch (office) {
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

        if(electoralCollegeButton.isSelected() && federal.isSelected()){
            newResults = ElectoralCollegeModel.getEcByCandidate();
        }
        //VotesResultsModel.stateResults("IL");
        results = newResults;
        addDataToPlots(newResults);
        // Also reset the slider to hold the range of the year over which we have polls from the voters
    }

    /**
     * update the possible counties to choose from given the user's chosen state
     * @param state
     */
    private void updateCountyChoice(String state) {
        // when a state is chosen from the countyState combobox, update the available counties to choose in the
        // countychoice combobox.
        countyChoice.getItems().clear();
        TreeSet<String> countyArrayList = ZipCode.parseState(state, "VotesResults/Resources/zipcodes.csv");



        for (String aCounty : countyArrayList){
            countyChoice.getItems().add(aCounty);
            System.out.println(aCounty);

        }

    }

    /**
     * add the data taken from the database relevant to the user's choices to the charts
     * @param results
     */
    private void addDataToPlots(Map<String,Integer> results){
        federalPieChart.getData().clear();
        federalBarChart.getData().clear();
        electoralBarChart.getData().clear();
        statePieChart.getData().clear();
        stateBarChart.getData().clear();
        countyBarChart.getData().clear();
        countyPieChart.getData().clear();

        //results = VotesResultsModel.getPresidentialResults();
        System.out.println("Result keys" + results.keySet());
        System.out.println("Result values" + results.values());


        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        if (federal.isSelected()) {
            for (String key : results.keySet()) {
                String name = key;
                Integer votes = results.get(key);
                federalPieChart.getData().add(new PieChart.Data(name, votes));
                series1.getData().add(new XYChart.Data(name, votes));
                series2.getData().add(new XYChart.Data(name,votes));
            }
            federalBarChart.getData().add(series1);
            electoralBarChart.getData().add(series2);
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