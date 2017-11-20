package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalFactory;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import java.util.Map;

import static org.junit.Assert.*;

public class ITerminalControllerTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        this.terminalController = TerminalFactory.create();
    }

    @After
    public void tearDown() throws Exception {
        this.terminalController = null;
    }

    @Test
    public void createBill() throws Exception {
        Bill expected = terminalController.createBill();
        assertEquals(0, expected.getProductsMap().size());
        assertNotNull(expected);
        assertEquals(0, expected.getId());
    }

    @Test
    public void addProduct() throws Exception {
        Bill bill = terminalController.createBill();
        bill = terminalController.addProduct(bill.getId(),
                terminalController.getiAppDb().findProductById(1));
        assertEquals(1,bill.getProductsMap().size());
    }

    @Test
    public void getAllBills() throws Exception {
        Bill bill1 = terminalController.createBill();
        Bill bill2 = terminalController.createBill();
        Bill bill3 = terminalController.createBill();
        Bill bill4 = terminalController.createBill();
        Bill bill5 = terminalController.createBill();

        assertEquals(5, terminalController.getAllBills().size());

    }

    @Test
    public void closeBill() throws Exception {
        Bill open = terminalController.createBill();
        Bill close = terminalController.closeBill(open.getId());
        Assert.assertEquals(open, close);
        assertNotNull(close.getCloseTime());
    }

    @Test
    public void calculateAmountPrice() throws Exception {
        Bill open = terminalController.createBill();
        Product product = Generator.createProduct();
        terminalController.addProduct(open.getId(), product);
        double amountPrice = terminalController.calculateAmountPrice(open);
        assertNotEquals(0.0, amountPrice);
    }

    @Test
    public void createSalesman() throws Exception {
        Salesman salesman = terminalController.getiAppDb().
                createSalesman("1", "1", "1");

        assertNotEquals(null, salesman);
    }

    @Test
    public void login() throws Exception {
        Salesman salesman = terminalController.getiAppDb().
                createSalesman("1", "1", "1");
        salesman = terminalController.getiAppDb().login("1", "1");
        assertNotEquals(null, salesman);
    }

    @Test
    public void login1() throws Exception {
        Salesman salesman = terminalController.getiAppDb().login("asd", "asd");
        assertEquals(null, salesman);
    }
}