import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AdamGary on 12/2/16.
 */
public class VoterLoginModel {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    private static Map<String,String> registeredVoters = new HashMap<>();

    private static boolean isRegisteredVoter;

    public static boolean validateInput(String ssn, String zipCode){
        if(zipCode.length() == 5 && zipCode.matches("\\d+") && ssn.length() == 9 && ssn.matches("\\d+")) {
            return true;
        }
        return false;
    }

    public static boolean checkVoterRegistration(String encryptedID, String zipCode) {

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


}
