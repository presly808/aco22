package ua.artcode.market;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void getPrice() throws Exception {
        Product p = new Product("Хлеб", 10);
        assertEquals(10, (int) p.getPrice());
    }


}