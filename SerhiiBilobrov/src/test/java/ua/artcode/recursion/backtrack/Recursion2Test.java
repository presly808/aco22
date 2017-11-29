package ua.artcode.recursion.backtrack;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by serhii on 26.11.17.
 */
public class Recursion2Test {
    @Test
    public void hasSeq() throws Exception {

        Assert.assertTrue(Recursion2.hasSeq(new int[]{1,2,5,10,15}, 13));

    }

    @Test
    public void retSeq() throws Exception {

        List<Integer> res = Recursion2.retSeq(new int[]{1,2,5,10,15}, new ArrayList<>(),13);
        List<Integer> expected = Arrays.asList(10, 1, 2);
        //Assert.assertEquals(expected, res);
        assertTrue(res.containsAll(expected));


    }

    @Test
    public void verificationForTravis() throws Exception {

        List<Integer> list= Arrays.asList(10,1,2);

        Recursion2.doubling(list);
    }

}