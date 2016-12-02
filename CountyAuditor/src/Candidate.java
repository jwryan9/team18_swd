/**
 * Created by AdamGary on 12/1/16.
 */
public class Candidate {
    //private int idNumber;
    private String name;
    private String county;
    private String state;
    private String office;
    private String party;


    public Candidate(String name, String county, String state, String office, String party){
        //this.idNumber;
        this.name = name;
        this.county = county;
        this.state = state;
        this.office = office;
        this.party = party;
    }

    /*
    public int getIdNumber() {
        return idNumber;
    }
    */

    public String getName() {
        return name;
    }

    public String getCounty() {
        return county;
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
