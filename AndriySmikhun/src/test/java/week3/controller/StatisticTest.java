package week3.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week3.appdb.IappDB;
import week3.appdb.IappDBimpl;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

public class StatisticTest {

    public IappDB db;
    public Bill b1, b2, b3;
    public Product p1, p2, p3;
    public Salesman s1, s2, s3, s4, m1, m2, d;
    public ITerminal t1;

    @Before
    public void setUp() {


        s1 = new Salesman(7, "sss1", "ddd4", "Fullname1");
        s2 = new Salesman(8, "sss2", "ddd6", "Fullname2");

        s3 = new Salesman(9, "sss3", "ddd4", "Fullname3");
        s4 = new Salesman(4, "sss4", "ddd5", "Fullname4");

        m1 = new Salesman(1, "Manager", "30FF76v", "Manager1");
        m2 = new Salesman(2, "Man2", "1234567", "Manager2");
        d = new Salesman(5, "Director", "dir123", "Starshoy");

        p1 = new Product(1, "Grecha", 25.0);
        p2 = new Product(2, "Sampo", 45.0);
        p3 = new Product(3, "Banana", 22.0);
        db = new IappDBimpl();
        t1 = new ITerminalimpl(db, s1);

    }

    @Test
    public void treeMan() {
        b1 = new Bill();
        db.saveBill(b1);
        b1.setId(1);
        t1.addProduct(1, p1);
        t1.addProduct(1, p2);
        t1.closeBill(b1);
        Assert.assertEquals(0.0, Statistic.treeMan(db, d), 1);
    }

    @Test
    public void sumAllBillBySalesman() {
        Assert.assertEquals(0.0, Statistic.sumAllBillBySalesman(db, s1), 1);

    }

}