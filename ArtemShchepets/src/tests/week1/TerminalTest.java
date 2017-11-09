package week1;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalTest {

    Terminal testTerminal;

    Seller testSeller1;
    Seller testSeller2;
    Seller testSeller3;
    Seller testSeller4;
    Seller testSeller5;

    Seller[] testSellerArray;

    Product testProduct1;
    Product testProduct2;
    Product testProduct3;
    Product testProduct4;


    Time testTime;
    Bill testBill1;
    Bill testBill2;
    Bill testBill3;


    @Before
    public void setUp() {
        System.out.println("Before called");

        testTerminal = new Terminal();

        testSeller1 = new Seller("NadyaHoroshun", 22, "worker1", "password1");
        testSeller2 = new Seller("AntonVorobey", 17, "worker2", "password2");
        testSeller3 = new Seller("VasyaPupkin", 59, "worker3", "password3");
        testSeller4 = new Seller("AnyaTupova", 14, "worker4", "password4");
        testSeller5 = new Seller(null, 20, "worker5", "password5");

        testSellerArray = new Seller[5];

        testProduct1 = new Product("Milk", 11.20, "#03242341");
        testProduct2 = new Product("Cheese", 2.05, "#0341");
        testProduct3 = new Product("Water", 33.5, "#01");
        testProduct4 = new Product(null, 7.55, "#222");

        testBill1 = new Bill(testSeller1, testTime);
        testBill2 = new Bill(testSeller4);
        testBill3 = new Bill(testSeller5);

        testTime = new Time(12, 33, 50);
    }

    @After
    public void tearDown() {

        System.out.println("After called");

        Terminal testTerminal = null;

        Seller testSeller1 = null;
        Seller testSeller2 = null;
        Seller testSeller3 = null;
        Seller testSeller4 = null;
        Seller testSeller5 = null;

        Seller[] testSellerArray = null;

        testSellerArray[0] = null;
        testSellerArray[1] = null;
        testSellerArray[2] = null;
        testSellerArray[3] = null;
        testSellerArray[4] = null;

        Product testProduct1 = null;
        Product testProduct2 = null;
        Product testProduct3 = null;
        Product testProduct4 = null;


        Time testTime = null;
        Bill testBill1 = null;
        Bill testBill2 = null;
        Bill testBill3 = null;

    }

    @Test
    public void testSignInValidation1() {

        //trying to sign in without base of sellers (and ther logins\passwords)
        testTerminal.signIn("login", "pass");

        Assert.assertFalse(testTerminal.isSignIn());

    }

    @Test
    public void testSignInValidation2() {

        // trying to sign in with base of logins\passwords, but with non-valid input login\pass

        testSellerArray[0] = testSeller1;
        testSellerArray[1] = testSeller2;
        testSellerArray[2] = testSeller3;
        testSellerArray[3] = testSeller4;
        testSellerArray[4] = testSeller5;

        testTerminal.setSellers(testSellerArray);

        testTerminal.signIn("null", "");

        Assert.assertFalse(testTerminal.isSignIn());

        //instead of annotation @After, which doesn't work
    }


    @Test
    public void testSignIn() {

        // trying to sign in with base of logins and with valid login/pass

        testSellerArray[0] = testSeller1;
        testSellerArray[1] = testSeller2;
        testSellerArray[2] = testSeller3;
        testSellerArray[3] = testSeller4;
        testSellerArray[4] = testSeller5;

        testTerminal.setSellers(testSellerArray);

        testTerminal.signIn("worker3", "password3");

        Assert.assertTrue(testTerminal.isSignIn());
        Assert.assertEquals(2, testTerminal.getCurrentSellerIndex());

        // instead of annotation @After, which doesn't work


    }

    /*

    @Test
    public void testCloseAndSaveBillValidation() {
        //trying to close bill without signing in

        Bill testBill = new Bill(testSeller2);

        Bill[] testBillList = new Bill[1];
        testBillList[0] = testBill;

        testTerminal.setBills(testBillList);
        testTerminal.closeAndSaveBill();

        Assert.assertFalse(testTerminal.getBills()[testTerminal.getCurrentBillIndex()].isClosed());

        //instead of @After annotation, which doesnt work
        testTerminal.setBills(null);
        testTerminal.setCurrentBillIndex(-1);
        testTerminal.setActualSizeOfBills(0);
    }

    */

}

