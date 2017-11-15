package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDate() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
        String creationDate = simpleDateFormat.format(date);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(creationDate);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
