package artcode.tests;

import artcode.shop.salesman.Salesman;
import artcode.shop.creator.SalesmanCreator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RunTestSalesman {


    @Test
    public void testSalesmanCreation() {
        Salesman actual = SalesmanCreator.salesmanCreateAutomatic();
        Salesman expected = new Salesman("asd", "asd", "asd");
        Assert.assertTrue(actual.equals(expected));
    }
    @Test
    public void testSalesmanCreation1() {
        Salesman actual = SalesmanCreator.salesmanCreateAutomatic();
        Salesman expected = new Salesman("asdd", "asd", "asd");
        Assert.assertFalse(actual.equals(expected));
    }

    @Test
    public void testSalesmanCreation2()  {
        Salesman actual = SalesmanCreator.salesmanCreateAutomatic();
        Salesman expected = new Salesman(null,null,null);
        Assert.assertFalse(actual.equals(expected));
    }

    @Test
    public void testSalesmanCreation3()  {
        Salesman actual = SalesmanCreator.salesmanCreateAutomatic();
        Assert.assertFalse(actual.equals(null));
    }
}
