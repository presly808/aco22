package src.test.main.java.ua.artcode.market.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SalesmanTest {
    @Test
    public void getFullName() throws Exception {
        Salesman salesman = new Salesman("Olia","Olia96","123qwerty",8,167.8);
        boolean res = 167.8 = Salesman.getamountOfAllSales();

        assertTrue(res);
    }

}