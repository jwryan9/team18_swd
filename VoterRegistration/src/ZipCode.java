import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for zip code lookup
 */
public class ZipCode {
    /**
     * Searches csv file of county/state/zip code values and checks if input state/zip combo is valid
     *
     * @param zipString zip coe being searched for
     * @param stateString state being searched for
     * @param zipFilePath path to csv file
     * @return true if state/zip combo is valid, else false
     */
    public static boolean validateZip(String zipString, String stateString, String zipFilePath) {
        String line;
        String[] lineArr;
        try {
            BufferedReader zipReader = new BufferedReader(new FileReader(zipFilePath));

            // Loop through the file and return true if state and zip code match
            while((line = zipReader.readLine()) != null) {
                lineArr = line.split(",");

                if(lineArr[1].equals(stateString) && lineArr[2].equals(zipString)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error opening file");
        }

        // Returns false if state/zip match is not found or if file not opened correctly
        return false;
    }
}
