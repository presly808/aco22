package week1.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import week1.data.Bill;
import week1.data.Product;
import week1.data.Seller;
import week1.data.Time;

public class BillTest {

    Product testProduct1 = new Product("Milk", 11.20, "#03242341");
    Product testProduct2 = new Product("Cheese", 2.05, "#0341");
    Product testProduct3 = new Product("Water", 33.5, "#01");
    Product testProduct4 = new Product("Cake", 7.55, "#222");

    Product[] testProductList = new Product[4];

    Seller testSeller = new Seller("Valya", 22);
    Time testTime = new Time(12, 33, 50);

    Bill testBill = new Bill( testSeller, testTime);

    @Test
    public void testAddProducts() {

        testProductList[0] = testProduct1;
        testProductList[1] = testProduct2;
        testProductList[2] = testProduct3;
        testProductList[3] = testProduct4;

        testBill.addProduct(testProduct1);
        testBill.addProduct(testProduct2);
        testBill.addProduct(testProduct3);
        testBill.addProduct(testProduct4);

        Assert.assertArrayEquals(testProductList, testBill.getBillList());
    }

    @Test
    public void testAmountPrice() {


        double expected = testProduct1.getPrice() + testProduct2.getPrice()
                + testProduct3.getPrice() + testProduct4.getPrice();

        testBill.calculateBill();

        Assert.assertEquals(expected, testBill.getBillCost(), 0);
    }

    @Test
    public void testShowInfo() {

        testBill.setClosed(false);

        String expected = "***BILL***\n"
                + testProduct1.showInfo() + "\n"
                + testProduct2.showInfo() + "\n"
                + testProduct3.showInfo() + "\n"
                + testProduct4.showInfo() + "\n"
                + "12:33:50\n" + "Seller: Valya, age: 22" + "\n***BILL IS OPENED***";

        String actual = testBill.showInfo();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testCloseBill() {

        testBill.closeBill();

        Assert.assertEquals(true, testBill.isClosed());
    }
}
