import com.firebase.client.Firebase;

/**
 * Created by AdamGary on 12/1/16.
 */
public class VoterRegistrationModel {

    /**
     * Variable to hold reference to the database.
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    public static boolean checkInput(String id, String zip){

        if(!id.matches("\\d+") || id.length() != 9){
            return false;
        }
        if(!zip.matches("\\d+") || zip.length() != 5){
            return false;
        }
        return true;
    }

    public static void exportVoter(String encryptedID, Voter voter){
        Firebase candidateRef = ref;

        candidateRef = ref.child(encryptedID);

        candidateRef.setValue(voter);

    }
}
