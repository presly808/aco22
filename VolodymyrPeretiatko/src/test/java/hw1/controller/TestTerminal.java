package hw1.controller;

import hw1.controller.Terminal;
import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;
import org.junit.*;

public class TestTerminal {

    private static Terminal terminal;

    private static Bill b1, b2;
    private static Product p1, p2;
    private static Salesman s1, s2;


    @BeforeClass
    public static void initTestData(){

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

        terminal = Terminal.getInstance();

        terminal.addProduct(p1);
        terminal.addProduct(p2);

        terminal.addSalesman(s1);
        terminal.addSalesman(s2);

        terminal.closeAndSaveBill(b1);
        terminal.closeAndSaveBill(b2);
    }

    @AfterClass
    public static void clearTestData(){
        s1 = null;
        s2 = null;
        p1 = null;
        p2 = null;
    }

    @Test
    public void testSingletone(){
        Terminal terminal1 = Terminal.getInstance();
        Terminal terminal2 = Terminal.getInstance();
        Assert.assertTrue(terminal1 == terminal2);
    }

    @Test
    public void testLogin(){
        Assert.assertEquals(s1, terminal.login("Jhon", "qwerty"));
        Assert.assertEquals(s2, terminal.login("Frank", "qwerty"));
        Assert.assertTrue(terminal.login("HuiChi", "qwerty") == null);
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
        Bill b = terminal.createBill(3, s2);
        Assert.assertTrue(b.getCloseTime() == null);
    }

    @Test
    public void testAddSalesman(){
        Assert.assertFalse(terminal.addSalesman(s1));
    }


}
