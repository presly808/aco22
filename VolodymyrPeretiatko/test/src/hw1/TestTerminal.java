package hw1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTerminal {

    public Terminal terminal;

    public static Product p1, p2;
    public static Salesman s1, s2;
    public static Bill b1;

    @BeforeClass
    public static void initTestData(){
        s1 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        s2 = new Salesman("Frank Sinatra", "Frank", "qwerty");

        p1 = new Product(1, "Laptop HP 750B",  500.0);
        p2 = new Product(2, "Laptop HP 1020Z", 650.0);

        b1 = new Bill(9, s1);
    }

    @Before
    public void init(){
        terminal = Terminal.getInstance();

        terminal.addProduct(p1);
        terminal.addProduct(p2);

        terminal.addSalesman(s1);
        terminal.addSalesman(s2);

        b1.addProduct(p1);
        terminal.closeAndSaveBill(b1);
    }

    @Test
    public void testSingletone(){
        Terminal terminal1 = Terminal.getInstance();
        Terminal terminal2 = Terminal.getInstance();

        Assert.assertEquals(terminal1, terminal2);
    }

    @Test
    public void testLogin(){
        Assert.assertTrue(terminal.login("Jhon", "qwerty") == s1 );
        Assert.assertTrue(terminal.login("Frank", "qwerty") == s2 );
        Assert.assertTrue(terminal.login("Hui", "qwerty") == null );
    }

    @Test
    public void testFindBillById(){
        Assert.assertTrue(terminal.findBillById(9) == b1);
        Assert.assertTrue(terminal.findBillById(7) == null);
    }

}
