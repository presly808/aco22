package logicTests;

import org.junit.Assert;
import utils.StringGenerator;
import utils.TerminalUtils;
import models.Product;
import org.junit.Before;
import org.junit.Test;


public class ProductTest {

    private Product product;

    @Before
    public void prepareData(){
        product = new Product(TerminalUtils.longIdGenerator(),
                StringGenerator.generateName(), Math.random());
    }

    @Test
    public void testPrintFullInfoMethod(){
        product.printFullInfo();
        Assert.assertTrue(true);
    }
}
