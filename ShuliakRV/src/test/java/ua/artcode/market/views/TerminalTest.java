package ua.artcode.market.views;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Salesman;

import static org.junit.Assert.*;

public class TerminalTest {

    private Terminal terminal;

    @Before
    public void setUp() throws Exception {

        ITerminal terminalController;

        terminalController = FactoryITerminal.createITerminalController();
        terminal = new Terminal(terminalController);
        Salesman salesman = terminalController.getAppDB().getAllSalesman().get(0);
        salesman = terminalController.logIn(salesman.getLogin(), salesman.getPassword());
    }

    @Test
    public void mainMenu() throws Exception {
        boolean run = false;
        terminal.mainMenu(run);
        assertFalse(run);
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
        assertEquals(0, terminal.runChoice(0));
    }

}