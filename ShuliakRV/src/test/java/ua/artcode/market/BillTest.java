package ua.artcode.market;

import org.junit.Test;
import ua.artcode.market.models.*;

import static org.junit.Assert.*;

public class BillTest {
    @Test
    public void addProduct() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);
        assertTrue(b.addProduct(appDB.getProducts()[0]));
    }

    @Test
    public void getId() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);
        b.addProduct(appDB.getProducts()[0]);
        b.addProduct(appDB.getProducts()[1]);
        b.closeBill();
        assertTrue(b.closeBill());
    }

    @Test
    public void getNumProd() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);
        b.addProduct(appDB.getProducts()[0]);
        b.addProduct(appDB.getProducts()[1]);
        b.closeBill();
        assertEquals(2, b.getNumProd());
    }

    @Test
    public void getAmountPrice() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);
        b.addProduct(appDB.getProducts()[0]);
        b.closeBill();
        assertEquals(appDB.getProducts()[0].getPrice(), b.getAmountPrice(), 0.1);
    }

    @Test
    public void closeBill() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);
        b.addProduct(appDB.getProducts()[0]);
        b.addProduct(appDB.getProducts()[1]);
        assertTrue(b.closeBill());
    }

}