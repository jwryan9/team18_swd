import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for zip code lookup
 */
public class ZipCode {

    /**
     * Parses csv file of count/state/zip code values and returns
     * the set containing the requested zip code.
     *
     * @param zipString zip code being searched for
     * @param zipFilePath path to csv file
     * @return String[] of file contents for requested zip or null if not found
     */
    public static String[] parseZip(String zipString, String zipFilePath) {
        String line;
        String[] lineArr;
        System.out.println("");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(zipFilePath));

            while((line = bufferedReader.readLine()) != null) {
                lineArr = line.split(",");
                if(lineArr[2].equals(zipString)) {
                    System.out.println("Found!");

                    return lineArr;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error opening file");
        }

        return null;
    }

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
