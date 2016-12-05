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

    private Map<String,Integer> usSenateResults = new HashMap<>();

    private Map<String,Integer> usHouseResults = new HashMap<>();


    private Map<String,Candidate> stateSenateCandidates = new HashMap<>();

    private Map<String,Integer> stateSenateResults = new HashMap<>();

    private Map<String,Candidate> stateHouseCandidates = new HashMap<>();

    private Map<String,Integer> stateHouseResults = new HashMap<>();

    private Map<String,Candidate> stateGovernorCandidates = new HashMap<>();

    private Map<String,Integer> stateGovernorResults = new HashMap<>();


    private Map<String,Integer> countySheriffResults = new HashMap<>();

    private Map<String,Integer> countyJudgeResults = new HashMap<>();


    //private Map<String,Candidate> stateCandidates = new HashMap<>();


    private Map<String,ArrayList<Candidate>> countyCandidates = new HashMap<>();

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");


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


    public synchronized static void getPresidentPopularVoteFromDatabase(String year) {

        Firebase popularRef = ref.child(year + "/Results/US President Popular Vote");

        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                //System.out.println("datasnapshot " + dataSnapshot.getValue());


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());
                try{
                    for (String nextKey : idNumberToVotesMap.keySet()) {
                        //int key = Integer.parseInt(nextKey);
                        //System.out.println("id map " + key + " " + idNumberToVotesMap.get(nextKey));

                        //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                        //Candidate currCand = presidentialCandidates.get(key);
                        //currCand.setVotes(idNumberToVotesMap.get(nextKey));
//                    System.out.println("candidate map: " + key + " " + presidentialCandidates.get(key).getName());


                        presidentialResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + presidentialResults.keySet());
                        System.out.println("results map values " + presidentialResults.values());

                    }

                }catch (NullPointerException e) {
                    System.out.println("null pt exception");
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });


    }


    public synchronized void stateResults(String state, String year) {
        this.stateSenateResults = new HashMap<>();
        this.stateHouseResults = new HashMap<>();
        this.usSenateResults = new HashMap<>();
        this.usHouseResults = new HashMap<>();
        this.stateGovernorResults = new HashMap<>();


        Firebase houseRef = ref.child(year + "/Results/" + state + "/State House");
        Firebase govRef = ref.child(year + "/Results/" + state+ "/Governor");
        Firebase senateRef = ref.child(year + "/Results/" + state + "/State Senate");

        houseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                try{

                    for (String nextKey : idNumberToVotesMap.keySet()) {


                        stateHouseResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + stateHouseResults.keySet());
                        System.out.println("results map values " + stateHouseResults.values());

                    }

                }catch (NullPointerException e) {
                    stateHouseResults.put("No Results", 0);

                    System.out.println("null pt exception");
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

                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                try{
                    for (String nextKey : idNumberToVotesMap.keySet()) {

                        stateGovernorResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("gov results map keys " + stateGovernorResults.keySet());
                        System.out.println("gov results map values " + stateGovernorResults.values());

                    }

                }catch (NullPointerException e) {
                    stateGovernorResults.put("No Results", 0);

                    System.out.println("null pt exception");
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
                try {
                    for (String nextKey : idNumberToVotesMap.keySet()) {
                       // int key = Integer.parseInt(nextKey);
                        //System.out.println("id map " + key + " " + idNumberToVotesMap.get(nextKey));

                        //System.out.println("next Key " + key + " " +presidentialCandidates.containsKey(key));
                        //Candidate currCand = presidentialCandidates.get(key);
                        //currCand.setVotes(idNumberToVotesMap.get(nextKey));
//                    System.out.println("candidate map: " + key + " " + stateSenateCandidates.get(key).getName());


                        stateSenateResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + stateSenateResults.keySet());
                        System.out.println("results map values " + stateSenateResults.values());

                    }
                } catch (NullPointerException e){
                    stateSenateResults.put("No Results", 0);

                    System.out.println("null pt exception");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

        Firebase usHouseRef = ref.child(year + "/Results/" + state + "/US House");
        Firebase usSenateRef = ref.child(year + "/Results/" + state + "/US Senate");


        usHouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();


                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                try{
                for (String nextKey : idNumberToVotesMap.keySet()) {


                        usHouseResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + usHouseResults.keySet());
                        System.out.println("results map values " + usHouseResults.values());

                    }
                }catch (NullPointerException e){
                    usHouseResults.put("No Results", 0);

                    System.out.println("null pt exception");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

        usSenateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                //System.out.println("ID map keys: " + idNumberToVotesMap.values());
                try{
                    for (String nextKey : idNumberToVotesMap.keySet()) {


                        usSenateResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + usSenateResults.keySet());
                        System.out.println("results map values " + usSenateResults.values());

                    }
                }catch (NullPointerException e){
                    usSenateResults.put("No Results", 0);

                    System.out.println("null pt exception");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });
    }



    public synchronized void initCounty(String countyName, String stateAbbriviation, String year) {
        countyJudgeResults = new HashMap<>();
        countySheriffResults = new HashMap<>();

        String stateURL = stateAbbriviation + "/" + countyName;
        Firebase judgeRef = ref.child(year + "/Results").child(stateURL).child("County Judge");
        Firebase sheriffRef = ref.child(year + "/Results").child(stateURL).child("County Sheriff");
        System.out.println(judgeRef);

        judgeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                try{
                    for (String nextKey : idNumberToVotesMap.keySet()) {


                        countyJudgeResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + countyJudgeResults.keySet());
                        System.out.println("results map values " + countyJudgeResults.values());

                    }
                }catch (NullPointerException e){
                    countyJudgeResults.put("No Results", 0);

                    System.out.println("null pt exception");
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
                Map<String,Integer> idNumberToVotesMap = new HashMap<>();

                idNumberToVotesMap = dataSnapshot.getValue(HashMap.class);

                try{
                    for (String nextKey : idNumberToVotesMap.keySet()) {


                        countySheriffResults.put(nextKey, idNumberToVotesMap.get(nextKey));

                        System.out.println("results map keys " + countySheriffResults.keySet());
                        System.out.println("results map values " + countySheriffResults.values());

                    }
                }catch (NullPointerException e){
                    countySheriffResults.put("No Results", 0);

                    System.out.println("null pt exception");
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

    public Map<String,Integer> getStateSenateResults(){
        return stateSenateResults;
    }

    public Map<String,Integer> getStateHouseResults(){
        return stateHouseResults;
    }

    public Map<String,Integer> getGovernorResults(){
        return stateGovernorResults;
    }

    public Map<String,Integer> getCountySheriffResults(){
        return countySheriffResults;
    }

    public Map<String,Integer> getCountyJudgeResults(){
        return countyJudgeResults;
    }

    public Map<String,Integer> getUsSenateResults(){
        return usSenateResults;
    }

    public Map<String,Integer> getUsHouseResults(){
        return usHouseResults;
    }

    public static String getElectionYear(){
        return electionYear;
    }



}
