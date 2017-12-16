package week3.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week3.appdb.IappDB;
import week3.appdb.IappDBimpl;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

public class ITerminalimplTest {
    public ITerminal t1;
    public IappDB db = new IappDBimpl();
    public Salesman s1, s2, s3, s4;
    public Product p1, p2, p3;
    public Bill b1, b2, b3;

    @Before
    public void initData() {
        s1 = new Salesman(7, "sss1", "ddd4", "Fullname1");
        s2 = new Salesman(8, "sss2", "ddd6", "Fullname2");

        s3 = new Salesman(9, "sss3", "ddd4", "Fullname3");
        s4 = new Salesman(4, "sss4", "ddd5", "Fullname4");

        // m1  = new Salesman( 1, "Manager", "30FF76v", "Manager1", Arrays.asList(s1,s2) );
        // m2 = new Salesman(2,"Man2", "1234567", "Manager2",Arrays.asList(s3,s4));
        // d = new Salesman(5,"Director", "dir123", "Starshoy", Arrays.asList(m1,m2));

        p1 = new Product(1, "Grecha", 25.0);
        p2 = new Product(2, "Sampo", 45.0);
        p3 = new Product(3, "Banana", 22.0);
        b1 = new Bill();

        IappDBimpl db = new IappDBimpl();
        b1 = new Bill();
        b2 = new Bill();
        b3 = new Bill();
        ITerminal tr = new ITerminalimpl(db, s1);
        t1 = new ProxyLogTerminal(tr) {
        };

    }

    @Test
    public void login() {
        t1.createSalesMan("andrii", "304FF76v", "Andrii Smikhun");
        Assert.assertTrue(t1.login("andrii", "304FF76v"));
        Assert.assertFalse(t1.login("andrii", "304FF76"));
    }

    @Test
    public void logOut() {
        t1.login("andrii", "304FF76v");
        Assert.assertTrue(t1.logOut());
    }

    @Test
    public void addProduct() {
        b1.setId(1);
        t1.openBill(b1);
        Assert.assertTrue(t1.addProduct(1, p1));

    }

    @Test
    public void deleteProduct() {
        b1.setId(1);
        t1.openBill(b1);
        t1.addProduct(1, p1);
        //Assert.assertTrue(t1.deleteProduct(1,p1));
        Assert.assertFalse(t1.deleteProduct(1, p2));
    }

    @Test
    public void openBill() {
        Assert.assertTrue(t1.openBill(b1));
    }

    @Test
    public void closeBill() {
        t1.openBill(b1);
        Assert.assertTrue(t1.closeBill(b1));
    }

    @Test
    public void createSalesMan() {
        Assert.assertTrue(t1.createSalesMan("andrii", "304FF76v", "Andrii Smikhun"));
    }


}