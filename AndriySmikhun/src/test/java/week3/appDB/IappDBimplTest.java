package week3.appDB;

import org.junit.*;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.Arrays;
import java.util.List;

public class IappDBimplTest {

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
    }

    @After
    public void afterData()throws Exception{

    }

    @Test
    public void getBills() throws Exception{

    }

    @Test
    public void updateBill() {
        db.saveBill(b1);
        Bill b5 = new Bill();
        Assert.assertTrue(db.updateBill(b5,b1));


    }

    @Test
    public void removeBill1() {
        db.saveBill(b1);
        Bill b5 = new Bill();
        Assert.assertTrue(db.removeBill(b1));
        Assert.assertFalse(db.removeBill(b3));
    }

    @Test
    public void findeBillByID1() {
        db.saveBill(b1);
        b1.setId(1);
        Assert.assertEquals(b1,db.findeBillByID(1));
        Assert.assertNotEquals(b2,db.findeBillByID(1));
    }

    @Test
    public void saveBill1()  {
        Bill bill = new Bill();
        Assert.assertTrue(db.saveBill(bill));

    }

    @Test
    public void findSalemanById() {
        db.saveSaleman(s1);
        s1.setId(5);
        Assert.assertEquals(s1,db.findSalemanById(5));
    }

    @Test
    public void saveSaleman1() {
        Assert.assertTrue(db.saveSaleman(s1));
    }

    @Test
    public void findSalemanByName() {
        s1.setFullName("Andrii");
        db.saveSaleman(s1);
        Assert.assertEquals(s1,db.findSalemanByName("Andrii"));
    }

    @Test
    public void removeSaleman1() {
        db.saveSaleman(s3);
        Assert.assertTrue(db.removeSaleman(s3));

    }

    @Test
    public void updateSalemen1() {
        db.saveSaleman(s3);
        Assert.assertTrue(db.updateSalemen(s1,s3));
    }

}