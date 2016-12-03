/**
 * Class for representing voters
 */
public class Voter {

    /**
     * Voter id for database
     */
    private String ID;

    /**
     * Voter's name
     */
    private String name;

    /**
     * Voter's zip code
     */
    private String zipCode;

    /**
     * Constructor for creating voter objects
     *
     * @param encryptedID encrypted id for securely referencing voter information
     * @param name name of voter
     * @param zipCode zip code of voter's residence
     */
    public Voter(String encryptedID, String name, String zipCode){
        this.ID = encryptedID;
        this.name = name;
        this.zipCode = zipCode;
    }

    /**
     * Getter method for voter id
     *
     * @return encrypted voter id
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter method for voter name
     *
     * @return voter name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for voter zip code
     *
     * @return voter zip code
     */
    public String getZipCode() {
        return zipCode;
    }

}
