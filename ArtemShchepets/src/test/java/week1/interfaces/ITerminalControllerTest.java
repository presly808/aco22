package week1.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.controller.ITerminalControllerImpl;
import week1.database.IAppDBImpl;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import static org.junit.Assert.*;

public class ITerminalControllerTest {

    private ITerminalController terminalController;
    private IAppDB iAppDB;

    @Before
    public void setUp() throws Exception {
        iAppDB = new IAppDBImpl();
        terminalController = new ITerminalControllerImpl(iAppDB);
    }

    @After
    public void tearDown() throws Exception {
        terminalController = null;
    }


    @Test
    public void login() throws Exception {

        iAppDB.getAllSellers().add(
                new Seller("worker", "password", "Nadya Horoshun"));

        assertTrue(terminalController.login("worker", "password"));
        assertEquals(0, iAppDB.getCurrentSellerId());
    }

    @Test
    public void findSellerByLoginOrFullName() throws Exception {

        Seller addSeller1 = new Seller(
                "worker", "password", "Nadya Horoshun");
        Seller addSeller2 = new Seller(
                "worker123", "password11", "Vasya Noob");
        Seller addSeller3 = new Seller(
                "worker22", "password432", "Annita Volosova");

        iAppDB.getAllSellers().add(addSeller1);
        iAppDB.getAllSellers().add(addSeller2);
        iAppDB.getAllSellers().add(addSeller3);

        Seller seller = terminalController.findSellerByLoginOrFullName("worker123");
        Seller seller1 = terminalController.findSellerByLoginOrFullName("Annita Volosova");

        assertEquals(addSeller2,seller);
        assertEquals(addSeller3,seller1);
    }

    @Test
    public void getTopNOfSalesman() throws Exception {

        Seller addSeller1 = new Seller(
                "worker", "password", "Nadya Horoshun");
        Seller addSeller2 = new Seller(
                "worker123", "password11", "Vasya Noob");
        Seller addSeller3 = new Seller(
                "worker22", "password432", "Annita Volosova");
        Seller addSeller4 = new Seller(
                "worker01", "password1111", "Vova Split");

        addSeller1.setSoldProducts(12);
        addSeller2.setSoldProducts(2);
        addSeller3.setSoldProducts(344);
        addSeller4.setSoldProducts(15);

        iAppDB.getAllSellers().add(addSeller1);
        iAppDB.getAllSellers().add(addSeller2);
        iAppDB.getAllSellers().add(addSeller3);
        iAppDB.getAllSellers().add(addSeller4);

        Seller[] expectedArray = new Seller[1];
        expectedArray[0] = addSeller3;

        assertArrayEquals(expectedArray, terminalController.getTopNOfSalesman(1));
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {

    }

    @Test
    public void createBill() throws Exception {

        Bill bill = terminalController.createBill();

        assertEquals(0, bill.getProductList().size());
        assertEquals(0, bill.getId());
    }

    @Test
    public void addProduct() throws Exception {

        Bill bill = terminalController.createBill();

        terminalController.addProduct(bill.getId(), new Product());
        assertEquals(1, bill.getProductList().size());
    }

    @Test
    public void getAllBills() throws Exception {

        Bill bill1 = terminalController.createBill();
        Bill bill2 = terminalController.createBill();
        Bill bill3 = terminalController.createBill();
        Bill bill4 = terminalController.createBill();

        assertEquals(4, terminalController.getAllBills().size());
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        Bill closed = terminalController.closeBill(bill.getId());

        assertTrue(closed.isClosed());
    }

}