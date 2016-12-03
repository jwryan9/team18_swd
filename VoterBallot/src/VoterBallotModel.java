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

    private static Firebase cycleReference = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    private static String electionYear = "";

    private static boolean waitingBool = true;

    private static Map<String,ArrayList<Candidate>> federalCandidates = new HashMap<>();

    private Map<String,Candidate> stateSenateCandidates = new HashMap<>();

    private Map<String,Candidate> stateHouseCandidates = new HashMap<>();

    private Map<String,Candidate> stateGovernorCandidates = new HashMap<>();

    private Map<String,Candidate> countySheriffCandidates = new HashMap<>();

    private Map<String,Candidate> countyJudgeCandidates = new HashMap<>();


    //private Map<String,Candidate> stateCandidates = new HashMap<>();


    private Map<String,ArrayList<Candidate>> countyCandidates = new HashMap<>();

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");

    public synchronized static void initFederal() {

        String level = "Federal";
        String[] federalPositions = {"US President", "US Senate", "US House"};

        Firebase cycleRef = ref.child(electionYear + "/Candidates");

            cycleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
                System.out.println("Federal Keys " + federalCandidates.values());

                waitingBool = false;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

    }

    public synchronized void initState(String stateAbbriviation) {

        String stateURL = "State/" + stateAbbriviation;
        Firebase stateGovRef = ref.child(electionYear + "/Candidates").child(stateURL).child("Governor");
        Firebase stateHouseRef = ref.child(electionYear + "/Candidates").child(stateURL).child("State House");
        Firebase stateSenateRef = ref.child(electionYear + "/Candidates").child(stateURL).child("State Senate");

        stateGovRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> allCandidates = dataSnapshot.getChildren();

                for (DataSnapshot nextCand: allCandidates) {
                    Map<String,String> nextCandidate = nextCand.getValue(Map.class);
                    Candidate newCandidate = new Candidate(nextCandidate, Integer.parseInt(nextCand.getKey()));
                    stateGovernorCandidates.put(nextCand.getKey(), newCandidate);
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("firebase governor error");
            }
        });

        stateHouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> allCandidates = dataSnapshot.getChildren();

                for (DataSnapshot nextCand: allCandidates) {
                    Map<String,String> nextCandidate = nextCand.getValue(Map.class);
                    Candidate newCandidate = new Candidate(nextCandidate, Integer.parseInt(nextCand.getKey()));
                    stateHouseCandidates.put(nextCand.getKey(), newCandidate);
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("firebase house error");
            }
        });

        stateSenateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> allCandidates = dataSnapshot.getChildren();
                System.out.println("senate ref" + stateSenateRef.toString());

                for (DataSnapshot nextCand: allCandidates) {

                    System.out.println("next senator" + nextCand.getKey());
                    Map<String,String> nextCandidate = nextCand.getValue(Map.class);
                    Candidate newCandidate = new Candidate(nextCandidate, Integer.parseInt(nextCand.getKey()));
                    stateSenateCandidates.put(nextCand.getKey(), newCandidate);
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("firebase senate error");
            }
        });

    }



    public synchronized void initCounty(String countyName, String stateAbbriviation) {

        String stateURL = "County/" + stateAbbriviation + "/" + countyName;
        Firebase judgeRef = ref.child(electionYear + "/Candidates").child(stateURL).child("County Judge");
        Firebase sheriffRef = ref.child(electionYear + "/Candidates").child(stateURL).child("County Sheriff");

        judgeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> allCandidates = dataSnapshot.getChildren();
                System.out.println("judge ref" + judgeRef.toString());

                for (DataSnapshot nextCand: allCandidates) {

                    System.out.println("next judge " + nextCand.getKey());
                    Map<String,String> nextCandidate = nextCand.getValue(Map.class);
                    Candidate newCandidate = new Candidate(nextCandidate, Integer.parseInt(nextCand.getKey()));
                    countyJudgeCandidates.put(nextCand.getKey(), newCandidate);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("firebase judge error");
            }
        });

        sheriffRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> allCandidates = dataSnapshot.getChildren();
                System.out.println("sheriff ref" + sheriffRef.toString());

                for (DataSnapshot nextCand: allCandidates) {

                    System.out.println("next sheriff " + nextCand.getKey());
                    Map<String,String> nextCandidate = nextCand.getValue(Map.class);
                    Candidate newCandidate = new Candidate(nextCandidate, Integer.parseInt(nextCand.getKey()));
                    countySheriffCandidates.put(nextCand.getKey(), newCandidate);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("firebase sheriff error");
            }
        });

    }

    public synchronized static void getElectionCycle(){
        cycleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                electionYear = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public static Map<String, ArrayList<Candidate>> getFederalCandidates() throws InterruptedException{
        return federalCandidates;
    }

    public Map<String,Candidate> getStateSenateCandidates(){
        return stateSenateCandidates;
    }

    public Map<String,Candidate> getStateHouseCandidates(){
        return stateHouseCandidates;
    }

    public Map<String,Candidate> getGovernorCandidates(){
        return stateGovernorCandidates;
    }

    public Map<String,Candidate> getCountySheriffCandidates(){
        return countySheriffCandidates;
    }

    public Map<String,Candidate> getCountyJudgeCandidates(){
        return countyJudgeCandidates;
    }

    public static String getElectionYear(){
        return electionYear;
    }

}
