import com.firebase.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Model for voter login application
 */
public class VoterLoginModel{

    /**
     * Reference to database registered voters
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    /**
     * Refernce to database voters who have already voted
     */
    private static Firebase alreadyVotedRef = new Firebase("https://votingsystem-5e175.firebaseio.com/");

    /**
     * Map of registered voter
     */
    private static Map<String,String> registeredVoters = new HashMap<>();

    /**
     * String of voters who have previously voted
     */
    private static String alreadyVotedString = "";

    /**
     * boolean variable for checking if voter is registered
     */
    private static boolean isRegisteredVoter;

    /**
     * Election year
     */
    private static String electionCycle = "";

    /**
     * Validates user social security number and zip code inputs
     *
     * @param ssn user social security number
     * @param zipCode user zip code
     * @return empty string if valid, error message if invalid
     */
    public static String validateInput(String ssn, String zipCode) {

        if(ssn.length() != 9 && !ssn.matches("\\d+")){
            return "Error: Invalid Social Security Number";
        }
        if(zipCode.length() != 5 && !zipCode.matches("\\d+")) {
            return "Error: Invalid Zip Code";
        }
        return "";
    }

    /**
     * Synchronized method for getting voters from database
     */
    public synchronized static void getVotersFromDatabase(){

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                registeredVoters.put(dataSnapshot.getKey(),dataSnapshot.child("zipCode").getValue(String.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                registeredVoters.replace(dataSnapshot.getKey(),dataSnapshot.child("zipCode").getValue(String.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                registeredVoters.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

    }

    /**
     * Synchronized method for checking if voters are registered
     *
     * @param encryptedID encrypted social security number
     * @param zipCode voter zip code
     * @return true if voter is registered, otherwise false
     */
    public synchronized static boolean checkVoterRegistrationQuery(String encryptedID, String zipCode) {

        if(registeredVoters.containsKey(encryptedID)){
            if(registeredVoters.get(encryptedID).equals(zipCode)){
                System.out.println("true, voter found");
                isRegisteredVoter = true;
            }
        }
        else{
            System.out.println("false, voter not found");
            isRegisteredVoter = false;
        }

        return isRegisteredVoter;
    }

    /**
     * Synchronized method for getting list of people who have voted previously voted in current election
     */
    public synchronized static void getAlreadyVotedFromDatabase(){

        String year = VoterBallotModel.getElectionYear();
        System.err.println("Year in get voted: " + year);

        try{
            Thread.sleep(1000);
        }catch (Exception e){}

        alreadyVotedRef = alreadyVotedRef.child(year).child("Results/Already Voted");

        alreadyVotedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alreadyVotedString += dataSnapshot.getValue(String.class);
                System.err.println("Already Voted String1234: " + alreadyVotedString);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    /**
     * Synchronized method for checking if voter has voted previously in current election
     *
     * @param encyrptedSSN voter's encrypted social security number
     * @return true  if voter has already voted, else false
     */
    public synchronized static boolean checkAlreadyVoted(String encyrptedSSN){
        System.err.println("voted error already voted string:" +alreadyVotedString);
        try {
            System.out.println(".contains ssn: " + alreadyVotedString.contains(encyrptedSSN));

            if (alreadyVotedString.contains(encyrptedSSN)) {
                System.out.println("return true");
                return true;
            }
        } catch (NullPointerException ex){
            System.err.println("Null pointer exception");
        }
        return false;
    }

    /**
     * Synchronized method to add voter to has voted list in database
     *
     * @param encryptedSSN encrypted social security number of voter
     */
    public synchronized static void markVoterAsHasVoted(String encryptedSSN){
        alreadyVotedString = alreadyVotedString + "," + encryptedSSN;
        String url = "https://votingsystem-5e175.firebaseio.com/" + VoterBallotModel.getElectionYear() + "/Results/Already Voted";
        Firebase voterRegisteredRef = new Firebase(url);

        voterRegisteredRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData voterData) {
                if(voterData.getValue() == null){
                    voterData.setValue(encryptedSSN + ",");
                }
                else{
                    voterData.setValue(voterData.getValue() + encryptedSSN + ",");
                }
                System.out.println("voterData.getvale " + voterData.getValue());

                return Transaction.success(voterData);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
                if (firebaseError != null) {
                    System.out.println("set voter to has voted failed.");
                } else {
                    System.out.println("set voter to has voted succeeded.");
                }
            }
        });


    }

    /**
     * Getter method for already voted string
     *
     * @return String of voters who have already voted in the election
     */
    public static String getAlreadyVotedString(){return alreadyVotedString;}

    /**
     * Getter method for isRegisteredVoter boolean
     *
     * @return boolean representing if voter is registered
     */
    public boolean getRegisteredVoterBool(){
        return isRegisteredVoter;
    }

}
