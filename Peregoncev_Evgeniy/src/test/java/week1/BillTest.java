package week1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 14.11.2017.
 */
public class BillTest {

    private Salesman testSalesMan;

    private Bill testBill;

    private Product testProduct1;
    private Product testProduct2;
    private Product testProduct3;
    private Product testProduct4;

    private Time testTime;

    private double testAmountPrice;

    @Before
    public void setUp() {

        testTime = new Time();

        testSalesMan = new Salesman("Izolda", "isoldaLog", "IsoldaPass");
        testBill = new Bill(testAmountPrice, testSalesMan, testTime, 1);

        testProduct1 = new Product("apricot", 16.5, "#001");
        testProduct2 = new Product("banana", 20.0, "#002");
        testProduct3 = new Product("watermelon", 50.5, "#003");
        testProduct4 = new Product("apple", 8.5, "#004");

    }

    @After
    public void setDown() {

        testSalesMan = null;
        testBill = null;

        testProduct1 = null;
        testProduct2 = null;
        testProduct3 = null;
        testProduct4 = null;

        testTime = null;

        testAmountPrice = 0;

    }


    @Test
    public void addProduct() throws Exception {

        testBill.addProduct(testProduct1);
        assertSame(testBill.getProducts()[testBill.getProductsCounter() - 1], testProduct1);

    }

    @Test
    public void calculateAmountPrice() throws Exception {

        testBill.getProducts()[0] = testProduct1;
        testBill.getProducts()[1] = testProduct2;
        testBill.setProductsCounter(2);
        double expected = testProduct1.getPrice() + testProduct2.getPrice();

        assertEquals(expected, testBill.calculateAmountPrice(testBill), 0);

    }

    @Test
    public void printBillInfo() throws Exception {

        testBill.getProducts()[0] = testProduct1;
        testBill.getProducts()[1] = testProduct2;
        testBill.getProducts()[2] = testProduct3;
        testBill.getProducts()[3] = testProduct4;

        testBill.setProductsCounter(4);

        double testAmountPrice = testBill.getProducts()[0].getPrice() +
                testBill.getProducts()[1].getPrice() +
                testBill.getProducts()[2].getPrice() +
                testBill.getProducts()[3].getPrice();

        testBill.setAmountPrice(testAmountPrice);

        String expected1 = "              BiLL  \n" +
                "Title           Price    Barcode\n" +
                "--------------------------------\n" +
                testBill.printAllProducts() +
                "\nAmount Price = " + testBill.getAmountPrice() + "\n" +
                "Seller: Izolda\n" +
                testTime.printTime();

        testTime.setCloseTime(testTime.printTime());

        assertEquals(expected1, testBill.printBillInfo());

    }

}


















