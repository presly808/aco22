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
    public void testAddProduct(){
        Bill bill = new Bill();
        Product expected = new Product("asd", 123.22, 123);
        bill.addProduct(expected);
//        Product product = new Product("asd", 1.11, 1);
        Product actual = bill.getProducts()[0];
        Assert.assertSame(expected, actual);
    }

}
