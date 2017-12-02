package ua.artcode.market.views;

import org.junit.Test;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;

import static org.junit.Assert.*;

public class TerminalTest {

    @Test
    public void runChoice() throws Exception {

        ITerminal terminalController;

        terminalController = FactoryITerminal.createITerminalController();

        Terminal terminal = new Terminal(terminalController);

        assertEquals(0, terminal.runChoice(1));
        assertEquals(0, terminal.runChoice(2));
        assertEquals(0, terminal.runChoice(3));
        assertEquals(0, terminal.runChoice(4));
        assertEquals(0, terminal.runChoice(5));
        assertEquals(0, terminal.runChoice(6));
        assertEquals(0, terminal.runChoice(7));
        assertEquals(0, terminal.runChoice(8));
        assertEquals(0, terminal.runChoice(9));
        assertEquals(-1, terminal.runChoice(10));
    }

}