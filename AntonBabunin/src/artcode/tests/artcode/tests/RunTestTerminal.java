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
        exepted.setSales(new Salesman[5]);
        exepted.setBills(new Bill[5]);
        Assert.assertTrue(actual.equals(exepted));
    }

    @Test
    public void testTerminalCreation1() {
        Terminal actual = TerminalCreator.terminalCreation();
        Terminal exepted = new Terminal();
        exepted.setSales(new Salesman[5]);
        exepted.setBills(new Bill[4]);
        Assert.assertFalse(actual.equals(exepted));
    }



}
