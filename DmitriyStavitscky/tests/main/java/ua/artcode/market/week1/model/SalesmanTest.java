package main.java.ua.artcode.market.week1.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SalesmanTest {
    @Test
    public void getFullName() throws Exception {
        Salesman salesman = new Salesman("Dima");
        salesman.setSumOfAllSales(67.6);
        boolean res = 67.6 == salesman.getSumOfAllSales();
        assertTrue(res);
    }

}