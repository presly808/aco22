package hw1.controller;

import hw1.model.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TerminalTest {

    private ITerminal terminal;

    private Bill b1, b2;
    private Product p1, p2;
    private Salesman s1, s2;


    @Before
    public void initTestData(){

        s1 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        s2 = new Salesman("Frank Sinatra", "Frank", "qwerty");

        p1 = new Product(1, "Laptop HP 750B",  500.0);
        p2 = new Product(2, "Laptop HP 1020Z", 650.0);

        s1 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        s2 = new Salesman("Frank Sinatra", "Frank", "qwerty");

        p1 = new Product(1, "Laptop HP 750B",  500.0);
        p2 = new Product(2, "Laptop HP 1020Z", 650.0);

        b1 = new Bill(1, s1);
        b2 = new Bill(2, s2);

        b1.addProduct(p1);

        b2.addProduct(p1);
        b2.addProduct(p2);

        terminal = new ProxyLoggerTerminal();

        terminal.addProduct(p1);
        terminal.addProduct(p2);

        terminal.addSalesman(s1);
        terminal.addSalesman(s2);

        terminal.closeAndSaveBill(b1);
        b1.setCloseTime(new Date(1510842400007L));
        terminal.closeAndSaveBill(b2);
        b2.setCloseTime(new Date(1510842400777L));
    }

    @Test
    public void getSalesmanSallaryTest(){
        s1.addSubSalesman(s2);
        System.out.println(terminal.getSalesmanSallary(s1, new Date(1510842400000L), new Date(1510842400777L)));
    }

    @Test
    public void testLogin(){
        Assert.assertTrue(terminal.login("Jhon", "qwerty"));
        Assert.assertTrue(terminal.login("Frank", "qwerty"));
        Assert.assertFalse(terminal.login("HuiChi", "qwerty"));
    }

    @Test
    public void testFindBillById(){

        Assert.assertEquals(b1, terminal.findBillById(1));
        Assert.assertEquals(b2, terminal.findBillById(2));

    }

    @Test
    public void testGetTopNofSalesMan(){
        Assert.assertEquals(s2, terminal.getTopNofSalesMan());
    }

    @Test
    public void testCreateBill(){
        Bill b = terminal.createBill();
        b.setSalesman(s2);
        Assert.assertTrue(b.getCloseTime() == null);
    }

    @Test
    public void testAddSalesman(){
        Assert.assertTrue(terminal.addSalesman(s1));
    }

    @Test
    public void testFilter(){

        ArrayList<Bill> expected = new ArrayList<>();
        expected.add(b1);
        expected.add(b2);

        List<Bill> actual = terminal.filter(terminal.getSalesmen(), terminal.getProducts(),
                new Date(1510842400000L), new Date(1510842400999L), new Bill.SortByDateComparator());

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFilterbyDate(){
        p1.getId();
        ArrayList<Bill> expected = new ArrayList<>();
        expected.add(b2);

        List<Bill> actual = terminal.filter(terminal.getSalesmen(), terminal.getProducts(),
                new Date(1510842400077L), new Date(1510842400999L), new Bill.SortByDateComparator());

        Assert.assertEquals(expected, actual);

        expected.clear();
        expected.add(b1);

        actual = terminal.filter(terminal.getSalesmen(), terminal.getProducts(),
                new Date(1510842400007L), new Date(1510842400667L), new Bill.SortByDateComparator());

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFilterbySalesman(){

        List<Bill> expected = new ArrayList<>();
        expected.add(b1);

        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(s1);

        List<Bill> actual = terminal.filter(salesmen, terminal.getProducts(),
                new Date(1510842400000L), new Date(1510842400999L), new Bill.SortByDateComparator());

        Assert.assertEquals(expected, actual);

        expected.clear();
        expected.add(b2);

        salesmen.clear();
        salesmen.add(s2);

        actual = terminal.filter(salesmen, terminal.getProducts(),
                new Date(1510842400000L), new Date(1510842400999L), new Bill.SortByDateComparator());

        Assert.assertEquals(expected, actual);

    }





}
