package ua.artcode.market.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by serhii on 07.11.17.
 */
public class Test1 {
    @Test
    public void test1(){
        Product product = new Product();
        product.setAge(23);
        product.setName("Product");

        Assert.assertEquals(23, product.getAge());
        Assert.assertEquals("Product", product.getName());
    }
}
