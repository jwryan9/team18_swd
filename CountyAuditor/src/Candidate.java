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


    public Candidate(int idNumber, String name, String city, String state, String office, String party){
        this.idNumber = idNumber;
        this.name = name;
        this.city = city;
        this.state = state;
        this.office = office;
        this.party = party;
    }

    public int getIdNumber() {
        return idNumber;
    }

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
