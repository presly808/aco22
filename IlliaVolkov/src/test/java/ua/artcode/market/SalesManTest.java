package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.Model.SalesMan;

public class SalesManTest {

    @Test
    public void createProduct() throws Exception {
        SalesMan newSalesMan = new SalesMan("fullName");

        Assert.assertEquals("fullName", newSalesMan.getFullName());
        //Assert.assertEquals( "login", newSalesMan.login);
        //Assert.assertEquals( "pass", newSalesMan.pass);
    }
}