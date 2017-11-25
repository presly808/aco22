package week1.controller;

import org.junit.Test;
import week1.interfaces.ITerminalController;

import static org.junit.Assert.*;

public class ITerminalControllerFactoryTest {

    @Test
    public void testCreate() {

        ITerminalController terminalController = ITerminalControllerFactory.create();

        assertNotNull(terminalController);
    }

}