package week1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.controller.BillController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import static week1.utils.Utils.getCurrentDate;

public class BillControllerTest {

    Product testProduct1;
    Product testProduct2;
    Product testProduct3;
    Product testProduct4;

    Product[] testProductList;

    Seller testSeller;

    Bill testBill;

    Bill[] testBills = new Bill[1];

    BillController billController = new BillController();


    @Before
    public void setUp() {
        testProduct1 = new Product("Milk", 11.20, "#03242341");
        testProduct2 = new Product("Cheese", 2.05, "#0341");
        testProduct3 = new Product("Water", 33.5, "#01");
        testProduct4 = new Product("Cake", 7.55, "#222");

        testSeller = new Seller("Valya", 22, "test", "test");

        testBill = new Bill(testSeller);

        testProductList = new Product[testBill.getDefaultSizeOfList()];

        testBills[0] = testBill;
    }

    @After
    public void tearDown() {
        Product testProduct1 = null;
        Product testProduct2 = null;
        Product testProduct3 = null;
        Product testProduct4 = null;

        Product[] testProductList = null;

        Seller testSeller = null;

        Bill testBill = null;
    }

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


        testBill.addProduct(testProduct1);
        testBill.addProduct(testProduct2);
        testBill.addProduct(testProduct3);
        testBill.addProduct(testProduct4);


        double expected = testProduct1.getPrice() + testProduct2.getPrice()
                + testProduct3.getPrice() + testProduct4.getPrice();

        testBill.calculateBill();

        Assert.assertEquals(expected, testBill.getBillCost(), 0);
    }


    @Test
    public void testShowInfo() {

        testBill.addProduct(testProduct1);
        testBill.addProduct(testProduct2);
        testBill.addProduct(testProduct3);
        testBill.addProduct(testProduct4);

        testBill.setClosed(false);

        String expected = "***BILL***\n"
                + testProduct1.showInfo() + "\n"
                + testProduct2.showInfo() + "\n"
                + testProduct3.showInfo() + "\n"
                + testProduct4.showInfo() + "\n"
                + getCurrentDate() + "\n" + "Seller: Valya, age: 22" + ", sold: 0" + "\n***BILL IS OPENED***";

        String actual = testBill.showInfo();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCloseBill() {

        billController.setBills(testBills);
        billController.closeAndSaveBill();

        Assert.assertEquals(true, billController.getBills()[billController.getCurrentBillIndex()].isClosed());
    }
}

