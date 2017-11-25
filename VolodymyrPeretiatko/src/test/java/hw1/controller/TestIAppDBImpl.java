package hw1.controller;

import hw1.model.Bill;
import hw1.model.DBItem;
import hw1.model.Product;
import hw1.model.Salesman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestIAppDBImpl {

    private IAppDB appDB;

    private Bill billExp;
    private Salesman salesmanExp;
    private Product productExp;

    @Before
    public void init(){
        appDB = new IAppDBImpl();

        billExp = new Bill(1);

        System.out.println(billExp);

        salesmanExp = new Salesman(1);
        salesmanExp.setFullName("Adolf");
        salesmanExp.setName("Adik");
        salesmanExp.setPass("qwerty");

        productExp = new Product(1);
        productExp.setName("HP 9000");
        productExp.setPrice(250);

    }

    @Test
    public void testCreate(){
        Bill billAct = (Bill) appDB.create(Bill.class);
        Assert.assertEquals(billExp, billAct);

        Salesman salesmanAct = (Salesman) appDB.create(Salesman.class);
        Assert.assertEquals(salesmanExp.getId(), salesmanAct.getId());

        Product productAct = (Product) appDB.create(Product.class);
        Assert.assertEquals(new Product(1), productAct);
    }

    @Test
    public void testSave(){
        appDB.save(billExp);
        Assert.assertTrue(appDB.getAll(Bill.class).contains(billExp));

        appDB.save(salesmanExp);
        Assert.assertTrue(appDB.getAll(Salesman.class).contains(salesmanExp));

        appDB.save(productExp);
        Assert.assertTrue(appDB.getAll(Product.class).contains(productExp));

        Assert.assertTrue(null == appDB.save(new DBItem()));
    }

    @Test
    public void testFindById(){
        appDB.save(billExp);
        Assert.assertEquals(billExp, (Bill) appDB.findById(1, Bill.class));

        appDB.save(salesmanExp);
        Assert.assertEquals(salesmanExp, (Salesman) appDB.findById(1, Salesman.class));

        appDB.save(productExp);
        Assert.assertEquals(productExp, (Product) appDB.findById(1, Product.class));
    }




}
