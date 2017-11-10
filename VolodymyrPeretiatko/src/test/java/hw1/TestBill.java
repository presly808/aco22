package hw1;

import org.junit.*;

import java.util.ArrayList;


public class TestBill {

    private Bill bill;
    private static Bill b2;
    private static Salesman s1;
    private static Product p1;

    @BeforeClass
    public static void initTestData(){
        s1 = new Salesman("Jhon Lohan", "Jhon", "qwerty");
        p1 = new Product(1, "Laptop HP 750B", 500.0);
        b2 = new Bill(1, s1);
        b2.addProduct(p1);
    }

    @Before
    public void init(){
        bill = new Bill(1, s1);
    }

    @Test
    public void testAddProduct(){
        Assert.assertTrue(bill.addProduct(p1));
        Assert.assertEquals(500.0, bill.getAmountPrice(), 0.00001);

        ArrayList<Product> products = bill.getProducts();
        Assert.assertTrue(products.contains(p1));

    }

    @Test
    public void testCloseBill(){
        Assert.assertTrue(bill.addProduct(p1));
        bill.closeBill();
        Assert.assertEquals(500.0, bill.getAmountPrice(), 0.00001);
        Assert.assertFalse(bill.getCloseTime() == null);
    }

}
