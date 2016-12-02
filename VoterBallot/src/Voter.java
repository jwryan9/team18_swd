/**
 * Created by AdamGary on 12/1/16.
 */
public class Voter {


    private String ID;
    private String name;
    private String zipCode;


    public Voter(String encryptedID, String name, String zipCode){
        this.ID = encryptedID;
        this.name = name;
        this.zipCode = zipCode;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return zipCode;
    }

}
