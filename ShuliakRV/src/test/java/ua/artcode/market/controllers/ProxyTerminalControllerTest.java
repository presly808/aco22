package ua.artcode.market.controllers;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.comparators.BillIdComparator;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;
import ua.artcode.market.views.Terminal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProxyTerminalControllerTest {

    private ProxyTerminalController terminalController;

    private Terminal terminal;

    private Salesman salesman;

    @Before
    public void setUp() throws Exception {
        terminalController = (ProxyTerminalController) FactoryITerminal.createITerminalController();
        terminal = new Terminal(terminalController);
        salesman = terminalController.getAppDB().getAllSalesman().get(0);
        assertEquals(salesman, terminalController.logIn(salesman.getLogin(), salesman.getPassword()));
        salesman = terminalController.logIn(salesman.getLogin(), salesman.getPassword());
    }

    @Test
    public void createBill() throws Exception {
        Bill bill = terminalController.createBill(salesman);
        assertEquals(1, bill.getId());
    }

    @Test
    public void addProduct() throws Exception {
        Bill bill = terminalController.createBill(salesman);
        Product product = terminalController.getAppDB().getAllProducts().get(0);
        bill = terminalController.addProduct(bill.getId(), product);
        product = terminalController.getAppDB().getAllProducts().get(1);
        bill = terminalController.addProduct(bill.getId(), product);
        assertEquals(2, bill.getProducts().size());
    }

    @Test
    public void closeAndSaveBill() throws Exception {
        Bill bill = terminalController.createBill(salesman);
        Product product = terminalController.getAppDB().getAllProducts().get(0);
        bill = terminalController.addProduct(bill.getId(), product);
        product = terminalController.getAppDB().getAllProducts().get(1);
        bill = terminalController.addProduct(bill.getId(), product);
        bill = terminalController.closeAndSaveBill(bill.getId());
        assertEquals(true, bill.isClosed());
    }

    @Test
    public void getTopNOfSalesMen() throws Exception {
        assertEquals(0, terminalController.getTopNOfSalesMen(1).get(0).getSoldProducts());
        Bill bill = terminalController.createBill(salesman);
        Product product = terminalController.getAppDB().getAllProducts().get(0);
        bill = terminalController.addProduct(bill.getId(), product);
        product = terminalController.getAppDB().getAllProducts().get(1);
        bill = terminalController.addProduct(bill.getId(), product);
        bill = terminalController.closeAndSaveBill(bill.getId());
        assertEquals(2, terminalController.getTopNOfSalesMen(1).get(0).getSoldProducts());
    }

    @Test
    public void doSomeStatisticStuff() throws Exception {
        Bill bill = terminalController.createBill(salesman);
        Product product = terminalController.getAppDB().getAllProducts().get(0);
        bill = terminalController.addProduct(bill.getId(), product);
        product = terminalController.getAppDB().getAllProducts().get(1);
        bill = terminalController.addProduct(bill.getId(), product);
        bill = terminalController.closeAndSaveBill(bill.getId());
        Statistic statistic = terminalController.doSomeStatisticStuff();
        assertEquals(2,statistic.getCountSoldProducts());

    }

    @Test
    public void filter() throws Exception {
        Bill bill = terminalController.createBill(salesman);
        Product product = terminalController.getAppDB().getAllProducts().get(0);
        bill = terminalController.addProduct(bill.getId(), product);
        product = terminalController.getAppDB().getAllProducts().get(1);
        bill = terminalController.addProduct(bill.getId(), product);
        bill = terminalController.closeAndSaveBill(bill.getId());

        List<Product> products = bill.getProducts();
        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(salesman);
        List<Bill> bills = terminalController.filter(salesmen,products,null,
                null,new BillIdComparator());
        assertEquals(1,bills.size());
    }

}