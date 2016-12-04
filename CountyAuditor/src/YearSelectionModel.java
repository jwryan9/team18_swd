/**
 * Created by jasonryan on 12/3/16.
 */
public class YearSelectionModel {
    public static void setYear(int year) {

    }

    public static String validateYear(int year) {
        if(year % 2 == 0 && year >= 2016) {
            return "";
        } else {
            return "ERROR: INVALID YEAR";
        }
    }
}
