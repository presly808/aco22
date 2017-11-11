package tests;


import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.product.Product;

import static ua.artcode.market.creator.ProductGeneration.generateProduct;


public class RunTestProduct {
    @Test
    public void testProductCreation() {
        Product actual = generateProduct();
        Product expected = new Product(actual.getId(), actual.getName(), actual.getPrice());
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testProductCreation1() {
        Product actual = null;
        Product expected = new Product(1, "1", 0.1);
        Assert.assertFalse(expected.equals(actual));
    }

    @Test
    public void testProductCreation12() {
        Product actual = generateProduct();
        Product expected = generateProduct();
        Assert.assertFalse(expected.equals(actual));
    }



}
