import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Class for zip code lookup
 */
public class ZipCode {

    /**
     * Parses csv file of county/state/zip code values and returns
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
            BufferedReader zipReader = new BufferedReader(new FileReader(zipFilePath));
            while((line = zipReader.readLine()) != null) {
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

    public static ArrayList<String> parseCounty(String countyString, String stateString, String zipFilePath) {
        String line;
        String[] lineArr;
        ArrayList<String> zipArrList;

        try {
            BufferedReader zipReader = new BufferedReader(new FileReader(zipFilePath));
            zipArrList = new ArrayList<>();

            while((line = zipReader.readLine()) != null) {
                lineArr = line.split(",");
                if(lineArr[0].equals(countyString) && lineArr[1].equals(stateString)) {
                    zipArrList.add(lineArr[2]);
                }
            }
            return zipArrList;
        } catch(IOException ex) {
            System.err.println("Error opening file");
        }

        return null;
    }

    public static TreeSet<String> parseState(String stateString, String zipFilePath) {
        String line;
        String[] lineArr;
        ArrayList<String> countyArrList;
        boolean firstCounty = true;
        TreeSet<String> countySet = new TreeSet<>();

        try {
            BufferedReader zipReader = new BufferedReader(new FileReader(zipFilePath));
            countyArrList = new ArrayList<>();
            while((line = zipReader.readLine()) != null) {
                lineArr = line.split(",");
                if(lineArr[1].equals(stateString)) {

                        countySet.add(lineArr[0]);

                }
            }
            return countySet;
        } catch(IOException ex) {
            System.err.println("Error opening file");
        }

        return null;
    }
}
