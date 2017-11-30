package ua.artcode.market.views;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;

import static org.junit.Assert.*;

public class TerminalTest {

    private ITerminal terminalController;

    private Terminal terminal;

    @Before
    public void setUp() throws Exception {

        terminalController = FactoryITerminal.createITerminalController();
        terminal = new Terminal(terminalController);

    }

    @Test
    public void runChoice() throws Exception {

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