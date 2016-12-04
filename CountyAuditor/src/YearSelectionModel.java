import com.firebase.client.Firebase;

/**
 * Model for year selection application
 */
public class YearSelectionModel {

    /**
     * Firebase reference used for setting date in database
     */
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    /**
     * Sets year in database
     *
     * @param year value to be set year variable in database
     */
    public static void setYear(int year) {
        ref.setValue(String.valueOf(year));
    }

    /**
     *
     * @param year value to validsate
     * @return empty string if valid, error message if invalid
     */
    public static String validateYear(int year) {
        if(year % 2 == 0 && year >= 2016) {
            return "";
        } else {
            return "ERROR: INVALID YEAR";
        }
    }
}
