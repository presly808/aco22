package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesManTest {

    @Test
    public void createProduct() throws Exception {
        SalesMan newSalesMan = new SalesMan("fullName");

        Assert.assertEquals("fullName", newSalesMan.fullName);
        //Assert.assertEquals( "login", newSalesMan.login);
        //Assert.assertEquals( "pass", newSalesMan.pass);
    }
}