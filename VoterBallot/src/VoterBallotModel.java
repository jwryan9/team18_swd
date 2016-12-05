import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Model for voter ballot application
 */
public class VoterBallotModel {

    /**
     * Reference to database
     */
    private static Firebase cycleReference = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    /**
     * Stores current election year
     */
    private static String electionYear = "";

    /**
     * Map for storing election candidates for federal positions
     */
    private static Map<String,ArrayList<Candidate>> federalCandidates = new HashMap<>();

    /**
     * Map for storing election candidates for state senate positions
     */
    private Map<String,Candidate> stateSenateCandidates = new HashMap<>();

    /**
     * Map for storing election candidates for state house positions
     */
    private Map<String,Candidate> stateHouseCandidates = new HashMap<>();

    /**
     * Map for storing candidates for governor position
     */
    private Map<String,Candidate> stateGovernorCandidates = new HashMap<>();

    /**
     * Map for storing candidates for county sheriff position
     */
    private Map<String,Candidate> countySheriffCandidates = new HashMap<>();

    /**
     * Map for storing candidates for county judge position
     */
    private Map<String,Candidate> countyJudgeCandidates = new HashMap<>();

    private Map<String,ArrayList<Candidate>> countyCandidates = new HashMap<>();

    /**
     * Reference to database
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com");

    /**
     * Synchronized method for initializing the list of federal candidates on the ballot
     */
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
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

    }

    /**
     * Synchronized method for initializing the list of state candidates on the ballot
     *
     * @param stateAbbriviation abbreviated voter state name
     */
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

    /**
     * Synchronized method for initializing the list of county candidates on the ballot
     *
     * @param countyName name of voter county
     * @param stateAbbriviation abbreviated voter state name
     */
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

    /**
     * Synchronized method for getting election cycle year from database
     */
    public synchronized static void getElectionCycle(){
        cycleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                electionYear = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }

    /**
     * Getter method for federal candidates
     *
     * @return Map with list of federal candidates
     */
    public static Map<String, ArrayList<Candidate>> getFederalCandidates() {
        return federalCandidates;
    }

    /**
     * Getter method for state senate candidates
     *
     * @return Map with list of state senate candidates
     */
    public Map<String,Candidate> getStateSenateCandidates(){
        return stateSenateCandidates;
    }

    /**
     * Getter methdo for state house candidates
     *
     * @return Map with list of state house candidates
     */
    public Map<String,Candidate> getStateHouseCandidates(){
        return stateHouseCandidates;
    }

    /**
     * Getter method for governor candidates
     *
     * @return Map with list of governor candidates
     */
    public Map<String,Candidate> getGovernorCandidates(){
        return stateGovernorCandidates;
    }

    /**
     * Getter method for county sheriff candidates
     *
     * @return Map with list of county sheriff candidates
     */
    public Map<String,Candidate> getCountySheriffCandidates(){
        return countySheriffCandidates;
    }

    /**
     * Getter method for county judge candidates
     *
     * @return Map with list of county judge candidates
     */
    public Map<String,Candidate> getCountyJudgeCandidates(){
        return countyJudgeCandidates;
    }

    /**
     * Getter method for election year
     *
     * @return current election year
     */
    public static String getElectionYear(){
        return electionYear;
    }

}
