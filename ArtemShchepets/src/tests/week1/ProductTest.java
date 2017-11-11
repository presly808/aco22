package week1;


import org.junit.Test;
import org.junit.Assert;

public class ProductTest {

    Product testProduct1 = new Product("Milk", 11.20, "#03242341");

    @Test
    public void testShowInfo() {
        String expected = "Product \"Milk\", price: 11,20, barcode: #03242341";
        String actual = testProduct1.showInfo();
        Assert.assertEquals(actual, expected);
    }

}
