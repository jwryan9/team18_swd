import com.firebase.client.Firebase;

/**
 * Created by jasonryan on 12/3/16.
 */
public class YearSelectionModel {

    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Election Cycle");

    public static void setYear(int year) {
        ref.setValue(year);
    }

    public static String validateYear(int year) {
        if(year % 2 == 0 && year >= 2016) {
            return "";
        } else {
            return "ERROR: INVALID YEAR";
        }
    }
}
