/**
 * Class to create Candidates.
 */
public class Candidate {
    /**
     * Variable to hold candidate's name.
     */
    private String name;

    /**
     * Variable to hold candidate's zip code.
     */
    private String zip;

    /**
     * Variable to hold candidate's state of residence.
     */
    private String state;

    /**
     * Variable to hold candidate's office they are running for.
     */
    private String office;

    /**
     * Variable to hold candidate's party affiliation.
     */
    private String party;

    /**
     * Constructor for the Candidate Class.
     * @param name      candidate's name.
     * @param zip       candidate's zip code.
     * @param state     candidate's state.
     * @param office    candidate's office.
     * @param party     candidate's party.
     */
    public Candidate(String name, String zip, String state, String office, String party){
        this.name = name;
        this.zip = zip;
        this.state = state;
        this.office = office;
        this.party = party;
    }

    /**
     * Getter method for the name field.
     * @return  the candidate's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the zip code field.
     * @return  the candidate's zip code.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Getter method for the state field.
     * @return  the candidate's state.
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method for the office field.
     * @return  the candidate's office.
     */
    public String getOffice() {
        return office;
    }

    /**
     * Getter method for the party field.
     * @return  the candidate's party.
     */
    public String getParty() {
        return party;
    }

}
