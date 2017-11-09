package artcode.tests;

import artcode.shop.bill.Bill;
import artcode.shop.creator.TerminalCreator;
import artcode.shop.salesman.Salesman;
import artcode.shop.terminal.Terminal;
import org.junit.Assert;
import org.junit.Test;

public class RunTestTerminal {
    @Test
    public void testTerminalCreation() {
        Terminal actual = TerminalCreator.terminalCreation();
        Terminal exepted = new Terminal();
        exepted.setSales(new Salesman[20]);
        exepted.setBills(new Bill[20]);
        Assert.assertTrue(actual.equals(exepted));
    }

    @Test
    public void testTerminalCreation1() {
        Terminal actual = TerminalCreator.terminalCreation();
        Terminal exepted = new Terminal();
        exepted.setSales(new Salesman[21]);
        exepted.setBills(new Bill[20]);
        Assert.assertFalse(actual.equals(exepted));
    }


}
