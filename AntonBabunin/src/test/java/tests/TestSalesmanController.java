package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.controllers.SalesmanController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;
import ua.artcode.market.utils.BillComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestSalesmanController {
    private SalesmanController sc;

    @Before
    public void initData() {
        this.sc = new SalesmanController();
    }

    @After
    public void erraseData(){
        this.sc = null;
    }

    @Test
    public void testCreateBillNull1() {
        Terminal terminal = new Terminal();
        Salesman salesman = null;
        Bill expected = null;
        Bill actual = sc.createBill(terminal, salesman);
//        System.out.printf("testCreateBillNull1: Result = %s | Terminal = %s |
//  Bill = null \n", bool, terminal);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateBillNull2() {
        Terminal terminal = null;
        Salesman salesman = new Salesman("salesman1",
                "salesman1","salesman1");
        Bill expected = new Bill();
        Bill actual = sc.createBill(terminal, salesman);
//        System.out.printf("testCreateBillNull2: Result = %s | Terminal = null
//  | Bill = %s \n", bool, bill);
        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void testCreateBillNotNull() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("salesman1", "salesman1",
                "salesman1");
        Bill bill = sc.createBill(terminal, salesman);
//        System.out.printf("testCreateBillNotNull: Result = %s | " +
//                "Terminal = %s | " + "Bill = %s \n", bool, terminal, bill);
        Assert.assertNotEquals(0, bill.getBillId());
    }

    @Test
    public void testAddProductNull1() {
        Terminal terminal = null;
        Bill bill = new Bill();
        Product product = new Product();
        boolean bool = sc.addProduct(terminal,bill,product);
//        System.out.printf("testAddProductNull1: Result = %s | Terminal = null |
//  Bill = %s | Product = %s \n", bool, bill, product);
        Assert.assertFalse(bool);
    }

    @Test
    public void testAddProductNullAll() {
        Terminal terminal = null;
        Bill bill = null;
        Product product = null;
        boolean bool = sc.addProduct(terminal,bill,product);
//        System.out.printf("testAddProductAllNull: Result = %s | Terminal = %s |
//  Bill = %s | Product = %s \n", bool, terminal, bill, product);
        Assert.assertFalse(bool);
    }

    @Test
    public void testAddProductSomePeaces() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("salesman1", "salesman1",
                "salesman1");
        Bill bill = sc.createBill(terminal, salesman);
        Product product1 = new Product();
        Product product2 = new Product();
        sc.addProduct(terminal,bill,product1);
        sc.addProduct(terminal,bill,product2);
//        boolean bool = booll && bool2;
//        System.out.printf("testAddProductSomePeaces: Result = %s | %s\n", bool,
//                terminal);
        Assert.assertFalse(Arrays.equals(bill.getProducts().keySet().toArray(),
                new Product[]{null, null}));
    }

    @Test
    public void testCloseAndSafeBill() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("salesman1", "salesman1",
                "salesman1");
        Bill bill = sc.createBill(terminal, salesman);
        boolean bool = sc.closeAndSafeBill(terminal,bill);
//        System.out.printf("testCloseAndSafeBill: Result = %s | %s\n", bool,
//                terminal);
        Assert.assertTrue(bool);
    }

    @Test
    public void testCloseAndSafeBill1() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("salesman1", "salesman1",
                "salesman1");
        Bill bill = sc.createBill(terminal, salesman);
        Product product1 = new Product();
        Product product2 = new Product();
        sc.addProduct(terminal,bill,product1);
        sc.addProduct(terminal,bill,product2);
        boolean bool = sc.closeAndSafeBill(terminal,bill);
//        System.out.printf("testCloseAndSafeBill: Result = %s | %s\n", bool,
//                terminal);

        Assert.assertTrue(bool);
    }


    @Test
    public void testCreate() {
        Salesman salesman = sc.create("SalesMan1", "SalesMan1", "SalesMan1");
        Assert.assertNotNull(salesman);
    }

    @Test
    public void testLogin() {
        Terminal terminal = new Terminal();
        Salesman actual = sc.login(terminal,  null, null);
        Assert.assertNull(actual);
    }

    @Test
    public void testLogin_1() {
        Terminal terminal = new Terminal();
        Salesman actual = sc.login(terminal,  "sad", "asd");
        Assert.assertNull(actual);
    }

    @Test
    public void testLogin_2() {
        Salesman expected = sc.create("SalesMan1", "SalesMan1", "Sales");
        Terminal terminal = new Terminal();
        Salesman top = sc.create("top", "top", "top");
        sc.login(terminal, "top", "top");
        Salesman actual = sc.login(terminal,  "SalesMan1", "Sales");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCreateBill() {
        Bill expected = new Bill();
        Terminal terminal = new Terminal();
        Salesman salesman = sc.create("1","1","2");
        sc.login(terminal, salesman.getFullName(),salesman.getPassword());
        Bill actual = sc.createBill(terminal, salesman);
        Assert.assertNotSame(expected, actual);
    }

    @Test
    public void testCreateBill_1() {
        Terminal terminal = new Terminal();
        Salesman salesman = sc.create(null,"1","2");
        sc.login(terminal, "1","1");
        Bill actual = sc.createBill(terminal, salesman);
        Assert.assertNull(actual);
    }

    @Test
    public void testFindBillById_1() {
        Terminal terminal = new Terminal();
        Salesman salesman = sc.create("1","1","2");
        sc.login(terminal, "1","1");
        sc.createBill(terminal,salesman);
        sc.createBill(terminal,salesman);
        Bill expected = sc.createBill(terminal,salesman);
        sc.createBill(terminal,salesman);
        Bill actual = sc.findBillById(expected.getBillId());
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void testFindBillById_2() {
        Terminal terminal = new Terminal();
        Terminal test = new Terminal();
        Salesman salesman = sc.create("1","1","2");
        sc.login(terminal, "1","1");
        sc.createBill(terminal,salesman);
        sc.createBill(test,salesman);
        Bill expected = sc.createBill(test,salesman);
        sc.createBill(terminal,salesman);
        Bill actual = sc.findBillById(expected.getBillId());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testFindBillById_3() {
        Terminal terminal = new Terminal();
        Terminal test = new Terminal();
        Salesman salesman = sc.create("1","1","2");
        sc.login(terminal, "1","1");
        sc.createBill(terminal,salesman);
        sc.createBill(test,salesman);
        Bill expected = sc.createBill(test,salesman);
        sc.createBill(terminal,salesman);
        Bill actual = sc.findBillById(11);
        Assert.assertNotEquals(expected,actual);
    }
    @Test
    public void filterMethodAll() {
        Terminal terminal = new Terminal();
        Salesman salesman1 = sc.create("Saleerman1","Saleerman1","1");
        Salesman salesman2 = sc.create("Saleerman2","Saleerman2","1");
        Salesman salesman3 = sc.create("Saleerman3","Saleerman3","1");
        Salesman salesman4 = sc.create("Saleerman4","Saleerman4","1");

        sc.login(terminal, "Saleerman1","1");
        sc.login(terminal, "Saleerman2","1");
        sc.login(terminal, "Saleerman3","1");
        sc.login(terminal, "Saleerman4","1");

        sc.createBill(terminal,salesman1);
        sc.createBill(terminal,salesman1);
        sc.createBill(terminal,salesman1);
        sc.createBill(terminal,salesman1);
        sc.createBill(terminal,salesman1);

        sc.createBill(terminal,salesman2);
        sc.createBill(terminal,salesman2);
        sc.createBill(terminal,salesman2);

        sc.createBill(terminal,salesman3);
        sc.createBill(terminal,salesman3);
        sc.createBill(terminal,salesman3);
        sc.createBill(terminal,salesman3);

        Bill bill1 = sc.createBill(terminal,salesman4);
        Bill bill2 = sc.createBill(terminal,salesman4);
        Bill bill3 = sc.createBill(terminal,salesman4);
        Bill bill4 = sc.createBill(terminal,salesman4);
        Bill bill5 = sc.createBill(terminal,salesman4);
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        sc.addProduct(terminal, bill1, product1);
        sc.addProduct(terminal, bill2, product1);
        sc.addProduct(terminal, bill3, product1);
        sc.addProduct(terminal, bill4, product1);
        sc.addProduct(terminal, bill1, product2);
        sc.addProduct(terminal, bill2, product2);
        sc.addProduct(terminal, bill3, product2);
        sc.addProduct(terminal, bill5, product3);

        List<Bill> expected = new ArrayList<>();
        expected.add(bill5);

        List<Bill> actual = sc.filterMethodAll(salesman4, product3, null,
                null, null);
        new BillComparator().compare(bill1, bill2);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());



    }




}
