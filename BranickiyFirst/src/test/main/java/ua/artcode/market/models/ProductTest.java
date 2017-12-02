package main.java.ua.artcode.market.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void equals() throws Exception {
            Product product1 = new Product("Ice", 15, 7);
            Product product2 = new Product("Ice", 15, 7);

            assertTrue(product1.equals(product2));
    }

    @Test
    public void toString() throws Exception {
        Product product = new Product("Potato", 12.12, 8);
        String expected = "name: Potato, id: 8, price: 12.12";
        assertTrue(product.toString().contains("Potato"));
    }

}