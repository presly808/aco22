package week1.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.controller.ITerminalControllerImpl;
import week1.database.IAppDBImpl;
import week1.model.Bill;
import week1.model.Product;

import static org.junit.Assert.*;

public class ITerminalControllerTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        terminalController = new ITerminalControllerImpl(new IAppDBImpl());
    }

    @After
    public void tearDown() throws Exception {
        terminalController = null;
    }

    @Test
    public void createBill() throws Exception {

        Bill bill = terminalController.createBill();

        assertEquals(0, bill.getProductList().size());
        assertEquals(0, bill.getId());
    }

    @Test
    public void addProduct() throws Exception {

        Bill bill = terminalController.createBill();

        terminalController.addProduct(bill.getId(), new Product());
        assertEquals(1, bill.getProductList().size());
    }

    @Test
    public void getAllBills() throws Exception {

        Bill bill1 = terminalController.createBill();
        Bill bill2 = terminalController.createBill();
        Bill bill3 = terminalController.createBill();
        Bill bill4 = terminalController.createBill();

        assertEquals(4, terminalController.getAllBills().size());
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        Bill closed = terminalController.closeBill(bill.getId());

        assertTrue(closed.isClosed());
    }

}