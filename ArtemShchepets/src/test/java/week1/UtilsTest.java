package week1;

import org.junit.Assert;
import org.junit.Test;

import static week1.utils.Utils.getCurrentDate;

public class UtilsTest {

    @Test
    public void testDateUtilsMethod(){
        Assert.assertTrue(getCurrentDate() instanceof String);
    }
}
