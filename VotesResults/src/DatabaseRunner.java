/**
 * Created by AdamGary on 12/4/16.
 */
public class DatabaseRunner {
    public static void main(String args[]){


        VotesResultsModel.getElectionCycleFromDatabase();

        VotesResultsModel.getPresidentialCandidatesFromDatabase();
        try {
            Thread.sleep(2000);
        }catch (Exception e){System.err.println("database runner error");}

        VotesResultsModel.getPresidentPopularVoteFromDatabase();
        //VotesResultsModel.get
        try {
            Thread.sleep(1000000000);
        }catch (Exception e){System.err.println("database runner error");}
    }
}
