package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.SalesmanController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

import java.util.Arrays;

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
        Bill bill = null;
        boolean bool = sc.createBill(terminal, bill);
//        System.out.printf("testCreateBillNull1: Result = %s | Terminal = %s | Bill = null \n", bool, terminal);
        Assert.assertFalse(bool);
    }

    @Test
    public void testCreateBillNull2() {
        Terminal terminal = null;
        Bill bill = new Bill();
        boolean bool = sc.createBill(terminal, bill);
//        System.out.printf("testCreateBillNull2: Result = %s | Terminal = null | Bill = %s \n", bool, bill);
        Assert.assertFalse(bool);
    }

    @Test
    public void testCreateBillNotNull() {
        Terminal terminal = new Terminal();
        Bill bill = new Bill();
        boolean bool = sc.createBill(terminal, bill);
//        System.out.printf("testCreateBillNotNull: Result = %s | Terminal = %s | Bill = %s \n", bool, terminal, bill);
        Assert.assertNotEquals(0, terminal.getBills().get(0).getBillId());
    }


    @Test
    public void testAddProductNull1() {
        Terminal terminal = null;
        Bill bill = new Bill();
        Product product = new Product();
        boolean bool = sc.addProduct(terminal,bill,product);
//        System.out.printf("testAddProductNull1: Result = %s | Terminal = null | Bill = %s | Product = %s \n", bool, bill, product);
        Assert.assertFalse(bool);
    }

    @Test
    public void testAddProductNullAll() {
        Terminal terminal = null;
        Bill bill = null;
        Product product = null;
        boolean bool = sc.addProduct(terminal,bill,product);
//        System.out.printf("testAddProductAllNull: Result = %s | Terminal = %s | Bill = %s | Product = %s \n", bool, terminal, bill, product);
        Assert.assertFalse(bool);
    }

    @Test
    public void testAddProductSomePeaces() {
        Terminal terminal = new Terminal();
        Bill bill = new Bill();
        Product product1 = new Product();
        Product product2 = new Product();
        boolean booll = sc.addProduct(terminal,bill,product1);
        boolean bool2 = sc.addProduct(terminal,bill,product2);
        boolean bool = booll && bool2;
        System.out.printf("bool1 = %s bool1 = %s \n", booll, bool2);
        System.out.printf("testAddProductSomePeaces: Result = %s | %s | %s | %s %s\n", bool, terminal, bill, product1, product2);
        Assert.assertFalse(Arrays.equals(bill.getProducts().keySet().toArray(), new Product[]{null, null}));
    }


}
