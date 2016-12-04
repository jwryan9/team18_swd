import com.fasterxml.jackson.databind.util.JSONPObject;
import com.firebase.client.*;
import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsModel {


    private static Firebase cycleReference = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    private static String electionYear = "2016";

    private static Map<Integer,Candidate> presidentialCandidates = new HashMap<>();

    private static Map<String,Integer> presidentialResults = new HashMap<>();

    private static Map<String,Integer> usSenateResults = new HashMap<>();

    private static Map<String,Integer> usHouseResults = new HashMap<>();


    private static Map<String,Candidate> stateSenateCandidates = new HashMap<>();

    private static Map<String,Integer> stateSenateResults = new HashMap<>();

    private static Map<String,Candidate> stateHouseCandidates = new HashMap<>();

    private static Map<String,Integer> stateHouseResults = new HashMap<>();

    private static Map<String,Candidate> stateGovernorCandidates = new HashMap<>();

    private static Map<String,Integer> stateGovernorResults = new HashMap<>();


    private static Map<String,Candidate> countySheriffCandidates = new HashMap<>();

    private static Map<String,Candidate> countyJudgeCandidates = new HashMap<>();


    //private Map<String,Candidate> stateCandidates = new HashMap<>();


    private Map<String,ArrayList<Candidate>> countyCandidates = new HashMap<>();

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");


    public synchronized static void getAllCandidates(){

        Firebase cycleRef = ref.child(electionYear + "/Results");

        cycleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                int idNumber = Integer.parseInt(dataSnapshot.getKey());
                Map<String,HashMap<String,String>> nextCandidate = dataSnapshot.getValue(HashMap.class);
                 //nextCandidate = dataSnapshot.getValue(HashMap.class);

                System.out.println("Next candidate: " + nextCandidate.keySet());
                //Candidate newCandidate = new Candidate(nextCandidate, idNumber);

                //presidentialCandidates.put(idNumber,newCandidate);

                //System.out.println("Pres results keys " + presidentialCandidates.keySet());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public synchronized static void getPresidentialCandidatesFromDatabase(){

        Firebase cycleRef = ref.child(electionYear + "/Candidates/Federal/US President");

        cycleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int idNumber = Integer.parseInt(dataSnapshot.getKey());
                Map<String,String> nextCandidate = dataSnapshot.getValue(Map.class);

                Candidate newCandidate = new Candidate(nextCandidate, idNumber);

                presidentialCandidates.put(idNumber,newCandidate);

                System.out.println("Pres results keys " + presidentialCandidates.keySet());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public synchronized static void getPresidentPopularVoteFromDatabase() {

        Firebase popularRef = ref.child(electionYear + "/Results/US President Popular Vote");

        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                //System.out.println("datasnapshot " + dataSnapshot.getValue());


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());

                for (String nextKey: idNumberToVotesMap.keySet()) {
                    int key = Integer.parseInt(nextKey);
                    System.out.println("id map " + key + " " + idNumberToVotesMap.get(nextKey));

                    //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                    //Candidate currCand = presidentialCandidates.get(key);
                    //currCand.setVotes(idNumberToVotesMap.get(nextKey));
                    System.out.println("candidate map: " + key + " " + presidentialCandidates.get(key).getName());


                    presidentialResults.put(nextKey,idNumberToVotesMap.get(nextKey));

                    System.out.println("results map keys " + presidentialResults.keySet());
                    System.out.println("results map values " + presidentialResults.values());

                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });


    }

    public synchronized static void getFederalCandidatesFromDatabase(){

        Firebase senateRef = ref.child(electionYear + "/Candidates/Federal/US Senate");
        Firebase houseRef = ref.child(electionYear + "/Candidates/Federal/House");

        senateRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int idNumber = Integer.parseInt(dataSnapshot.getKey());
                Map<String,String> nextCandidate = dataSnapshot.getValue(Map.class);

                Candidate newCandidate = new Candidate(nextCandidate, idNumber);

                presidentialCandidates.put(idNumber,newCandidate);

                System.out.println("Pres results keys " + presidentialCandidates.keySet());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public synchronized static void stateResults(String state) {


        Firebase houseRef = ref.child(electionYear + "/Results/" + state + "/State House");
        Firebase govRef = ref.child(electionYear + "/Results/" + state+ "/Governor");
        Firebase senateRef = ref.child(electionYear + "/Results/" + state + "/State Senate");

        houseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                //System.out.println("datasnapshot " + dataSnapshot.getValue());


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());

                for (String nextKey: idNumberToVotesMap.keySet()) {
                    int key = Integer.parseInt(nextKey);
                    System.out.println("id map " + key + " " + idNumberToVotesMap.get(nextKey));

                    //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                    //Candidate currCand = presidentialCandidates.get(key);
                    //currCand.setVotes(idNumberToVotesMap.get(nextKey));
                    System.out.println("candidate map: " + key + " " + stateHouseCandidates.get(key).getName());


                    stateHouseResults.put(stateHouseCandidates.get(key).getName(),idNumberToVotesMap.get(nextKey));

                    System.out.println("results map keys " + stateHouseResults.keySet());
                    System.out.println("results map values " + stateHouseResults.values());

                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

        govRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                //System.out.println("datasnapshot " + dataSnapshot.getValue());


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());

                for (String nextKey: idNumberToVotesMap.keySet()) {
                    int key = Integer.parseInt(nextKey);
                    System.out.println("gov id map " + key + " " + idNumberToVotesMap.get(nextKey));

                    //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                    //Candidate currCand = presidentialCandidates.get(key);
                    //currCand.setVotes(idNumberToVotesMap.get(nextKey));
                    System.out.println("gov candidate map: " + key + " " + stateGovernorCandidates.get(key).getName());


                    stateGovernorResults.put(stateGovernorCandidates.get(key).getName(),idNumberToVotesMap.get(nextKey));

                    System.out.println("gov results map keys " + presidentialResults.keySet());
                    System.out.println("gov results map values " + presidentialResults.values());

                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

        senateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                //System.out.println("datasnapshot " + dataSnapshot.getValue());


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());

                for (String nextKey: idNumberToVotesMap.keySet()) {
                    int key = Integer.parseInt(nextKey);
                    System.out.println("id map " + key + " " + idNumberToVotesMap.get(nextKey));

                    //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                    //Candidate currCand = presidentialCandidates.get(key);
                    //currCand.setVotes(idNumberToVotesMap.get(nextKey));
                    System.out.println("candidate map: " + key + " " + stateSenateCandidates.get(key).getName());


                    stateSenateResults.put(stateSenateCandidates.get(key).getName(),idNumberToVotesMap.get(nextKey));

                    System.out.println("results map keys " + stateSenateResults.keySet());
                    System.out.println("results map values " + stateSenateResults.values());

                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });
    }

    public synchronized void getStateCandidatesFromDatabase(String stateAbbriviation) {

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

    public synchronized static void getElectionCycleFromDatabase(){
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


    public synchronized static Map<String,Integer> getPresidentialResults(){
        return presidentialResults;
    }

    public Map<String,Integer> getStateSenateCandidates(){
        return stateSenateResults;
    }

    public Map<String,Integer> getStateHouseResults(){
        return stateHouseResults;
    }

    public Map<String,Integer> getGovernorResults(){
        return stateGovernorResults;
    }

    public static Map<String,Candidate> getCountySheriffResults(){
        return countySheriffCandidates;
    }

    public static Map<String,Candidate> getCountyJudgeResults(){
        return countyJudgeCandidates;
    }

    public static Map<String,Integer> getUsSenateResults(){
        return usSenateResults;
    }

    public static Map<String,Integer> getUsHouseResults(){
        return usHouseResults;
    }

    public static String getElectionYear(){
        return electionYear;
    }



}
