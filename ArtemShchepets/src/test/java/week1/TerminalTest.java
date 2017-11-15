package week1;


import org.junit.*;
import week1.controller.Terminal;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Time;


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

    Bill[] testBillList;


    @Before
    public void setUp() {

        testTerminal = new Terminal();

        testSeller1 = new Seller("NadyaHoroshun", 22, "worker1", "password1"); // 1 pr
        testSeller2 = new Seller("AntonVorobey", 17, "worker2", "password2"); // 3 pr
        testSeller3 = new Seller("VasyaPupkin", 59, "worker3", "password3"); // 0
        testSeller4 = new Seller("AnyaTupova", 14, "worker4", "password4"); // 4 pr
        testSeller5 = new Seller(null, 20, "worker5", "password5"); // 0

        testSellerArray = new Seller[5];

        testSellerArray[0] = testSeller1;
        testSellerArray[1] = testSeller2;
        testSellerArray[2] = testSeller3;
        testSellerArray[3] = testSeller4;
        testSellerArray[4] = testSeller5;

        testProduct1 = new Product("Milk", 11.20, "#03242341");
        testProduct2 = new Product("Cheese", 2.05, "#0341");
        testProduct3 = new Product("Water", 33.5, "#01");
        testProduct4 = new Product(null, 7.55, "#222");

        testTime = new Time(12, 33, 50);

        testBill1 = new Bill(testSeller1);
        testBill2 = new Bill(testSeller4);
        testBill3 = new Bill(testSeller2);

        testBill1.addProduct(testProduct1);
        testBill2.addProduct(testProduct2);
        testBill2.addProduct(testProduct1);
        testBill2.addProduct(testProduct3);
        testBill2.addProduct(testProduct3);
        testBill3.addProduct(testProduct2);
        testBill3.addProduct(testProduct2);
        testBill3.addProduct(testProduct2);

        testBillList = new Bill[3];

        testBillList[0] = testBill1;
        testBillList[1] = testBill2;
        testBillList[2] = testBill3;
    }

    @After
    public void tearDown() {

        Terminal testTerminal = null;

        Seller testSeller1 = null;
        Seller testSeller2 = null;
        Seller testSeller3 = null;
        Seller testSeller4 = null;
        Seller testSeller5 = null;

        testSellerArray[0] = null;
        testSellerArray[1] = null;
        testSellerArray[2] = null;
        testSellerArray[3] = null;
        testSellerArray[4] = null;

        Seller[] testSellerArray = null;

        Product testProduct1 = null;
        Product testProduct2 = null;
        Product testProduct3 = null;
        Product testProduct4 = null;

        Time testTime = null;

        Bill testBill1 = null;
        Bill testBill2 = null;
        Bill testBill3 = null;

        testBillList = null;
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
        testTerminal.setSellers(testSellerArray);

        testTerminal.signIn("null", "");

        Assert.assertFalse(testTerminal.isSignIn());

        //instead of annotation @After, which doesn't work
    }


    @Test
    public void testSignIn() {

        // trying to sign in with base of logins and with valid login/pass
        testTerminal.setSellers(testSellerArray);

        testTerminal.signIn("worker3", "password3");

        Assert.assertTrue(testTerminal.isSignIn());
        Assert.assertEquals(2, testTerminal.getCurrentSellerIndex());

        // instead of annotation @After, which doesn't work


    }

    @Test
    public void testCloseAndSaveBillValidation() {

        //trying to close bill without signing in
        testTerminal.setBills(testBillList);
        testTerminal.closeAndSaveBill(testTime);

        Assert.assertFalse(testTerminal.getBills()[testTerminal.getCurrentBillIndex()].isClosed());
    }


    @Test
    public void testFindBillByIdValidation() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Bill searchingBill = testTerminal.findBillById(-21435);

        Assert.assertNull(searchingBill);
    }

    @Test
    public void testFindBillById() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Bill searchingBill = testTerminal.findBillById(2);

        Assert.assertEquals(2, searchingBill.getId());

    }

    @Test
    public void testFindSalesmanByLoginOrFullNameValidation() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");

        Seller searchingSeller = testTerminal.findSalesmanByLoginOrFullname(null);

        Assert.assertNull(searchingSeller);
    }

    @Test
    public void testFindSalesmanByLoginOrFullNameValidation2() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");

        Seller searchingSeller = testTerminal.findSalesmanByLoginOrFullname("testValue");

        Assert.assertNull(searchingSeller);
    }

    @Test
    public void testFindSalesmanByLoginOrFullName() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");

        Seller searchingSeller = testTerminal.findSalesmanByLoginOrFullname("VasyaPupkin");

        Assert.assertEquals(testSellerArray[2].getLogin(), searchingSeller.getLogin());
    }

    @Test
    public void testGetTopNofSalesMan() {
        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");


        testBillList[0] = testBill1;
        testBillList[1] = testBill2;
        testBillList[2] = testBill3;

        testTerminal.setBills(testBillList);

        Seller[] searchingSellerArray = testTerminal.getTopNofSalesMan(1);

        Assert.assertEquals(testSeller4.toString(), searchingSellerArray[0].toString());
    }

    @Test
    public void testGetTopNofSalesMan2() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Seller[] searchingSellerArray = testTerminal.getTopNofSalesMan(2);

        Seller[] expectedTopSellers = new Seller[2];
        expectedTopSellers[0] = testSeller4;
        expectedTopSellers[1] = testSeller2;

        Assert.assertArrayEquals(expectedTopSellers, searchingSellerArray);

    }

    @Test
    public void testCreateBill() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Assert.assertTrue(testTerminal.createBill(testBill3));
    }

    @Test
    public void testCreateBill2() {
        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        testTerminal.createBill(testBill3);

        Assert.assertEquals(testBill3, testTerminal.getBills()[3]);
    }

    @Test
    public void testAddProduct() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Assert.assertTrue(testTerminal.addProductToBill(testProduct1));
    }

    @Test
    public void testAddProduct2() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        testTerminal.addProductToBill(testProduct1);

        Product actualProductInBill = testTerminal.getBills()[testTerminal.getCurrentBillIndex()]
                .getBillList()[testTerminal.getBills()
                [testTerminal.getCurrentBillIndex()].getActualSizeOfList() - 1];

        Assert.assertEquals(testProduct1, actualProductInBill);
    }

    @Test
    public void testCloseAndSaveBill() {
        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Assert.assertTrue(testTerminal.closeAndSaveBill(testTime));
    }

    @Test
    public void testCloseAllPreviousBills() {

        testTerminal.setSellers(testSellerArray);
        testTerminal.signIn("worker2", "password2");
        testTerminal.setBills(testBillList);

        Assert.assertTrue(testTerminal.closeAllPreviousBills(testTime));
    }
}

