package week1.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import week1.data.Product;

public class ProductTest {

    Product testProduct1 = new Product("Milk", 11.20, "#03242341");
    Product testProduct2 = new Product("Cheese", 2.05, "#0341");
    Product testProduct3 = new Product("Water", 33.5, "#01");
    Product testProduct4 = new Product("Cake", 7.55, "#222");

    @Test
    public void testShowInfo() {
        String expected = "Product \"Milk\", price: 11,20, barcode: #03242341";
        String actual = testProduct1.showInfo();
        Assert.assertEquals(actual, expected);
    }

}

