package ua.artcode.market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {

    public static final int DEFAULT_COUNT_PRODUCTS = 100;
    public static final int DEFAULT_COUNT_SALESMEN = 5;
    public static final int DEFAULT_COUNT_BILLS = 5;

    Product[] p = new Product[DEFAULT_COUNT_PRODUCTS];
    Salesman[] s = new Salesman[DEFAULT_COUNT_SALESMEN];
    Bill[] bills = new Bill[DEFAULT_COUNT_BILLS];


    Terminal t = new Terminal(s);
    Salesman saler;

    @Before
    public void setUp() throws Exception {

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
    public void createBill() throws Exception {
       assertTrue( t.createBill(saler));
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
        bills[0]=b;
        assertNotNull(b.getSalesMan());
        assertEquals(3,b.getNumProd());
    }

    @Test
    public void findBillById() throws Exception {
        closeAndSaveBill();
        assertEquals(bills[0],t.findBillById(1));

    }

    @Test
    public void findSalesmanByLoginOrFullname() throws Exception {
      String login, fullname;

      login = s[0].getLogin();
      fullname = s[0].getFullname();

      assertEquals(s[0],t.findSalesmanByLoginOrFullname(login)[0]);
      assertEquals(s[0],t.findSalesmanByLoginOrFullname(fullname)[0]);

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