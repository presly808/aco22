package ua.artcode.market.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void convertToStringFullInfo() throws Exception {
        Product product = new Product("Milk", 10, 5.50);
        String expected = "name: Milk, id: 10, price: 5,50";
        assertEquals(expected, product.convertToStringFullInfo());
    }

}