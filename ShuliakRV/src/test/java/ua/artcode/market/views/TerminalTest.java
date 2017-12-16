package ua.artcode.market.views;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.exceptions.AppException;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;

import static org.junit.Assert.*;

public class TerminalTest {

    private ITerminal terminalController;

    private Terminal terminal;

    private Salesman salesman;

    @Before
    public void setUp() throws Exception {

        terminalController = FactoryITerminal.createITerminalController();
        terminal = new Terminal(terminalController);
        salesman = terminalController.getAppDB().getAllSalesman().get(0);
        salesman = terminalController.logIn(salesman.getLogin(), salesman.getPassword());
    }

    @Test
    public void mainMenu() throws Exception {
        terminal.mainMenu(false);
    }

    @Test
    public void runChoice() throws Exception {

        ITerminal terminalController;

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