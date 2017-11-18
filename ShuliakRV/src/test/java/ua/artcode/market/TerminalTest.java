package ua.artcode.market;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.models.*;
import ua.artcode.market.utils.Utils;

import static org.junit.Assert.*;

public class TerminalTest {

    @Test
    public void getIndexSales() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        assertEquals(0, t.getIndexSales(appDB.getSales()[0]));
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {
        Statistic s = null;
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotNull(t.doSomeStatisticStuff());
    }

    @Test
    public void createBill() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        assertTrue(t.createBill(appDB.getSales()[0]));
    }

    @Test
    public void addProduct() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        assertTrue(t.addProduct(appDB.getProducts()[0]));
        assertTrue(t.addProduct(appDB.getProducts()[1]));
    }

    @Test
    public void closeAndSaveBill() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        assertTrue(t.addProduct(appDB.getProducts()[0]));
        assertTrue(t.addProduct(appDB.getProducts()[1]));
        assertNotNull(t.closeAndSaveBill());
    }

    @Test
    public void findBillById() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotEquals(appDB.getBills()[0].getId(),
                t.findBillById(appDB.getBills()[0].getId()));
    }

    @Test
    public void findSalesmanByLoginOrFullname() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);

        String login = appDB.getSales()[0].getLogin();
        String fullname = appDB.getSales()[0].getFullname();

        assertEquals(appDB.getSales()[0],
                t.findSalesmanByLoginOrFullname(login)[0]);
        assertEquals(appDB.getSales()[0],
                t.findSalesmanByLoginOrFullname(fullname)[0]);

    }

    @Test
    public void getMax() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotEquals(0, t.getMax());
    }

    @Test
    public void getMin() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotEquals(0, t.getMin());
    }

    @Test
    public void getAverage() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotEquals(0, t.getAverage());
    }

    @Test
    public void countSoldProducts() throws Exception {
        AppDB appDB = new AppDB();
        TerminalController t = new TerminalController(appDB);
        t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());
        t.createBill(appDB.getSales()[0]);
        t.addProduct(appDB.getProducts()[0]);
        t.addProduct(appDB.getProducts()[1]);
        t.closeAndSaveBill();
        assertNotEquals(0, t.countSoldProducts());
    }

}