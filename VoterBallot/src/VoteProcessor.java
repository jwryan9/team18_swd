import com.firebase.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for processing votes, and submitting the votes to data base
 */
public class VoteProcessor {

    /**
     * Reference to Firebase database
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");

    /**
     * Map for holding current election results
     */
    public static Map<String,Integer> currentResults = new HashMap<>();

    /**
     * Year of current election
     */
    private static String electionYear = "";

    public static void getStateResults(String state){}


    public synchronized static void addBallot(Map<String,String> ballot, String voterZip, String voterCounty, String voterState){
        String electionYear = VoterBallotModel.getElectionYear();
        System.out.println("voter state in add ballot: " + voterState);
        Firebase stateRef = ref.child(electionYear + "/Results").child(voterState);
        String position;
        String vote;

        for(String key:ballot.keySet()) {

            Firebase resultsRef = stateRef;

            position = key;
            vote = ballot.get(key);
            System.out.println("Position:" + position);
            System.out.println("Vote: " + vote);

            System.out.println("vote != 0" + !vote.equals("0"));

            if(!vote.equals("")){
                if(key.contains("County")){
                    resultsRef = resultsRef.child(voterCounty);
                }
                String voteChildString = position + "/" + vote;

                System.out.println("voted child url: " + voteChildString);

                resultsRef = resultsRef.child(voteChildString);
                System.out.println("voted child url: " + resultsRef.toString());

                updateVoteResultsInDatabase(resultsRef);


                if(key.contains("President")){
                    Firebase presidentURL = ref.child(electionYear + "/Results/" + position + " Popular Vote/" + vote);
                    updateVoteResultsInDatabase(presidentURL);
                }
            }
        }
    }




    public synchronized static void updateVoteResultsInDatabase(Firebase uploadURL){
        uploadURL.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData databaseResults) {
                if(databaseResults.getValue() == null){
                    System.out.println("starting at 1");
                    databaseResults.setValue(1);
                }
                else{
                    System.out.println("adding 1");
                    databaseResults.setValue((Long) databaseResults.getValue() + 1);
                }
                return Transaction.success(databaseResults);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
                if(firebaseError != null){
                    System.out.println("Increment failed.");
                }
                else{
                    System.out.println("Increment Succeeded");
                }
            }
        });

    }


}
