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


    public static boolean checkInput(String name, String city){

        if(!name.matches("[a-zA-Z ']*$") || name.length() == 0){
            return false;
        }
        if(!city.matches("[a-zA-z ']*$") || city.length() == 0){
            return false;
        }

        return true;
    }

    public static int generateNewCandidateID(String newCandidateLevel){

        int newID = 1;

        String[] level = {"Federal","State","County"};
        Firebase levelRef = ref;
        Map<String,ArrayList<Integer>> candidateIdNumbers = new HashMap<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(String aLevel:level) {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    String idChild = dataSnapshot.child(aLevel+"/IDs").getValue(String.class);
                    System.out.println("idChild: " + idChild);

                    if(idChild!=null) {
                        String[] idArray = idChild.split(",");

                        for (String aID:idArray
                                ) {
                            ids.add(Integer.parseInt(aID));
                        }
                    }


                    candidateIdNumbers.put(aLevel, ids);
                }
                System.out.println("map: " + candidateIdNumbers);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Firebase Error");
            }
        });

        ArrayList<Integer> usedIds = candidateIdNumbers.get(newCandidateLevel);
        newID = usedIds.get(usedIds.size()-1) + 1;


        return newID;

    }

    public static void exportCandidate(int id, Candidate candidate, String level){
        Firebase candidateRef = ref;

        if(level == "Federal") {
            candidateRef = candidateRef.child("Federal/" + candidate.getOffice());
        }
        else{
            candidateRef = candidateRef.child(level + "/" + candidate.getState() + "/" + candidate.getOffice());
        }

        candidateRef = candidateRef.child(Integer.toString(id));

        candidateRef.setValue(candidate);

    }
}
