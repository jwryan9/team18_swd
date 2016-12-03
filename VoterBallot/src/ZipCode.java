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

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(zipFilePath));

            while((line = bufferedReader.readLine()) != null) {
                lineArr = line.split(",");
                if(lineArr[2].equals(zipString)) {
                    return lineArr;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error opening file");
        }

        return null;
    }
}
