import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 14.11.2017.
 */
public class BillTest {

    Bill testBill;

    private Salesman testSalesMan;

    Product testProduct1;
    Product testProduct2;
    Product testProduct3;
    Product testProduct4;

    double testAmountPrice;

    @Before
    public void setUp() {

        testSalesMan = new Salesman("Izolda", "isoldaLog", "IsoldaPass");
        testBill = new Bill(testAmountPrice, testSalesMan, "23:00", 1);

        testProduct1 = new Product("apricot", 16.5, "#001");
        testProduct2 = new Product("banana", 20.0, "#002");
        testProduct3 = new Product("watermelon", 50.5, "#003");
        testProduct4 = new Product("apple", 8.5, "#004");
    }

    @After
    public void setDown() {

        testBill = null;

        testSalesMan = null;

        testProduct1 = null;
        testProduct2 = null;
        testProduct3 = null;
        testProduct4 = null;
    }

    @Test
    public void addProduct() throws Exception {

        testBill.addProduct(testProduct1);
        Assert.assertSame(testBill.getProducts()[testBill.getProductsCounter() - 1], testProduct1);

    }

    @Test
    public void calculateAmountPrice() throws Exception {

        testBill.getProducts()[0] = testProduct1;
        testBill.getProducts()[1] = testProduct2;
        testBill.setProductsCounter(2);
        double expected = testProduct1.getPrice() + testProduct2.getPrice();

        Assert.assertEquals(expected, testBill.calculateAmountPrice(testBill), 0);

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
                "\nAmount Price = 95,50\n" +
                "Seller: Izolda\n" +
                "Time: 23:00";

        Assert.assertEquals(expected1, testBill.printBillInfo());

    }

}


















