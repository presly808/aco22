package ua.artcode.market.utils;

import ua.artcode.market.models.Time;

public class TimeUtils {
    public static int sumOfSeconds(Time time) {
        if (time == null) {
            return 0;
        }

        int hourInSeconds = 3600;
        int minuteInSeconds = 60;

        return (time.getHours() * hourInSeconds) +
                (time.getMinutes() * minuteInSeconds) +
                time.getSeconds();
    }
}
