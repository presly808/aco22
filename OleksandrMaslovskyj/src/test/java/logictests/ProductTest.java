package logictests;

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
        String printedInfo = product.printFullInfo();
        System.out.println(printedInfo);
        Assert.assertTrue(printedInfo.contains("id"));
        Assert.assertTrue(printedInfo.contains("name"));
        Assert.assertTrue(printedInfo.contains("price"));
    }
}
