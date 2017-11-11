package tests;


import org.junit.Assert;
import org.junit.Test;

import static artcode.shop.creator.BillCreator.createBill;

public class RunTestBill {
    @Test
    public void testBillCreation() {
        Terminal t1 = TerminalCreator.terminalCreation();
        Salesman s1 = new Salesman("asd", "asd","asd");
        s1.loginAutomatic(t1);
        Bill actual = createBill(s1);
        Bill expected = new Bill(actual.getId(), new Salesman("asd", "asd","asd"));
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testBillCreation1() {
        Bill actual = createBill(new Salesman("asd", "asd","asd"));
        Bill expected = new Bill(1, new Salesman("asdasd", "asd","asd"));
        Assert.assertFalse(expected.equals(actual));
    }
    @Test
    public void testBillCreation2() {
        Bill actual = createBill(new Salesman("asd", "asd","asd"));
        Bill expected = new Bill(2, new Salesman("asd", "asd","asd"));
        Assert.assertFalse(expected.equals(actual));
    }

}
