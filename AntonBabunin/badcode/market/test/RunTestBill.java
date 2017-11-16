package tests;


import ua.artcode.market.bill.Bill;
import ua.artcode.market.creator.TerminalCreator;
import ua.artcode.market.salesman.Salesman;
import ua.artcode.market.terminal.Terminal;

import static ua.artcode.market.creator.BillCreator.createBill;
import org.junit.Assert;
import org.junit.Test;

public class RunTestBill {
    @Test
    public void testBillCreation() {
        Terminal t1 = TerminalCreator.terminalCreation();
        Salesman s1 = new Salesman("asd", "asd","asd");
        s1.login(t1);
        Bill actual = createBill(s1);
        Bill expected = new Bill(actual.getId(),
                new Salesman("asd", "asd","asd"));
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
