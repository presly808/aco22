package week1.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.controllers.ISalesmanControllerImpl;
import week1.controllers.ProxyTerminalControllerImpl;
import week1.factories.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;
import week1.models.Department;
import week1.models.Salesman;

import static org.junit.Assert.*;
import static week1.utils.TerminalUtils.makeSalesmansAndSubSalesmans;

public class SalesmansUtilsTest {
    private ITerminalController testTerminal;

    ISalesmanControllerImpl controller;

    @Before
    public void setUp() throws Exception {

        testTerminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());
        makeSalesmansAndSubSalesmans(testTerminal.getDb());
        controller = new ISalesmanControllerImpl();
    }

    @After
    public void tearDown() throws Exception {

        testTerminal = null;

    }


    @Test
    public void calculateSallary() {
        Salesman main = testTerminal.getDb().getAllSalesMans().get(0);
        double expected = 67.368;
        Department dep = new Department();
        double actual = controller.calculateSallary(dep, main);
        assertEquals(expected, actual, 0.01);
        assertEquals(355.768, dep.getMoneyToPay(), 0.01);
        assertEquals(7000.00, dep.getMoneyIncome(), 0.01);
    }
}