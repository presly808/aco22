package week1.models;

import org.junit.Before;
import org.junit.Test;
import week1.controllers.ProxyTerminalControllerImpl;
import week1.factories.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;
import week1.utils.TerminalUtils;

import java.util.List;

import static org.junit.Assert.*;


public class SalesmanTest {
    private ITerminalController testTerminal;


    @Before
    public void setUp() throws Exception {

        testTerminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());
        TerminalUtils.makeSalesmansAndSubSalesmans(testTerminal.getDb());
    }

    @Test
    public void CalculateSalary() throws Exception {

    }
}