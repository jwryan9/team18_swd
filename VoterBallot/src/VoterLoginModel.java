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

    private static Map<String,String> registeredVoters = new HashMap<>();

    private static boolean isRegisteredVoter;


    public static String validateInput(String ssn, String zipCode){

        if(ssn.length() == 9 && ssn.matches("\\d+")){
            return "Error: Invalid Social Security Number";
        }
        if(zipCode.length() == 5 && zipCode.matches("\\d+")) {
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

    /*
    public void run(){
        checkVoterRegistration(id,zip);
    }
*/
    public boolean getRegisteredVoterBool(){
        return isRegisteredVoter;
    }
}
