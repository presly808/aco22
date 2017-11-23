package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.BillComparator;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static ua.artcode.market.models.BillComparator.other;

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
        open.toString();
        open.getOpenTime();
        open.getAmountPrice();
        open.setAmountPrice(0.0);
        terminalController.getiAppDb().getAllProducts().put(product, 15);
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
        salesman.toString();
        Salesman salesman2 = terminalController.getiAppDb().
                createSalesman("2", "2", "2");
        salesman2.setLogin("2");
        salesman2.setPassword("2");
        salesman2.setFullName("2");
        salesman2.setIsConnected(false);

        assertFalse(salesman.equals(salesman2));
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
        terminalController.prinBill(expected);
        assertEquals(expectedReturn,acttual);
    }

    @Test
    public void filter() throws Exception {




        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        product1.setName("1");
        product1.setId(1);
        product2.setName("2");
        product2.setId(2);
        product3.setName("3");
        product3.setId(3);

        product1.setPrice(1);
        product2.setPrice(5);
        product3.setPrice(2313.56);

        Salesman salesman1 = terminalController.
                createSalesman("1123","123","1");
        Salesman salesman2 = terminalController.
                createSalesman("2","12","1");
        Salesman salesman3 = terminalController.
                createSalesman("2","13","1");

        terminalController.getiAppDb().getAllProducts().put(product1, 15);
        terminalController.getiAppDb().getAllProducts().put(product2, 15);
        terminalController.getiAppDb().getAllProducts().put(product3, 15);



        Bill bill1 = terminalController.createBill();
        terminalController.addProduct(bill1.getId(), product1);
        terminalController.addProduct(bill1.getId(), product1);
        terminalController.addProduct(bill1.getId(), product1);

        Bill bill2 = terminalController.createBill();
        terminalController.addProduct(bill2.getId(), product1);
        terminalController.addProduct(bill2.getId(), product2);
        terminalController.addProduct(bill2.getId(), product2);

        Bill bill3 = terminalController.createBill();
        terminalController.addProduct(bill3.getId(), product3);
        terminalController.addProduct(bill3.getId(), product2);
        terminalController.addProduct(bill3.getId(), product2);

        Bill bill4 = terminalController.createBill();
        terminalController.addProduct(bill4.getId(), product3);
        terminalController.addProduct(bill4.getId(), product3);
        terminalController.addProduct(bill4.getId(), product1);

        bill3.setSalesman(salesman3);
        bill2.setSalesman(salesman2);
        bill1.setSalesman(salesman1);
        bill4.setSalesman(salesman1);



        terminalController.closeBill(bill1.getId());
        terminalController.closeBill(bill2.getId());
        terminalController.closeBill(bill3.getId());
        terminalController.closeBill(bill4.getId());

        List<Bill> sorted = terminalController.getiAppDb().filter(salesman1,
                product1, null, null, BillComparator.
                        billComparator.thenComparing(other));

       /* LocalDateTime.MIN, LocalDateTime.MAX*/
        assertTrue(sorted.get(0).getAmountPrice() >=
                sorted.get(1).getAmountPrice());
        assertTrue(sorted.size() == 2);
    }
}