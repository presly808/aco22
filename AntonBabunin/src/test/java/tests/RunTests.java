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
//            Product product7 = ProductGeneration.generateProduct();
//            Product product8 = ProductGeneration.generateProduct();

            Salesman salesman1 = SalesmanCreator.salesmanCreateAutomatic();
            salesman1.loginAutomatic(terminal1);

            Bill bill1 = salesman1.createBill(terminal1);
            salesman1.addProduct(terminal1, product1);
            salesman1.addProduct(terminal1, product2);
            salesman1.addProduct(terminal1, product3);
            salesman1.addProduct(terminal1, product1);
            salesman1.addProduct(terminal1, product2);
            salesman1.addProduct(terminal1, product3);
            salesman1.addProduct(terminal1, product3);
            salesman1.addProduct(terminal1, product4);
            salesman1.addProduct(terminal1, product5);
            salesman1.addProduct(terminal1, product6);

            salesman1.closeBill(bill1, terminal1);

            Salesman salesman2 = SalesmanCreator.salesmanCreateAutomatic();
            salesman2.loginAutomatic(terminal1);

            Bill bill2 = salesman2.createBill(terminal1);
            salesman2.addProduct(terminal1, product1);
            salesman2.addProduct(terminal1, product2);
            salesman2.addProduct(terminal1, product3);
            salesman2.addProduct(terminal1, product3);
            salesman2.addProduct(terminal1, product3);

            salesman2.closeBill(bill2, terminal1);

            Salesman salesman3 = SalesmanCreator.salesmanCreateAutomatic();
            salesman3.loginAutomatic(terminal1);

            Bill bill3 = salesman3.createBill(terminal1);
            salesman3.addProduct(terminal1, product1);
            salesman3.addProduct(terminal1, product2);
            salesman3.addProduct(terminal1, product3);
            salesman3.addProduct(terminal1, product3);
            salesman3.addProduct(terminal1, product4);
            salesman3.addProduct(terminal1, product5);
            salesman3.addProduct(terminal1, product6);

            salesman3.closeBill(bill3, terminal1);

            System.out.println("");

            System.out.println("The best seller is: " + terminal1.getTopNofSalesMan().getFullName());

            boolean billIsClosed = salesman1.closeBill(bill1, terminal1);

            Assert.assertTrue(billIsClosed);


//        Bill bill2 = salesman1.createBill(terminal1);
//        salesman1.addProduct(terminal1, product1);
//        salesman1.addProduct(terminal1, product2);
//        salesman1.addProduct(terminal1, product3);
//        System.out.println(salesman1.closeBill(bill2, terminal1));



    }

}