import com.firebase.client.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 12/2/2016.
 */
public class VotesResultsModel {
    /**
     * create a reference to the database election cycle
     */
    private static Firebase cycleReference = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    /**
     * string representation of the year of the election
     */
    private static String electionYear = "2016";

    /**
     * map holding the presidential candidates
     */
    private static Map<Integer,Candidate> presidentialCandidates = new HashMap<>();

    /**
     * map holding the presidential results
     */
    private static Map<String,Integer> presidentialResults = new HashMap<>();

    /**
     * map holding the federal senate results
     */
    private Map<String,Integer> usSenateResults = new HashMap<>();

    /**
     * map holding the federal house of representatives results
     */
    private Map<String,Integer> usHouseResults = new HashMap<>();


    /**
     * map holding the state senate candidates
     */
    private Map<String,Candidate> stateSenateCandidates = new HashMap<>();

    /**
     * map holding the state senate results
     */
    private Map<String,Integer> stateSenateResults = new HashMap<>();

    /**
     * map holding the state house of representatives candidates
     */
    private Map<String,Candidate> stateHouseCandidates = new HashMap<>();

    /**
     * map holding the state house of representatives results
     */
    private Map<String,Integer> stateHouseResults = new HashMap<>();

    /**
     * map holding the state governor candidates
     */
    private Map<String,Candidate> stateGovernorCandidates = new HashMap<>();

    /**
     * map holding the state governor results
     */
    private Map<String,Integer> stateGovernorResults = new HashMap<>();


    /**
     * map holding the county sheriff results
     */
    private Map<String,Integer> countySheriffResults = new HashMap<>();

    /**
     * map holding the county judge results
     */
    private Map<String,Integer> countyJudgeResults = new HashMap<>();



    //private Map<String,Candidate> stateCandidates = new HashMap<>();


    /**
     * map holding the possible counties
     */
    private Map<String,ArrayList<Candidate>> countyCandidates = new HashMap<>();

    /**
     * create a reference to the database
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");


    /**
     * get the possible presidential candidates from the database
     */
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


    /**
     * get the popular vote of the presidential candidates from the database
     * @param year
     */
    public synchronized static void getPresidentPopularVoteFromDatabase(String year) {

        Firebase popularRef = ref.child(year + "/Results/US President Popular Vote");
        System.out.println("popular Ref " + popularRef );
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


    /**
     * Given a state and year get the results of the states elections
     * @param state
     * @param year
     */
    public synchronized void stateResults(String state, String year) {
        this.stateSenateResults = new HashMap<>();
        this.stateHouseResults = new HashMap<>();
        this.usSenateResults = new HashMap<>();
        this.usHouseResults = new HashMap<>();
        this.stateGovernorResults = new HashMap<>();


        Firebase houseRef = ref.child(year + "/Results/" + state + "/State House");
        Firebase govRef = ref.child(year + "/Results/" + state+ "/Governor");
        Firebase senateRef = ref.child(year + "/Results/" + state + "/State Senate");

        System.out.println("house Ref " + houseRef );
        System.out.println("gov Ref " + govRef );
        System.out.println("senate Ref " + senateRef );


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


    /**
     * given a county, state, and year find the county voting info
     * @param countyName
     * @param stateAbbriviation
     * @param year
     */
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

    /**
     * get the election year cycle from the database
     */
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


    /**
     * map holding the presidential results
     * @return
     */
    public synchronized static Map<String,Integer> getPresidentialResults(){
        return presidentialResults;
    }

    /**
     * map holding the state senate results
     * @return
     */
    public Map<String,Integer> getStateSenateResults(){
        return stateSenateResults;
    }

    /**
     * map holding the state house of representatives results
     * @return
     */
    public Map<String,Integer> getStateHouseResults(){
        return stateHouseResults;
    }

    /**
     * map holding the governor results
     * @return
     */
    public Map<String,Integer> getGovernorResults(){
        return stateGovernorResults;
    }

    /**
     * map holding the county sheriff results
     * @return
     */
    public Map<String,Integer> getCountySheriffResults(){
        return countySheriffResults;
    }

    /**
     * map holding the county judge results
     * @return
     */
    public Map<String,Integer> getCountyJudgeResults(){
        return countyJudgeResults;
    }

    /**
     * map holding the federal senate results
     * @return
     */
    public Map<String,Integer> getUsSenateResults(){
        return usSenateResults;
    }

    /**
     * map holding the federal house of representatives results
     * @return
     */
    public Map<String,Integer> getUsHouseResults(){
        return usHouseResults;
    }

    /**
     * map holding the year of the election
     * @return
     */
    public static String getElectionYear(){
        return electionYear;
    }
}
