import java.util.Date;
import java.text.*;
/**
 * Created by ENIAC on 16.11.2017.
 */
public class Time {

     static Time time = new Time();

    public String printTime() {
        Date timeNow = new Date();
        SimpleDateFormat time = new SimpleDateFormat("'Time: 'H:mm:ss'\nDate: 'yyyy.MM.dd");
        return (time.format(timeNow));
    }


}




