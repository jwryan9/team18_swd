import com.firebase.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AdamGary on 12/2/16.
 */
public class VoteProcessor {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Results");

    public static Map<String,Integer> currentResults = new HashMap<>();

    public static void getStateResults(String state){






    }


    public synchronized static void addBallot(Map<String,String> ballot, String voterZip, String voterCounty, String voterState){
        System.out.println("voter state in add ballot: " + voterState);
        Firebase stateRef = ref.child(voterState);
        String position;
        String vote;

        for(String key:ballot.keySet()) {

            Firebase resultsRef = stateRef;

            position = key;
            vote = ballot.get(key);
            System.out.println("Position:" + position);
            System.out.println("Vote: " + vote);

            System.out.println("vote != 0" + !vote.equals("0"));

            if(!vote.equals("0")){
                if(key.contains("County")){
                    resultsRef = resultsRef.child(voterCounty);
                }
                String voteChildString = position + "/" + vote;

                System.out.println("voted child url: " + voteChildString);

                resultsRef = resultsRef.child(voteChildString);
                System.out.println("voted child url: " + resultsRef.toString());

                updateVoteResultsInDatabase(resultsRef);


                if(key.contains("President")){
                    Firebase presidentURL = ref.child(position + " Popular Vote/" + vote);
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
