package utilsTests;

import utils.DateUtils;
import utils.StringGenerator;
import utils.TerminalUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class UtilsTests {

    @Test
    public void testDateUtilsMethod(){
        Assert.assertTrue(DateUtils.getCurrentDate() instanceof Date);
    }

    @Test
    public void testStringGenerator(){
        String generatedName = StringGenerator.generateName();
        Assert.assertNotNull(generatedName);
        Assert.assertTrue(generatedName instanceof String);
    }

    @Test
    public void testTerminalUtils(){
        Assert.assertTrue(TerminalUtils.longIdGenerator()
                                != TerminalUtils.longIdGenerator());
    }

}
