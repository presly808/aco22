package week1.models;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by ENIAC on 16.11.2017.
 */
public class TimeTest {

    @Test
    public void printTime() throws Exception {

        Time testTime = new Time();

        Date testTimeNow = new Date();
        SimpleDateFormat time = new SimpleDateFormat("'Date: 'yyyy.MM.dd 'Time: 'H:mm:ss");

        assertEquals(time.format(testTimeNow), testTime.printTime());

    }

}