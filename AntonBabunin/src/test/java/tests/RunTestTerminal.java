package tests;

import ua.artcode.market.bill.Bill;
import ua.artcode.market.creator.TerminalCreator;
import ua.artcode.market.salesman.Salesman;
import ua.artcode.market.terminal.Terminal;
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
        exepted.setSales(new Salesman[20]);
        exepted.setBills(new Bill[19]);
        Assert.assertFalse(actual.equals(exepted));

    }



}
