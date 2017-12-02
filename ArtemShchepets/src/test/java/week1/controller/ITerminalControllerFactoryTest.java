package week1.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ITerminalControllerFactoryTest {

    @Test
    public void testCreate() {

        ITerminalController terminalController = ITerminalControllerFactory.create();

        assertNotNull(terminalController);
    }

}