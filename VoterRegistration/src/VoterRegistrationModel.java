import com.firebase.client.Firebase;

/**
 * Created by AdamGary on 12/1/16.
 */
public class VoterRegistrationModel {

    /**
     * Variable to hold reference to the database.
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    public static String checkInput(String id, String zip){

        if(!id.matches("\\d+") || id.length() != 9){
            return "Error: Invalid Social Security Number";
        }
        if(!zip.matches("\\d+") || zip.length() != 5){
            return "Error: Invalid Zip Code";
        }
        return "";
    }

    public synchronized static void exportVoter(String encryptedID, Voter voter){
        Firebase candidateRef = ref;

        candidateRef = ref.child(encryptedID);

        candidateRef.setValue(voter);

    }
}
