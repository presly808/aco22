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
    }

}