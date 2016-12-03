import com.firebase.client.*;
import com.firebase.client.snapshot.IndexedNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by AdamGary on 12/2/16.
 */
public class VoterLoginModel{

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    private static Firebase alreadyVotedRef = new Firebase("https://votingsystem-5e175.firebaseio.com/Results/Already Voted");

    private static Map<String,String> registeredVoters = new HashMap<>();

    private static String alreadyVotedString = "";

    private static boolean isRegisteredVoter;


    public static String validateInput(String ssn, String zipCode){

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
                    System.out.println("encyptedidcheck: " +encryptedID.equals(nextVoterKey) );
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

        alreadyVotedRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                alreadyVotedString = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                alreadyVotedString = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                alreadyVotedString = dataSnapshot.getValue(String.class);
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

    public synchronized static boolean checkAlreadyVoted(String encyrptedSSN){

        if(alreadyVotedString.contains(encyrptedSSN)){
            return true;
        }

        return false;
    }


    public synchronized static void markVoterAsHasVoted(String encryptedSSN){

        Firebase voterRegisteredRef = new Firebase("https://votingsystem-5e175.firebaseio.com/Results/Already Voted");

        voterRegisteredRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData voterData) {
                if(voterData.getValue() == null){
                    voterData.setValue(encryptedSSN + ",");
                }
                else{
                    voterData.setValue(voterData.getValue() + "," + encryptedSSN);
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

    public boolean getRegisteredVoterBool(){
        return isRegisteredVoter;
    }

}
