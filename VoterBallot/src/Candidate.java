import java.util.HashMap;
import java.util.Map;

/**
 * Created by AdamGary on 12/1/16.
 */
public class Candidate {
    private int idNumber;
    private String name;
    private String city;
    private String state;
    private String office;
    private String party;

    public Candidate(Map<String,String> newCandidateMap, int idNumber){
        this.name = newCandidateMap.get("name");
        this.city = newCandidateMap.get("city");
        this.state = newCandidateMap.get("state");
        this.office = newCandidateMap.get("office");
        this.party = newCandidateMap.get("party");
        this.idNumber = idNumber;

    }

    public Candidate(String name, String city, String state, String office, String party){
        //this.idNumber;
        this.name = name;
        this.city = city;
        this.state = state;
        this.office = office;
        this.party = party;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", this.name);
        result.put("city", this.city);
        result.put("state", this.state);
        result.put("office", this.office);
        result.put("party", this.party);

        return result;
    }

    /*
    public int getIdNumber() {
        return idNumber;
    }
    */

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getOffice() {
        return office;
    }

    public String getParty() {
        return party;
    }

}
