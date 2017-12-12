package week3.controller;

import org.junit.Before;
import org.junit.Test;
import week1.controller.Terminal;
import week3.appDB.IappDB;
import week3.appDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StatisticTest {
  /*  IappDB db = new IappDBimpl();
    Bill b1, b2, b3;
    Product p1, p2, p3;
    Salesman s1, s2, s3, s4, m1, m2, d;
    ITerminal t1;

    @Before
    public void setUp() {



        s1 = new Salesman(7,"sss1","ddd4", "Fullname1", Arrays.asList());
        s2 = new Salesman(8,"sss2","ddd6", "Fullname2", Arrays.asList());

        s3 = new Salesman(9,"sss3","ddd4", "Fullname3", Arrays.asList());
        s4 = new Salesman(4,"sss4","ddd5", "Fullname4", Arrays.asList());

        m1  = new Salesman( 1, "Manager", "30FF76v", "Manager1", Arrays.asList(s1,s2) );
        m2 = new Salesman(2,"Man2", "1234567", "Manager2",Arrays.asList(s3,s4));
        d = new Salesman(5,"Director", "dir123", "Starshoy", Arrays.asList(m1,m2));

        p1 = new Product(1, "Grecha", 25.0);
        p2 = new Product(2,"Sampo",45.0);
        p3 = new Product(3,"Banana" , 22.0);



        b1 = new Bill();
        db.saveBill(b1);
        b1.setId(1);
        t1.addProduct(1,p1);
        t1.addProduct(1,p2);
        t1.closeBill(b1);

        b2 = new Bill();
        db.saveBill(b2);
        b1.setId(2);
        t1.addProduct(2,p3);
        t1.addProduct(2,p2);
        t1.closeBill(b2);

        b3 = new Bill();
        db.saveBill(b3);
        b1.setId(2);
        t1.addProduct(2,p3);
        t1.addProduct(2,p1);
        t1.closeBill(b3);



    }

    @Test
    public void treeMan() throws Exception {
        Statistic.treeMan(db, d);
    }

    @Test
    public void sumAllBillBySalesman() throws Exception {
    }*/

}