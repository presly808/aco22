package week1;

import org.junit.Test;
import org.junit.Assert;
import week1.models.Product;

public class ProductTest {

    Product testProduct1 = new Product("Milk", 11.20, "#03242341");

    @Test
    public void testProductCreation() {
        Product actual = null;
        Product expected = new Product("testName", 12.2, "#022");
        Assert.assertFalse(expected.equals(actual));
    }

}
