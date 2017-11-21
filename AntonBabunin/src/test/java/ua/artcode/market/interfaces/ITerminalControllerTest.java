package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import static org.junit.Assert.*;

public class ITerminalControllerTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        this.terminalController = TerminalControllerFactory.create();
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
        assertEquals(1, expected.getId());
    }

    @Test
    public void addProduct() throws Exception {
        Bill bill = terminalController.createBill();
        bill = terminalController.addProduct(bill.getId(),
                terminalController.getiAppDb().findProductById(1));
        terminalController.getiAppDb().saveBill(bill);
        if (bill != null) {
            assertEquals(0, bill.getProductsMap().size());
        }
        assertNull(bill);
    }

    @Test
    public void getAllBills() throws Exception {
        terminalController.createBill();
        terminalController.createBill();
        terminalController.createBill();
        terminalController.createBill();
        terminalController.createBill();

        assertEquals(5, terminalController.getAllBills().size());

    }

    @Test
    public void closeBill() throws Exception {
        Bill open = terminalController.createBill();
        Bill close = terminalController.closeBill(open.getId());
        assertEquals(open, close);
        assertNotNull(close.getCloseTime());
    }

    @Test
    public void calculateAmountPrice() throws Exception {
        Bill open = terminalController.createBill();
        Product product = Generator.createProduct();
        open = terminalController.addProduct(open.getId(), product);
        double amountPrice = terminalController.calculateAmountPrice(open);
        product.getPrice();
        product.getId();
        product.getName();
        assertNotEquals(0.0, amountPrice);
        assertNotEquals(0.0, open.getAmountPrice());

    }

    @Test
    public void createSalesman() throws Exception {
        Salesman salesman = terminalController.getiAppDb().
                createSalesman("1", "1", "1");
        salesman.getLogin();
        salesman.getFullName();
        salesman.getPassword();
        salesman.setLogin("1");
        salesman.setPassword("1");
        salesman.setFullName("1");
        salesman.setIsConnected(false);
        salesman.toString();

        assertFalse(salesman.equals(null));
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

    @Test
    public void saveAndRemoveBill() throws Exception {
        Bill expected = terminalController.createBill();
        Bill expectedReturn = terminalController.getiAppDb().saveBill(expected);
        Bill acttual = terminalController.getiAppDb().
                removeBill(expectedReturn.getId());

        assertEquals(expectedReturn,acttual);
    }

}