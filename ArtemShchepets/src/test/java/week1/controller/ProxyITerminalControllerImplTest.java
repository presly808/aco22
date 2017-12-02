package week1.controller;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.comparators.CreationDateComparator;
import week1.model.Bill;
import week1.model.Product;

import java.time.LocalDateTime;

public class ProxyITerminalControllerImplTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        terminalController = new ProxyITerminalControllerImpl(ITerminalControllerFactory.create());
    }

    @After
    public void tearDown() throws Exception {
        terminalController.getAllBills().clear();
        terminalController = null;
    }

    @Test
    public void login() throws Exception {
        terminalController.login("login", "password");
        Assert.assertNull(null);
    }

    @Test
    public void createBill() throws Exception {
        terminalController.createBill();
        Assert.assertNull(null);
    }

    @Test
    public void addProduct() throws Exception {

        Bill bill = terminalController.createBill();

        terminalController.addProduct(bill.getId(), new Product());
        Assert.assertNull(null);
    }

    @Test
    public void getAllBills() throws Exception {

        terminalController.getAllBills();
        Assert.assertNull(null);
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        terminalController.closeBill(bill.getId());
        Assert.assertNull(null);
    }

    @Test
    public void findBillById() throws Exception {

        Bill bill = terminalController.createBill();
        terminalController.findBillById(bill.getId());
        Assert.assertNull(null);
    }

    @Test
    public void findSellerByLoginOrFullName() throws Exception {

        terminalController.findSellerByLoginOrFullName("worker1");
        Assert.assertNull(null);
    }

    @Test
    public void getTopOfSalesman() throws Exception {

        terminalController.getTopOfSalesman();
        Assert.assertNull(null);
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {

        terminalController.doSomeStatisticStuff();
        Assert.assertNull(null);
    }

    @Test
    public void filter() throws Exception {

        LocalDateTime startTime = LocalDateTime.parse("2014-01-01T00:00:00");
        LocalDateTime endTime = LocalDateTime.parse("2016-12-31T23:59:59");

        terminalController.filter(startTime, endTime, new CreationDateComparator());
        Assert.assertNull(null);
    }

    @Test
    public void turnOnTerminalLogger() throws Exception {

        terminalController.turnOnTerminalLogger();
        Assert.assertNull(null);
    }

    @Test
    public void turnOnDatabaseLogger() throws Exception {

        terminalController.turnOnDatabaseLogger();
        Assert.assertNull(null);
    }

}