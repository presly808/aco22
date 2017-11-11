package tests;



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