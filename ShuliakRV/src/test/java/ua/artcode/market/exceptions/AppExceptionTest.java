package ua.artcode.market.exceptions;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.proxies.ProxyTerminalController;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;

import static org.junit.Assert.*;

public class AppExceptionTest {

    private ProxyTerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        terminalController = (ProxyTerminalController)
                FactoryITerminal.createITerminalController();
        Salesman salesman = terminalController.getAppDB().getAllSalesman().get(0);
        salesman = terminalController.logIn(salesman.getLogin(), salesman.getPassword());
    }

    @Test(expected = AppException.class)
    public void createBill() throws Exception {
        Bill bill = terminalController.createBill(null);
        assertNull(bill);
    }
}

