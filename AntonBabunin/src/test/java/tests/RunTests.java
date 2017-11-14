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


public class RunTests {
    @Test
    public void testAllFunction () {
        Terminal terminal1 = TerminalCreator.terminalCreation();
        Product product1 = ProductGeneration.generateProduct();
        Product product2 = ProductGeneration.generateProduct();
        Product product3 = ProductGeneration.generateProduct();
        Product product4 = ProductGeneration.generateProduct();
        Product product5 = ProductGeneration.generateProduct();
        Product product6 = ProductGeneration.generateProduct();
        Salesman salesman1 = SalesmanCreator.salesmanCreateAutomatic();
        salesman1.loginAutomatic(terminal1);
        Bill bill1 = salesman1.createBill(terminal1);
        boolean b1 = salesman1.addProduct(terminal1, product1);
        System.out.println("b1 = " + b1);
        boolean b2 = salesman1.addProduct(terminal1, product2);
        System.out.println("b2 = " + b2);
        salesman1.addProduct(terminal1, product3);
        salesman1.addProduct(terminal1, product1);
        salesman1.addProduct(terminal1, product2);
        salesman1.addProduct(terminal1, product3);
        salesman1.addProduct(terminal1, product3);
        salesman1.addProduct(terminal1, product4);
        salesman1.addProduct(terminal1, product5);
        salesman1.addProduct(terminal1, product6);
        boolean billIsClosed = salesman1.closeBill(bill1, terminal1);
        Assert.assertTrue(billIsClosed);

    }

}