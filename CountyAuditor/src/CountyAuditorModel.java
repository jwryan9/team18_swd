
import com.firebase.client.*;

/**
 * Class to export candidates to the database.
 */
public class CountyAuditorModel {

    /**
     * Variable to hold reference to the database.
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Candidates");

    /**
     * Method to check the user input for the candidate information.
     * @param name      the candidate's name.
     * @param zip       the candidate's zip.
     * @return          boolean if the entered data is valid.
     */
    public static String checkInput(String name, String zip){

        if(!name.matches("[a-zA-Z ']*$") || name.length() == 0){
            return "Error: Invalid Name";
        }
        if(!zip.matches("\\d+") || zip.length() != 5 || ZipCode.parseZip(zip, "zipcodes.csv") == null){
            return "Error: Invalid Zip Code";
        }

        return "";
    }

    /**
     * Method to export a candidate to the database.
     * @param id            The candidate's id number.
     * @param candidate     The reference to the candidate object.
     * @param level         The level of government that the candidate is seeking office.
     */
    public synchronized static void exportCandidate(int id, Candidate candidate, String level){
        Firebase candidateRef = ref;

        if(level == "Federal") {
            candidateRef = candidateRef.child("Federal/" + candidate.getOffice());
        }
        else if(level == "State"){
            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidate.getOffice());
        }
        else if(level == "County"){
            String[] candidateCounty = ZipCode.parseZip(candidate.getZip(), "zipcodes.csv");

            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidateCounty[0] + "/" + candidate.getOffice());
        }

        candidateRef = candidateRef.child(Integer.toString(id));

        candidateRef.setValue(candidate);

    }
}
