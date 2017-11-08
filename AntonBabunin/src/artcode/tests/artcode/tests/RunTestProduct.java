package artcode.tests;

import artcode.shop.product.Product;
import org.junit.Assert;
import org.junit.Test;

import static artcode.shop.product.ProductGeneration.generateProduct;

public class RunTestProduct {
    @Test
    public void testProductCreation() {
        Product actual = generateProduct();
        Product expected = new Product(actual.getId(), actual.getName(), actual.getPrice());
        System.out.println(actual.getPrice());
        Assert.assertTrue(equals(expected,actual));
    }

    public static boolean equals (Product product1, Product product2) {
        return product1.getId() == product2.getId() && product1.getName().equals(product2.getName()) &&
                product1.getPrice() == product2.getPrice();
    }
}
