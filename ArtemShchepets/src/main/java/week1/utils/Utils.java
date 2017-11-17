package week1.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getCurrentDate() {
            Date timeNow = new Date();
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return (time.format(timeNow));
    }
}
