package ua.artcode.market.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void convertToStringFullInfo() throws Exception {
        Product product = new Product("Milk", 10, 5.50);
        String expected = "name: Milk, id: 10, price: 5,50";
        assertTrue(product.toString().contains("Milk"));
    }

    @Test
    public void productEquals() throws Exception {
        Product product1 = new Product("Meat", 15, 7);
        Product product2 = new Product("Meat", 15, 7);

        assertTrue(product1.equals(product2));
    }

}