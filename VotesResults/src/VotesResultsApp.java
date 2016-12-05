import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VotesResultsModel.getElectionCycleFromDatabase();

        ElectoralCollegeModel.generateElectoralCollegeModel("VotesResults/Resources/ElectoralVotesByState.csv");


        try{
            Thread.sleep(1000);
        }catch (Exception e){}
        VotesResultsModel.getPresidentPopularVoteFromDatabase(VotesResultsModel.getElectionYear());

        try{
            Thread.sleep(1000);
        }catch (Exception e){}
        //ElectoralCollegeModel.getWinnerOfEachState("2016");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("votesResults.fxml"));
        Parent root = loader.load();

        VotesResultsController controller = loader.getController();
        controller.initGUI();




        primaryStage.setTitle("!!POLLS ARE IN!!");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:VotesResults/Resources/american-flag-small.png"));

        primaryStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        primaryStage.show();
    }
    public static void main(String args[]) {launch(args);}
}
