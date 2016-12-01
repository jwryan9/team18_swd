import java.util.Map;
import com.firebase.client.*;

/**
 * Created by AdamGary on 12/1/16.
 */
public class CountyAuditorModel {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Candidates");


    public static boolean checkInput(String name, String city){

        if(!name.matches("[a-zA-Z ']*$") || name.length() == 0){
            return false;
        }
        if(!city.matches("[a-zA-z ']*$") || city.length() == 0){
            return false;
        }

        return true;
    }
/*
    public static Map<Integer,Candidate> getCandidatesFromDatabase(){


    }
*/
    public static void exportCandidate(int id, Candidate candidate){
        Firebase candidateRef = ref;

        if(candidate.getOffice() == "US President") {
            candidateRef = candidateRef.child("Federal/" + candidate.getOffice());
        }
        else if(candidate.getOffice() == "State Senator") {
            candidateRef = candidateRef.child("State/" + candidate.getOffice());
        }
        else if(candidate.getOffice() == "Sheriff") {
            candidateRef = candidateRef.child("County/" + candidate.getOffice());
        }

        candidateRef = candidateRef.child(Integer.toString(id));

        candidateRef.setValue(candidate);

    }
}
