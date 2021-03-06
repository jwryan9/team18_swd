import java.util.Map;

/**
 * Class used for representing candidates up for election
 */
public class Candidate {
    /**
     * Candidate's id number
     */
    private int idNumber;

    /**
     * Candidate name
     */
    private String name;

    /**
     * Candidate's zip code
     */
    private String zip;

    /**
     * Candidate's state of residence
     */
    private String state;

    /**
     * Elected office candidate is running for
     */
    private String office;

    /**
     * Candidate's party affiliation
     */
    private String party;

    /**
     * Constructor of Candidate objects
     *
     * @param newCandidateMap Map storing candidate information
     * @param idNumber candidate's id number for database lookup
     */
    public Candidate(Map<String,String> newCandidateMap, int idNumber){
        this.name = newCandidateMap.get("name");
        this.zip = newCandidateMap.get("zip");
        this.state = newCandidateMap.get("state");
        this.office = newCandidateMap.get("office");
        this.party = newCandidateMap.get("party");
        this.idNumber = idNumber;

    }

    /**
     * Getter method for name attribute
     *
     * @return candidate's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for zip code attribute
     *
     * @return candidate's zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Getter method for state attribute
     *
     * @return candidate's state of residence
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method for office attribute
     *
     * @return office candidate is running for
     */
    public String getOffice() {
        return office;
    }

    /**
     * Getter method for party attribute
     *
     * @return candidate's pary affiliation
     */
    public String getParty() {
        return party;
    }

}
