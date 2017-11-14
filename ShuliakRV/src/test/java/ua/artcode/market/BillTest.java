package ua.artcode.market;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillTest {

    private static final int DEFAULT_COUNT_PRODUCTS = 100;

    private Product[] p;
    private Bill b;
    private Salesman s;

    @Before
    public void setUp() throws Exception {
        p = new Product[DEFAULT_COUNT_PRODUCTS];
        s = new Salesman("Andry1", "Andry", "1234567");
        b = new Bill(s);
        for (int i = 0; i < p.length; i++) {
            p[i] = Utils.generateProduct();
        }
    }

    @Test
    public void getId() throws Exception {
        assertNotEquals(0, b.getId());
    }

    @Test
    public void getNumProd() throws Exception {
        b.addProduct(p[0]);
        b.addProduct(p[1]);
        b.closeBill();
        assertEquals(2, b.getNumProd());
    }

    @Test
    public void getAmountPrice() throws Exception {

        assertNotEquals(0, b.getAmountPrice());
    }

    @Test
    public void closeBill() throws Exception {
        b.addProduct(p[0]);
        b.addProduct(p[1]);
        assertTrue(b.closeBill());
    }

}