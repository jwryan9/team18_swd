import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Class to export candidates to the database.
 */
public class CountyAuditorModel {

    /**
     * Stores current election year
     */
    private static String electionYear = "";

    /**
     * Reverence to election cycle in database
     */
    private static Firebase cycleReference = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    /**
     * Variable to hold reference to the database.
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/");

    /**
     * Method to check the user input for the candidate information.
     *
     * @param name      the candidate's name.
     * @param zip       the candidate's zip.
     * @param state     the candidate's state.
     * @return          boolean if the entered data is valid.
     */
    public static String checkInput(String name, String zip, String state){

        if(!name.matches("[a-zA-Z ']*$") || name.length() == 0){
            return "Error: Invalid Name";
        }
        if(!zip.matches("\\d+") || zip.length() != 5 || ZipCode.parseZip(zip, "Resources/zipcodes.csv") == null){
            return "Error: Invalid Zip Code";
        }
        if(!ZipCode.validateZip(zip, state, "Resources/zipcodes.csv")) {
            return "Error: Invalid State/Zip Code Pair";
        }

        return "";
    }

    /**
     * Method to export a candidate to the database.
     *
     * @param id            The candidate's id number.
     * @param candidate     The reference to the candidate object.
     * @param level         The level of government that the candidate is seeking office.
     */
    public synchronized static void exportCandidate(int id, Candidate candidate, String level){

        Firebase candidateRef = ref.child(electionYear).child("Candidates");

        if(level == "Federal") {
            candidateRef = candidateRef.child("Federal/" + candidate.getOffice());
        }
        else if(level == "State"){
            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidate.getOffice());
        }
        else if(level == "County"){
            String[] candidateCounty = ZipCode.parseZip(candidate.getZip(), "Resources/zipcodes.csv");

            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidateCounty[0] + "/" + candidate.getOffice());
        }

        candidateRef = candidateRef.child(Integer.toString(id));

        candidateRef.setValue(candidate);

    }

    /**
     * Method to get the current election cycle year from the database and update the instance variable.
     */
    public synchronized static void getElectionCycle(){
        cycleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                electionYear = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
