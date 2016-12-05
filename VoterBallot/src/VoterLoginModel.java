import com.firebase.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AdamGary on 12/2/16.
 */
public class VoterLoginModel{

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    private static Firebase alreadyVotedRef = new Firebase("https://votingsystem-5e175.firebaseio.com/");

    private static Map<String,String> registeredVoters = new HashMap<>();

    private static String alreadyVotedString = "";

    private static boolean isRegisteredVoter;

    private static String electionCycle = "";


    public static String validateInput(String ssn, String zipCode) {

        if(ssn.length() != 9 && !ssn.matches("\\d+")){
            return "Error: Invalid Social Security Number";
        }
        if(zipCode.length() != 5 && !zipCode.matches("\\d+")) {
            return "Error: Invalid Zip Code";
        }
        return "";
    }

    public synchronized static boolean checkVoterRegistration(String encryptedID, String zipCode) {

        isRegisteredVoter = false;

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> allVoters = dataSnapshot.getChildren();

                for(DataSnapshot nextVoter: allVoters){

                    String nextVoterKey = nextVoter.getKey();
                    String nextVoterZip = nextVoter.child("zipCode").getValue(String.class);

                    System.out.println("next ID: " + nextVoterKey + " next Zip: " + nextVoterZip);
                    System.out.println("encypted id check: " +encryptedID.equals(nextVoterKey) );
                    System.out.println("zipcheck: " +  zipCode.equals(nextVoterZip));

                    if(encryptedID.equals(nextVoterKey) && zipCode.equals(nextVoterZip)) {
                        System.out.println("true, voter found");
                        isRegisteredVoter = true;
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });


        System.out.println("Leaving voter check");

        return isRegisteredVoter;

    }

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

    public synchronized static void getAlreadyVotedFromDatabase(){
        //System.err.println("Ref: " + ref);

        String year = VoterBallotModel.getElectionYear();
        System.err.println("Year in get voted: " + year);

        alreadyVotedRef = alreadyVotedRef.child(year).child("Results/Already Voted");
        //System.err.println("alreadyRef: " + alreadyVotedRef);

        alreadyVotedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alreadyVotedString = dataSnapshot.getValue(String.class);
                System.err.println("Already Voted String1234: " + alreadyVotedString);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public synchronized static boolean checkAlreadyVoted(String encyrptedSSN){

        //getAlreadyVotedFromDatabase();
        System.err.println(alreadyVotedString);
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


    public synchronized static void markVoterAsHasVoted(String encryptedSSN){

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

    public static String getAlreadyVotedString(){return alreadyVotedString;}
    public boolean getRegisteredVoterBool(){
        return isRegisteredVoter;
    }

}
