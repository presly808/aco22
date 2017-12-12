package week3.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week3.appDB.IappDB;
import week3.appDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ITerminalimplTest {
    ITerminal t1;
    IappDB db = new IappDBimpl();
    Salesman s1, s2, s3, s4, m1, m2, d;
    Product p1,p2,p3,p4,p5;
    Bill b1, b2, b3;

    @Before
    public void initData(){
       s1 = new Salesman(7,"sss1","ddd4", "Fullname1");
        s2 = new Salesman(8,"sss2","ddd6", "Fullname2");

        s3 = new Salesman(9,"sss3","ddd4", "Fullname3");
        s4 = new Salesman(4,"sss4","ddd5", "Fullname4");

        //m1  = new Salesman( 1, "Manager", "30FF76v", "Manager1", Arrays.asList(s1,s2) );
       // m2 = new Salesman(2,"Man2", "1234567", "Manager2",Arrays.asList(s3,s4));
       // d = new Salesman(5,"Director", "dir123", "Starshoy", Arrays.asList(m1,m2));

        p1 = new Product(1, "Grecha", 25.0);
        p2 = new Product(2,"Sampo",45.0);
        p3 = new Product(3,"Banana" , 22.0);
        b1 = new Bill();

        IappDBimpl db = new IappDBimpl();
        b1 = new Bill();
        b2 = new Bill();
        b3 = new Bill();

        t1 = new ITerminalimpl(db);
    }

    @Test
    public void login()  {
        t1.createSalesMan("andrii","304FF76v","Andrii Smikhun");
        Assert.assertTrue(t1.login("andrii","304FF76v"));
        Assert.assertFalse(t1.login("andrii","304FF76"));


    }

    @Test
    public void addProduct() {
        b1.setId(1);

        Assert.assertTrue(t1.addProduct(1,p1));

    }

    @Test
    public void deleteProduct() throws Exception {
    }

    @Test
    public void newBill()throws Exception{
        Assert.assertTrue(t1.newBill());
    }

    @Test
    public void filterBill() throws Exception {
    }

    @Test
    public void closeBill() throws Exception {
    }

    @Test
    public void createSalesMan() {
        Assert.assertTrue(t1.createSalesMan("andrii","304FF76v","Andrii Smikhun"));
    }

    @Test
    public void getTopSalesman() throws Exception {
    }

}