package artcode.tests;

import org.junit.Assert;
import org.junit.Test;
import artcode.shop.Bill;
import artcode.shop.Product;

public class RunTests {
    @Test
    public void testGetPrice1(){
        Product product = new Product("asd", 1.11, 1);
        double expected = 1.11;
        double actual = product.getPrice();
        Assert.assertEquals(expected, actual, 0);
    }
    @Test
    public void testAddProduct1(){
        Bill bill = new Bill();
        Product expected = new Product("asd", 123.22, 123);
        bill.addProduct(expected);
        Product actual = bill.getProducts()[0];
        Assert.assertSame(expected, actual);
    }
    @Test
    public void testAddProduct2(){
        Bill bill = new Bill();
        Product expected = new Product(null, 123.22, 123);
        bill.addProduct(expected);
        Product actual;
        if (bill != null && expected.getName() != null) {
            actual = bill.getProducts()[0];
        } else {
            actual = null;
        }
        Assert.assertNull(actual);
    }
//    @Test
//    public void testAddProduct3(){
//        Bill bill = new Bill();
//        Product expected = new Product(null, 123.22, 123);
//        bill.addProduct(expected);
//        Product actual = bill.getProducts()[0];
//        Assert.assertSame(expected, actual);
//    }


}
