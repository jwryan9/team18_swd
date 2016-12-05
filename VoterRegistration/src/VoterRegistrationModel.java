import com.firebase.client.Firebase;

/**
 * Class to hold the validate and update the voter registration information.
 */
public class VoterRegistrationModel {

    /**
     * Variable to hold reference to the database.
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Voters");

    /**
     * Method to check the user input for the voter registration app.
     * @param id    the voter's social security number.
     * @param zip   the voter's zip code.
     * @return      the error code for invalid entry, and an empty string if the input is valid.
     */
    public static String checkInput(String id, String zip, String state){

        if(!id.matches("\\d+") || id.length() != 9){
            return "Error: Invalid Social Security Number";
        }
        if(!zip.matches("\\d+") || zip.length() != 5){
            return "Error: Invalid Zip Code";
        }
        if(!ZipCode.validateZip(zip, state, "VoterRegistration/Resources/zipcodes.csv")) {
            return "Error: Invalid State/Zip Code pair";
        }

        return "";
    }

    /**
     * Method to export a voter to the database.
     * @param encryptedID   the voter's encrypted ssn.
     * @param voter         the voter to upload.
     */
    public synchronized static void exportVoter(String encryptedID, Voter voter){
        Firebase candidateRef = ref;

        candidateRef = ref.child(encryptedID);

        candidateRef.setValue(voter);

    }
}
