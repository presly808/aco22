package week1;

import org.junit.Assert;
import org.junit.Test;

import ua.artcode.exclude.ITest;

public class TestForAllProjects {

    @Test
    public void test(){
        ITest testI = new ITest();
        Assert.assertEquals(4,testI.sum(2,2));
    }

}
