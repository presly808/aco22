package week1.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.comparators.CreationDateComparator;
import week1.database.IAppDB;
import week1.model.Bill;
import week1.model.Product;
import week1.model.SalesStatistic;
import week1.model.Seller;
import week1.utils.InitUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ITerminalControllerTest {

    private ITerminalController terminalController;
    private IAppDB iAppDB;

    @Before
    public void setUp() throws Exception {
        iAppDB = InitUtils.initSellerDb();
        terminalController = new ITerminalControllerImpl(iAppDB);
        terminalController.turnOnDatabaseLogger();
        terminalController.turnOnTerminalLogger();
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
        assertEquals(0, terminalController.getCurrentSellerId());
    }

    @Test
    public void findSellerByLoginOrFullName() throws Exception {

        Seller addSeller1 = new Seller(
                "worker", "password", "Nadya Horoshun");
        Seller addSeller2 = new Seller(
                "worker123", "password11", "Vasya Noob");
        Seller addSeller3 = new Seller(
                "worker22", "password432", "Annita Volosova");

        iAppDB.saveSeller(addSeller1);
        iAppDB.saveSeller(addSeller2);
        iAppDB.saveSeller(addSeller3);

        Seller seller = terminalController.findSellerByLoginOrFullName("worker123");
        Seller seller1 = terminalController.findSellerByLoginOrFullName("Annita Volosova");

        assertEquals(addSeller2, seller);
        assertEquals(addSeller3, seller1);
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

        iAppDB.updateSeller(addSeller1);
        iAppDB.updateSeller(addSeller2);
        iAppDB.updateSeller(addSeller3);
        iAppDB.updateSeller(addSeller4);

        iAppDB.saveBill(new Bill());

        assertEquals(addSeller3, terminalController.getTopOfSalesman());
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {

        Seller addSeller1 = new Seller(
                "worker", "password", "Nadya Horoshun");
        Seller addSeller2 = new Seller(
                "worker123", "password11", "Vasya Noob");
        Seller addSeller3 = new Seller(
                "worker22", "password432", "Annita Volosova");

        iAppDB.saveSeller(addSeller1);
        iAppDB.saveSeller(addSeller2);
        iAppDB.saveSeller(addSeller3);

        terminalController.login("worker22", "password432");
        terminalController.createBill();
        terminalController.addProduct(0, new Product("Milk", 2.220));
        terminalController.addProduct(0, new Product("Cheese", 0.30));
        terminalController.addProduct(0, new Product("Milk", 15.50));
        terminalController.addProduct(0, new Product("Milk", 2.220));
        terminalController.addProduct(0, new Product("Milk", 0.010));
        terminalController.addProduct(0, new Product("Milk", 0.010));
        terminalController.addProduct(0, new Product("Milk", 0.010));// 20.27

        terminalController.login("worker123", "password11");
        terminalController.createBill();
        terminalController.addProduct(1, new Product("Cake", 27.170));
        terminalController.addProduct(1, new Product("Cheese", 33.30));
        terminalController.addProduct(1, new Product("Water", 15.50));
        terminalController.addProduct(1, new Product("Milk", 2.220)); // 78.19

        terminalController.login("worker", "password");
        terminalController.createBill();
        terminalController.addProduct(2, new Product("Watermelon", 3.990));
        terminalController.addProduct(2, new Product("Cheese", 0.30));
        terminalController.addProduct(2, new Product("Milk", 15.50));
        terminalController.addProduct(2, new Product("Juice", 9.0)); // 28.79


        SalesStatistic salesStatistic = terminalController.doSomeStatisticStuff();

        assertNotNull(salesStatistic);
        assertEquals(20.27, salesStatistic.getMinBillPrice(), 0.001);
        assertEquals(78.19, salesStatistic.getMaxBillPrice(), 0.001);
        assertEquals(15, salesStatistic.getSoldProducts());
        assertEquals(addSeller3, salesStatistic.getBestSalesMan());

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

        terminalController.createBill();
        terminalController.createBill();
        terminalController.createBill();
        terminalController.createBill();

        assertEquals(4, terminalController.getAllBills().size());
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        Bill closed = terminalController.closeBill(bill.getId());

        assertTrue(closed.isClosed());
    }

    @Test
    public void filter() throws Exception {

        Bill bill = new Bill();
        bill.setOpenTime(LocalDateTime.parse("2007-12-01T10:15:30"));
        iAppDB.saveBill(bill);

        Bill bill2 = new Bill();
        bill2.setOpenTime(LocalDateTime.parse("2009-10-17T00:15:22"));
        iAppDB.saveBill(bill2);

        Bill bill3 = new Bill();
        bill3.setOpenTime(LocalDateTime.parse("2013-03-12T10:22:47"));
        iAppDB.saveBill(bill3);

        Bill bill4 = new Bill();
        bill4.setOpenTime(LocalDateTime.parse("2014-12-09T10:15:30"));
        bill4 = iAppDB.saveBill(bill4);

        Bill bill5 = new Bill();
        bill5.setOpenTime(LocalDateTime.parse("2015-06-02T10:15:30"));
        bill5 = iAppDB.saveBill(bill5);

        Bill bill6 = new Bill();
        bill6.setOpenTime(LocalDateTime.parse("2016-08-29T20:11:10"));
        bill6 = iAppDB.saveBill(bill6);

        Bill bill7 = new Bill();
        bill7.setOpenTime(LocalDateTime.now());
        iAppDB.saveBill(bill7);

        List<Bill> expectedList = new ArrayList<>();

        expectedList.add(bill4);
        expectedList.add(bill5);
        expectedList.add(bill6);

        LocalDateTime startTime = LocalDateTime.parse("2014-01-01T00:00:00");
        LocalDateTime endTime = LocalDateTime.parse("2016-12-31T23:59:59");

        assertEquals(expectedList, terminalController.filter(startTime, endTime, new CreationDateComparator()));
    }


}