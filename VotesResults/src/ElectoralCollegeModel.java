import com.firebase.client.*;
import com.firebase.client.core.view.filter.ChildChangeAccumulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Electoral college votes source: mathforum.org/workshops/sum96/data.collections/datalibrary/ElectoralPopArea.xls
 * Via Source:  U.S. Department of State (Oct 2005) and 2000 census
 */
public class ElectoralCollegeModel {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/");

    private static Map<String, Integer> ecByState = new HashMap<>();

    private static Map<String, Integer> ecByCandidate = new HashMap<>();

    public static void generateElectoralCollegeModel(String ecFileLocation){
            String line;
            String[] lineArr;
            try {
                BufferedReader zipReader = new BufferedReader(new FileReader(ecFileLocation));
                line = zipReader.readLine();
                while((line = zipReader.readLine()) != null) {
                    lineArr = line.split(",");
                    ecByState.put(lineArr[0],Integer.parseInt(lineArr[1]));
                    System.out.println(lineArr[0] + " " + lineArr[1]);
                }
            } catch(IOException ex) {
                System.err.println("Error opening file");
            }
    }

    public synchronized static void getWinnerOfEachState(String year, String state){
        ecByCandidate.clear();
        Firebase presidentRef =ref.child(year + "/Results/" + state +"/US President");// = ref.child(year).child("Results").child(state).child("US President");

        System.out.println("ref " + presidentRef);
        presidentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    try {
                        Iterable<DataSnapshot> stateCandidates = dataSnapshot.getChildren();
                        int maxVotes = 0;
                        String maxCandidate = "";
                        if(stateCandidates!=null) {
                            for (DataSnapshot candidate : stateCandidates) {

                                System.out.println("next candidate key" + candidate.getKey() + " " + candidate.getValue());
                                System.out.println(Integer.parseInt(candidate.getValue().toString()) > maxVotes);
                                if (Integer.parseInt(candidate.getValue().toString()) > maxVotes) {

                                    maxCandidate = candidate.getKey();
                                    maxVotes = Integer.parseInt(candidate.getValue().toString());
                                    System.out.println("statecadidates keys " + dataSnapshot.child("US President").getValue());


                                }
                            }
                            if (maxVotes > 0) {

                                if (ecByCandidate.containsKey(maxCandidate)) {
                                    ecByCandidate.put(maxCandidate, ecByCandidate.get(maxCandidate) + ecByState.get(state));
                                } else{
                                    ecByCandidate.put(maxCandidate, ecByState.get(state));
                                }
                            }
                        }
                        System.out.println("ec keys" + ecByCandidate.keySet());

                        System.out.println("ec values " + ecByCandidate.values());
                    }catch (NullPointerException e){
                        System.err.println("null ptr exception");
                    }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    public static Map<String,Integer> getEcByState() {
        return ecByState;
    }

    public static Map<String,Integer> getEcByCandidate(){
        return ecByCandidate;
    }
}
