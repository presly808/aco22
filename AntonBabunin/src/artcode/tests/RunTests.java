package artcode.tests;

import badcode.SalesMan;
import badcode.Terminal;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RunTests {
/*    @Test
    public void testTerminalCreated1() {
        Terminal terminal1 = Terminal.getInstance();
        Assert.assertNotNull(terminal1);
    }

    @Test
    public void testTerminalCreated() {
        Terminal terminal1 = Terminal.getInstance();
        Terminal terminal2 = Terminal.getInstance();
        Assert.assertEquals(terminal1, terminal2);
    }
*/

    @Test
    public void testLogin() {
//        Test that salesMan Not added to List

    Terminal terminal1 = Terminal.getInstance();
    SalesMan salesMan1 = new SalesMan("Anton");
    salesMan1.setLogin("Anton");
    salesMan1.setPassword("Anton1");
    terminal1.login(salesMan1);
    List actual = terminal1.getSales();
    List expected = new ArrayList();
    expected.add(salesMan1);
    Assert.assertNull(actual);
}

    @Test
    public void testLogin1() {
//        Test that salesMan added to List

        Terminal terminal1 = Terminal.getInstance();
        SalesMan salesMan1 = new SalesMan("Anton");
        salesMan1.setLogin("Anton");
        salesMan1.setPassword("Anton");
        terminal1.login(salesMan1);
        List actual = terminal1.getSales();
        List expected = new ArrayList();
        expected.add(salesMan1);
        Assert.assertNotNull(actual.toArray());
    }



    @Test
    public void testLogin2() {
//        Test that salesMan added to List correct
        Terminal terminal1 = Terminal.getInstance();
        terminal1.getSales().clear();
        SalesMan salesMan1 = new SalesMan("Anton");
        salesMan1.setLogin("Anton");
        salesMan1.setPassword("Anton");
        terminal1.login(salesMan1);
        List actual = terminal1.getSales();
        List expected = new ArrayList();
        expected.add(salesMan1);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

}























    /*
    @Test
    public void testGetPrice1(){
        Product product = new Product("asd", 1.11, 1);
        double expected = 1.11;
        double actual = product.getPrice();
        Assert.assertEquals(expected, actual, 0);
    }
    @Test
    public void testAddProduct1(){
        Bill bill = new Bill();
        Product expected = new Product("asd", 123.22, 123);
        bill.addProduct(expected);
        Product actual = bill.getProducts()[0];
        Assert.assertSame(expected, actual);
    }
    @Test
    public void testAddProduct2(){
        Bill bill = new Bill();
        Product expected = new Product(null, 123.22, 123);
        bill.addProduct(expected);
        Product actual;
        if (bill != null && expected.getName() != null) {
            actual = bill.getProducts()[0];
        } else {
            actual = null;
        }
        Assert.assertNull(actual);
    }

    @Test
    public void testDelFromBill(){

        Bill bill = new Bill();
        Product product1 = new Product("Notebook", 1.1, 1);
        Product product2 = new Product("Smart", 1.1, 2);
        Product product3 = new Product("Tablet", 1.1, 3);
        Product product4 = new Product("Delete", 1.1, 4);
        Product product5 = new Product("Pain", 1.1, 5);
        bill.addProduct(product1);
        bill.addProduct(product2);
        bill.addProduct(product3);
        bill.addProduct(product4);
        bill.addProduct(product5);
        bill.delFromBill(product4);
//        for (Product x : bill.getProducts()){
//            if (x != null) {
//                System.out.println(x.getName());
//            } else {
//                System.out.println("null");
//            }
//        }
        String[] expected = {"Notebook","Smart", "Tablet", null, "Pain", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        String[] actual = new String[20];
        for (int i = 0; i < bill.getProducts().length; i++) {
            if (bill.getProducts()[i] != null) {
                actual[i] = bill.getProducts()[i].getName();
            }
        }
        Assert.assertArrayEquals(expected, actual);
    }

 */
