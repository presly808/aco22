package ua.artcode.market.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static int generateID() {
        return (int)(Math.random()*10000);
    }

    public static String getCurrentTime() {
        SimpleDateFormat dF =
                new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
        return (dF.format(new Date()));
    }
}
