import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by ENIAC on 16.11.2017.
 */
public class ProductTest {

    Product testProduct;

    @Test
    public void printFullInfo() throws Exception {

        testProduct = new Product("carrot", 4.75, "001");

        Assert.assertEquals("carrot          4,75     001", testProduct.printFullInfo());
    }

}