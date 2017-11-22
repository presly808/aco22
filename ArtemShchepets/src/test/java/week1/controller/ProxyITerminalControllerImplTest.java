package week1.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.comparators.CreationDateComparator;
import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;

import java.time.LocalDateTime;

public class ProxyITerminalControllerImplTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        terminalController = ProxyITerminalControllerImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        terminalController.getAllBills().clear();
        terminalController = null;
    }

    @Test
    public void login() throws Exception {
        terminalController.login("login", "password");
    }

    @Test
    public void createBill() throws Exception {
        terminalController.createBill();
    }

    @Test
    public void addProduct() throws Exception {

        Bill bill = terminalController.createBill();

        terminalController.addProduct(bill.getId(), new Product());
    }

    @Test
    public void getAllBills() throws Exception {

        terminalController.getAllBills();
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        terminalController.closeBill(bill.getId());
    }

    @Test
    public void findBillById() throws Exception {

        Bill bill = terminalController.createBill();
        terminalController.findBillById(bill.getId());
    }

    @Test
    public void findSellerByLoginOrFullName() throws Exception {

        terminalController.findSellerByLoginOrFullName("worker1");
    }

    @Test
    public void getTopOfSalesman() throws Exception {

        terminalController.getTopOfSalesman();
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {

        terminalController.doSomeStatisticStuff();
    }

    @Test
    public void filter() throws Exception {

        LocalDateTime startTime = LocalDateTime.parse("2014-01-01T00:00:00");
        LocalDateTime endTime = LocalDateTime.parse("2016-12-31T23:59:59");

        terminalController.filter(startTime, endTime, new CreationDateComparator());
    }

    @Test
    public void turnOnTerminalLogger() throws Exception {

        terminalController.turnOnTerminalLogger();
    }

    @Test
    public void turnOnDatabaseLogger() throws Exception {

        terminalController.turnOnDatabaseLogger();
    }

}