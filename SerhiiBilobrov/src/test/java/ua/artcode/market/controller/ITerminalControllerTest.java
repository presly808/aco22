package ua.artcode.market.controller;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import static org.junit.Assert.*;

/**
 * Created by serhii on 19.11.17.
 */
public class ITerminalControllerTest {

    private ITerminalController terminalController;

    @Before
    public void setUp(){
        this.terminalController = new ITerminalControllerImpl(new IAppDbImpl());
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

        Product product = new Product();
        product.setPrice(123);
        product.setId(1);
        product.setName("Potato");

        terminalController.addProduct(bill.getId(), product);

        product.getId();
        product.getName();
        product.getPrice();


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