package week1.models;

import java.util.Date;
import java.text.*;

/**
 * Created by ENIAC on 16.11.2017.
 */
public class Time {

    private String closeTime;


    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }


    public String printTime() {
        Date timeNow = new Date();
        SimpleDateFormat time = new SimpleDateFormat("'Date: 'yyyy.MM.dd' Time: 'H:mm:ss");
        return (time.format(timeNow));
    }

}




