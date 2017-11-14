package tests;


import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.bill.Bill;
import ua.artcode.market.creator.ProductGeneration;
import ua.artcode.market.creator.SalesmanCreator;
import ua.artcode.market.creator.TerminalCreator;
import ua.artcode.market.product.Product;
import ua.artcode.market.salesman.Salesman;
import ua.artcode.market.terminal.Terminal;


public class RunTestSalesman {


    @Test
    public void testSalesmanCreation() {
        Salesman actual = SalesmanCreator.createSalerman();
        Salesman expected = new Salesman(actual.getFullName(), "asd", "asd");
        Assert.assertTrue(actual.equals(expected));
    }
    @Test
    public void testSalesmanCreation1() {
        Salesman actual = SalesmanCreator.createSalerman();
        Salesman expected = new Salesman("asdd", "asd", "asd");
        Assert.assertFalse(actual.equals(expected));
    }

    @Test
    public void testSalesmanCreation2()  {
        Salesman actual = SalesmanCreator.createSalerman();
        Salesman expected = new Salesman(null,null,null);
        Assert.assertFalse(actual.equals(expected));
    }

 /*   @Test
    public void testSalesmanCreation3()  {
        Salesman actual = SalesmanCreator.createSalerman();
        Assert.assertFalse(actual.equals(null));
    }*/

    @Test
    public void testSalesmanCreateBill() {
        Salesman salesman = SalesmanCreator.createSalerman();
        Terminal terminal = TerminalCreator.terminalCreation();
        salesman.login(terminal);
        Bill actual = salesman.createBill(terminal);
        Bill expected = new Bill(actual.getId(), salesman);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testSalesmanCreateBill2() {
        Salesman salesman = SalesmanCreator.createSalerman();
        Terminal terminal = TerminalCreator.terminalCreation();
        salesman.login(terminal);
        Bill actual = salesman.createBill(terminal);
        Bill expected = new Bill(10, salesman);
        Assert.assertFalse(expected.equals(actual));
    }

    @Test
    public void testSalesmanCreateBill3() {
        Salesman salesman = SalesmanCreator.createSalerman();
        Terminal terminal = TerminalCreator.terminalCreation();
        salesman.login(terminal);
        Bill actual = salesman.createBill(terminal);
        Product product = ProductGeneration.generateProduct();
        Product product1 = ProductGeneration.generateProduct();
        salesman.addProduct(terminal, product);
        salesman.addProduct(terminal, product1);
        actual.closeBill();
        Salesman salesman1 = new Salesman("asdq", "asd", "asd");
        salesman1.login(terminal);
        Bill expected = salesman1.createBill(terminal);
        expected.addProduct(product);
        expected.addProduct(product1);
        Assert.assertFalse(expected.equals(actual));
    }


//    countCreatedBill


}
