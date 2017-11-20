package week1.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class BillTest {
    Product product1, product2;
    String myDataTime1, myDataTime2;
    Bill bill1, bill2, bill3;
    Salesman salesman1, salesman2;


    @Before
    public void initData() {

        product1 = new Product(1,"Grecha", 40.00);
        product2 = new Product(2,"juse", 20.00);


        salesman1 = new Salesman("Marusia", "maria", "pass", true);
        salesman2 = new Salesman("Oleg", "oleg", "pass", false);

        bill1 = new Bill(1, salesman1);
        bill2 = new Bill(2, salesman2);
        bill3 = new Bill(3, salesman2);

        myDataTime1 = "17:10:10 23/7";
        myDataTime2 = "22:10:10 27/7";




    }
    @Test
    public void getProducts() {
        Assert.assertEquals(bill1.getProducts(),bill1.getProducts());
    }

    @Test
    public void getSalesman() {
        Assert.assertEquals(bill1.getSalesman(),bill1.getSalesman());
    }

    @Test
    public void getAmountPrice() {
        Assert.assertEquals(bill1.getAmountPrice(),bill1.getAmountPrice(),1);
    }

    @Test
    public void getDataTime() {
        Assert.assertEquals(bill1.getDataTime(),bill1.getDataTime());
    }

    @Test
    public void addProduct1(){
        Assert.assertTrue(bill1.addProduct(product1));
    }

    @Test
    public void calculateAmountPrice() {
        bill1.products[0] = product1;
        bill1.products[1] = product2;
        double sum = bill1.calculateAmountPrice();
        Assert.assertEquals(60.0,sum,1);
    }

    @Test
    public void printBill()  {
        Assert.assertTrue(bill1.setCloseBill());
    }

    @Test
    public void compareTo()  {
        Assert.assertEquals(product1.compareTo(product1),0);

    }

    @Test
    public void setCloseBill() {
        Assert.assertTrue(bill1.setCloseBill());
    }

}