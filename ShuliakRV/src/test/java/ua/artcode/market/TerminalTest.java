package ua.artcode.market;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {

    private static final int DEFAULT_COUNT_PRODUCTS = 100;
    private static final int DEFAULT_COUNT_SALESMEN = 5;
    //private static final int DEFAULT_COUNT_BILLS = 5;

    private Product[] p;
    private Salesman[] s;


    private Terminal t;
    private Salesman saler;

    @Before
    public void setUp() throws Exception {

        p = new Product[DEFAULT_COUNT_PRODUCTS];

        s = new Salesman[DEFAULT_COUNT_SALESMEN];

        t = new Terminal(s);

        for (int i = 0; i < p.length; i++) {
            p[i] = Utils.generateProduct();
        }

        for (int i = 0; i < s.length; i++) {
            s[i] = Utils.generateSalesman();
        }

        saler = s[0];
        saler.setLogged(true);
    }

    @Test
    public void getIndexSales() throws Exception {
        assertEquals(0, t.getIndexSales(s[0]));
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {
        t.doSomeStatisticStuff();
    }

    @Test
    public void createBill() throws Exception {
        assertTrue(t.createBill(saler));
    }

    @Test
    public void addProduct() throws Exception {
        createBill();
        assertTrue(t.addProduct(p[0]));
        assertTrue(t.addProduct(p[1]));
        assertTrue(t.addProduct(p[2]));
    }

    @Test
    public void closeAndSaveBill() throws Exception {
        addProduct();
        Bill b = t.closeAndSaveBill();
        assertNotNull(b.getSalesMan());
        assertEquals(3, b.getNumProd());
    }

    @Test
    public void findBillById() throws Exception {
        t.createBill(saler);
        addProduct();
        assertNull(t.findBillById(0));

    }

    @Test
    public void findSalesmanByLoginOrFullname() throws Exception {

        String login = s[0].getLogin();
        String fullname = s[0].getFullname();

        assertEquals(s[0], t.findSalesmanByLoginOrFullname(login)[0]);
        assertEquals(s[0], t.findSalesmanByLoginOrFullname(fullname)[0]);

    }

    @Test
    public void getMax() throws Exception {
        closeAndSaveBill();
        assertNotEquals(0, t.getMax());
    }

    @Test
    public void getMin() throws Exception {
        closeAndSaveBill();
        assertNotEquals(0, t.getMin());
    }

    @Test
    public void getAverage() throws Exception {
        closeAndSaveBill();
        assertNotEquals(0, t.getAverage());
    }

    @Test
    public void countSoldProducts() throws Exception {
        closeAndSaveBill();
        assertNotEquals(0, t.countSoldProducts());
    }

}