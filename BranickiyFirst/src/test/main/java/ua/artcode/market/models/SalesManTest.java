package main.java.ua.artcode.market.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SalesManTest {
    @Test
    public void getFullName() throws Exception {
        SalesMan salesMan = new SalesMan("Olia","Olia96","123qwerty",8,167.8);
        boolean res = 167.8 = SalesMan.getamountOfAllSales();

        assertTrue(res);
    }

}