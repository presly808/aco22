package hw1.model;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


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

    @Test
    public void testComparableBill(){

        Bill b1 = new Bill(1, s1);
        Bill b2 = new Bill(2, s1);
        Bill b3 = new Bill(3, s1);

        Bill expected[] = new Bill[3];
        expected[0]= b1;
        expected[1]= b2;
        expected[2]= b3;

        Bill actual[] = new Bill[3];
        actual[0]= b2;
        actual[1]= b3;
        actual[2]= b1;
        Arrays.sort(actual);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByDateComparator(){
        Bill b1 = new Bill(1, s1);
        Bill b2 = new Bill(2, s1);
        Bill b3 = new Bill(3, s1);

        b1.closeBill(new Date(1111111));
        b2.closeBill(new Date(2222222));
        b3.closeBill(new Date(3333333));

        ArrayList<Bill> expected = new ArrayList<>(3);
        expected.add(b1);
        expected.add(b2);
        expected.add(b3);

        ArrayList<Bill> actual = new ArrayList<>(3);
        actual.add(b3);
        actual.add(b1);
        actual.add(b2);

        actual.sort(new Bill.SortByDateComparator());
        Assert.assertEquals(expected, actual);
    }


}
