import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.firebase.client.*;

/**
 * Created by AdamGary on 12/1/16.
 */
public class VoterBallotModel {

    private static boolean waitingBool = true;

    private static Map<String,ArrayList<Candidate>> federalCandidates = new HashMap<>();

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Candidates");

    public synchronized static void initFederal() {

            String level = "Federal";

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] federalPositions = {"US President", "US Senate", "US House"};
                for(int i = 0; i < federalPositions.length; i++){
                    ArrayList<Candidate> candidateArrayList = new ArrayList<>();
                    Iterable<DataSnapshot> allCandidates = dataSnapshot.child(level + "/" + federalPositions[i]).getChildren();

                    for(DataSnapshot nextCand: allCandidates){
                        int idNumber = Integer.parseInt(nextCand.getKey());
                        Map<String,String> nextCandidate = nextCand.getValue(Map.class);

                        Candidate newCandidate = new Candidate(nextCandidate, idNumber);
                        candidateArrayList.add(newCandidate);
                    }

                    federalCandidates.put(federalPositions[i],candidateArrayList);
                    //System.out.println("Federal Keys " + federalCandidates.keySet());

                    System.out.println("position:" + federalPositions[i] + " size: " + federalCandidates.get(federalPositions[i]).size());

                }
                System.out.println("Federal Keys " + federalCandidates.keySet());
                waitingBool = false;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });


    }

    public synchronized static void initState(String stateAbbriviation) {

    }

    public synchronized static void initCounty(String county) {



    }


    public static Map<String, ArrayList<Candidate>> getFederalCandidates() throws InterruptedException{

        return federalCandidates;
    }


}
