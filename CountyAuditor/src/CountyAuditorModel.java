import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.firebase.client.*;
import com.firebase.client.DataSnapshot;
/**
 * Created by AdamGary on 12/1/16.
 */
public class CountyAuditorModel {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Candidates");


    public static boolean checkInput(String name, String zip){

        if(!name.matches("[a-zA-Z ']*$") || name.length() == 0){
            return false;
        }
        if(!zip.matches("\\d+") || zip.length() != 5){
            return false;
        }

        return true;
    }


    public static void exportCandidate(int id, Candidate candidate, String level){
        Firebase candidateRef = ref;

        if(level == "Federal") {
            candidateRef = candidateRef.child("Federal/" + candidate.getOffice());
        }
        else if(level == "State"){
            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidate.getOffice());
        }
        else if(level == "County"){
            candidateRef = candidateRef.child(level + "/" + candidate.getZip() + "/" + candidate.getOffice());
        }

        candidateRef = candidateRef.child(Integer.toString(id));

        candidateRef.setValue(candidate);

    }
}
