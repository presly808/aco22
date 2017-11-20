package hw1.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestUtils {

    @Test
    public void testListContainElementsOther(){

        ArrayList<String> arrList1 = new ArrayList();
        arrList1.add("A");
        arrList1.add("B");
        arrList1.add("C");
        arrList1.add("D");

        ArrayList<String> arrList2 = new ArrayList();
        arrList2.add("A");
        arrList2.add("B");

        ArrayList<String> arrList3 = new ArrayList();
        arrList3.add("E");
        arrList3.add("Z");

        Assert.assertTrue(Utils.listContainElementsOther(arrList1, arrList2));
        Assert.assertFalse(Utils.listContainElementsOther(arrList1, arrList3));

    }

}
