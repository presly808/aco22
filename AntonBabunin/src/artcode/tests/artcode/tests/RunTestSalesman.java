package artcode.tests;

import artcode.shop.bill.Bill;
import artcode.shop.creator.ProductGeneration;
import artcode.shop.creator.TerminalCreator;
import artcode.shop.product.Product;
import artcode.shop.salesman.Salesman;
import artcode.shop.creator.SalesmanCreator;
import artcode.shop.terminal.Terminal;
import org.junit.Assert;
import org.junit.Test;


public class RunTestSalesman {


    @Test
    public void testSalesmanCreation() {
        Salesman actual = SalesmanCreator.salesmanCreateAutomatic();
        Salesman expected = new Salesman(actual.getFullName(), "asd", "asd");
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

    @Test
    public void testSalesmanCreateBill() {
        Salesman salesman = SalesmanCreator.salesmanCreateAutomatic();
        Terminal terminal = TerminalCreator.terminalCreation();
        salesman.loginAutomatic(terminal);
        Bill actual = salesman.createBill(terminal);
        Bill expected = new Bill(actual.getId(), salesman);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testSalesmanCreateBill2() {
        Salesman salesman = SalesmanCreator.salesmanCreateAutomatic();
        Terminal terminal = TerminalCreator.terminalCreation();
        boolean bol1 = salesman.loginAutomatic(terminal);
        Bill actual = salesman.createBill(terminal);
        Bill expected = new Bill(10, salesman);
        Assert.assertFalse(expected.equals(actual));
    }

    @Test
    public void testSalesmanCreateBill3() {
        Salesman salesman = SalesmanCreator.salesmanCreateAutomatic();
        Terminal terminal = TerminalCreator.terminalCreation();
        salesman.loginAutomatic(terminal);
        Bill actual = salesman.createBill(terminal);
        Product product = ProductGeneration.generateProduct();
        Product product1 = ProductGeneration.generateProduct();
        salesman.addProduct(terminal, product);
        salesman.addProduct(terminal, product1);
        Salesman salesman1 = new Salesman("asdq", "asd", "asd");
        boolean b = salesman1.loginAutomatic(terminal);
        Bill expected = salesman1.createBill(terminal);
        expected.addProduct(product);
        expected.addProduct(product1);
        Assert.assertFalse(expected.equals(actual));
    }


//    countCreatedBill


}
