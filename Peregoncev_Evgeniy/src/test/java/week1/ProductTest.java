package week1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 16.11.2017.
 */
public class ProductTest {


    @Test
    public void printFullInfo() throws Exception {

        Product testProduct = new Product();

        testProduct.setName("carrot");
        testProduct.setPrice(4.75);
        testProduct.setBarcode("001");

        String expected = String.format("%-15s %-8.2f %s", testProduct.getName(), testProduct.getPrice(), testProduct.getBarcode());


        assertEquals(expected, testProduct.printFullInfo());
    }

}