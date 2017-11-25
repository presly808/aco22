package week1.ProxyTerminalController;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.AbstractFactory.ITerminalControllerFactory;
import week1.comparators.BillComparatorForSorting;
import week1.controller.IAppDbImpl;
import week1.interfaces.IAppDb;
import week1.interfaces.ITerminalController;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ProxyTerminalControllerImplTest {

    private ITerminalController testTerminal;


    @Before
    public void setUp() throws Exception {

        testTerminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());
    }

    @After
    public void tearDown() throws Exception {

        testTerminal.getAllBills().clear();

                testTerminal = null;
    }

    @Test
    public void login() throws Exception {
        String login = "2";
        String pass = "3";
        testTerminal.login(login, pass);

        Assert.assertEquals(2, testTerminal.getCurrentSalesmanIndex());

    }

    @Test
    public void createBill() throws Exception {
        testTerminal.login("2", "3");
        testTerminal.createBill();
        assertEquals(1, testTerminal.getAllBills().size());


    }


    @Test
    public void closeBill() throws Exception {
        testTerminal.login("2", "3");
        testTerminal.createBill();
        testTerminal.closeBill(0);

        assertTrue(testTerminal.getAllBills().get(0).isClosed());

    }

    @Test
    public void filterForBills() throws Exception {
        testTerminal.login("2", "3");

        testTerminal.createBill();
        testTerminal.createBill();
        testTerminal.createBill();

        testTerminal.closeBill(0);
        testTerminal.closeBill(1);
        testTerminal.closeBill(2);

        testTerminal.getAllBills().get(0).getTime().setCloseTime("Date: 2017.05.21 Time: 21:15:09");
        testTerminal.getAllBills().get(1).getTime().setCloseTime("Date: 2017.07.22 Time: 10:15:09");
        testTerminal.getAllBills().get(2).getTime().setCloseTime("Date: 2017.11.23 Time: 01:15:09");

        assertSame(testTerminal.getAllBills().get(2),
                testTerminal.filterForBills("Date: 2017.10.24 Time: 05:15:09",
                        "Date: 2017.12.24 Time: 23:15:09",
                        new BillComparatorForSorting()).get(0));

    }

    @Test
    public void findSalesmanByLogin() throws Exception {
        testTerminal.login("2", "3");
        testTerminal.createBill();

        assertSame(testTerminal.getAllBills().get(0).getSalesman(), testTerminal.findSalesmanByLogin("2"));

    }

    @Test
    public void getTopOfSalesmans() throws Exception {

        testTerminal.login("2", "3");
        testTerminal.createBill();
        testTerminal.addProduct(0, testTerminal.getAllProducts().get(1));
        testTerminal.addProduct(0, testTerminal.getAllProducts().get(1));
        testTerminal.closeBill(0);

        assertSame(testTerminal.findSalesmanByLogin("2"), testTerminal.getTopOfSalesmans());

    }

    @Test
    public void setCurrentSalesmanIndex() throws Exception {
        testTerminal.setCurrentSalesmanIndex(0);
        assertEquals(0, testTerminal.getCurrentSalesmanIndex());
    }

    @Test
    public void addProduct() throws Exception {
        testTerminal.login("2", "3");
        testTerminal.createBill();
        testTerminal.addProduct(0, testTerminal.getAllProducts().get(1));

        assertEquals(1, testTerminal.findBillById(0).getProductList().size());
    }

}