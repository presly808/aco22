package week1.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import week1.data.*;

public class TerminalTest {

    Terminal testTerminal = new Terminal();

    Seller testSeller1 = new Seller("NadyaHoroshun", 22, "worker1", "password1");
    Seller testSeller2 = new Seller("AntonVorobey", 17, "worker2", "password2");
    Seller testSeller3 = new Seller("VasyaPupkin", 59, "worker3", "password3");
    Seller testSeller4 = new Seller("AnyaTupova", 14, "worker4", "password4");
    Seller testSeller5 = new Seller(null, 20, "worker5", "password5");

    Seller[] testSellerArray = new Seller[5];

    Product testProduct1 = new Product("Milk", 11.20, "#03242341");
    Product testProduct2 = new Product("Cheese", 2.05, "#0341");
    Product testProduct3 = new Product("Water", 33.5, "#01");
    Product testProduct4 = new Product(null, 7.55, "#222");


    Seller testSeller = new Seller("Valya", 22, "test", "test");
    Time testTime = new Time(12, 33, 50);
    Bill testBill = new Bill( testSeller, testTime);

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
        Assert.assertEquals(2,testTerminal.getCurrentSellerIndex());

        testTerminal.setCurrentSellerIndex(-1);
        testTerminal.setSignIn(false);
    }


}
