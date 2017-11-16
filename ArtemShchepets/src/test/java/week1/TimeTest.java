package week1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeTest {

    Time testTime;

    @Before
    public void setUp() {

        testTime = new Time();
        testTime.setSeconds(12);
        testTime.setMinutes(12);
        testTime.setHours(12);
    }

    @After
    public void tearDown() {
        testTime = null;
    }

    @Test
    public void testShowTime() {

        Assert.assertEquals("12:12:12", testTime.toString());

    }
}
