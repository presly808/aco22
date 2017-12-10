package week3.appDB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.controller.Terminal;
import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.Arrays;
import java.util.List;

public class IappDBimplTest {

    Terminal t1;
    IappDB db = new IappDBimpl();
    Salesman s1, s2, s3, s4, m1, m2, d;
    Product p1,p2,p3,p4,p5;
    Bill b1, b2, b3;

    @Before
    public void initData(){
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







        IappDBimpl db = new IappDBimpl(Arrays.asList(),Arrays.asList(),Arrays.asList());

    }

    @Test
    public void getBills() {
        List<Bill> bills = db.getBills();
        Assert.assertNotNull(bills);
    }

    @Test
    public void updateBill(){

    }

    @Test
    public void removeBill1() throws Exception {
    }

    @Test
    public void findeBillByID1() throws Exception {
    }

    @Test
    public void saveBill1()  {
        Bill bill = new Bill();
        Assert.assertTrue(db.saveBill(bill));

    }

    @Test
    public void findSalemanById() throws Exception {
    }

    @Test
    public void saveSaleman1() throws Exception {
    }

    @Test
    public void findSalemanByName() throws Exception {
    }

    @Test
    public void removeSaleman1() throws Exception {
    }

    @Test
    public void updateSalemen1() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllBill() throws Exception {
    }

    @Test
    public void getAllSalesman() throws Exception {
    }

    @Test
    public void getAllSaleman() throws Exception {
    }

    @Test
    public void getBill() throws Exception {
    }

    @Test
    public void updateDill() throws Exception {
    }

    @Test
    public void removeBill() throws Exception {
    }

    @Test
    public void findeBillByID() throws Exception {
    }

    @Test
    public void saveBill() throws Exception {
    }

    @Test
    public void getSaleman() throws Exception {
    }

    @Test
    public void saveSaleman() throws Exception {
    }

    @Test
    public void findSaleman() throws Exception {
    }

    @Test
    public void removeSaleman() throws Exception {
    }

    @Test
    public void updateSalemen() throws Exception {
    }

}