package artcode.tests;


import artcode.shop.bill.Bill;
import artcode.shop.creator.ProductGeneration;
import artcode.shop.creator.SalesmanCreator;
import artcode.shop.creator.TerminalCreator;
import artcode.shop.product.Product;
import artcode.shop.salesman.Salesman;
import artcode.shop.terminal.Terminal;
import org.junit.Test;

public class RunTests {
    @Test
    public void testAllFunction () {
        Terminal terminal1 = TerminalCreator.terminalCreation();
        Salesman salesman1 = SalesmanCreator.salesmanCreateAutomatic();
        Product product1 = ProductGeneration.generateProduct();
        Product product2 = ProductGeneration.generateProduct();
        Product product3 = ProductGeneration.generateProduct();

        salesman1.loginAutomatic(terminal1);

        Bill bill1 = salesman1.createBill(terminal1);
        salesman1.addProduct(terminal1, product1);
        salesman1.addProduct(terminal1, product2);
        salesman1.addProduct(terminal1, product3);



        salesman1.closeBill(bill1, terminal1);

//        Bill bill2 = salesman1.createBill(terminal1);
//        salesman1.addProduct(terminal1, product1);
//        salesman1.addProduct(terminal1, product2);
//        salesman1.addProduct(terminal1, product3);
//        System.out.println(salesman1.closeBill(bill2, terminal1));



    }

}