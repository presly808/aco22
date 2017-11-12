package week1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {

    Bill bill;
    Terminal terminal;
    Salesman salesman;


    @Before
    public void initData() {
        bill = new Bill();
        salesman = new Salesman("Marusia", "maria", "pass", false);
        terminal = new Terminal();
    }

    @Test
    public void createBill()  {

        Assert.assertFalse(terminal.createBill(bill, salesman));
        salesman.setStatus(true);
        Assert.assertTrue(terminal.createBill(bill, salesman));

    }

    @Test
    public void closeAndSaveBill(){
        
    }

}