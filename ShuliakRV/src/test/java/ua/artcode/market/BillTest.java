package ua.artcode.market;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.models.AppDB;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Utils;

import static org.junit.Assert.*;

public class BillTest {

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
        Bill b = new Bill(appDB.getSales()[0]);;
        b.addProduct(appDB.getProducts()[0]);
        assertNotEquals(0, b.getAmountPrice());
    }

    @Test
    public void closeBill() throws Exception {
        AppDB appDB = new AppDB();
        Bill b = new Bill(appDB.getSales()[0]);;
        b.addProduct(appDB.getProducts()[0]);
        b.addProduct(appDB.getProducts()[1]);
        assertTrue(b.closeBill());
    }

}