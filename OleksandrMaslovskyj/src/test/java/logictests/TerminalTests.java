package logictests;

import controllers.BillController;
import utils.StringGenerator;
import utils.TerminalUtils;
import controllers.TerminalController;
import models.Bill;
import models.Product;
import models.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class TerminalTests {

    private TerminalController terminal;

    @Before
    public void prepareData(){
        this.terminal = new TerminalController(new BillController());
    }

    @After
    public void clearData(){
        this.terminal = null;
    }

    @Test
    public void testCreateBillMethod(){
        Bill bill = terminal.createBill(new Bill());

        Assert.assertNotNull("BillController created", bill);
        Assert.assertTrue("Long id generated", bill.getId() != 0);
    }

    @Test
    public void testAddProduct(){
        Bill bill = new Bill();
        String productName = StringGenerator.generateName();
        Product product = terminal.addProduct(bill, productName);

        Bill bill2 = new Bill();
        Assert.assertNotNull("BillController no created", product);

        Bill addedBill = terminal.createBill(bill2);
        product = terminal.addProduct(addedBill, productName);

        Assert.assertNotNull("Product created", product);
        Assert.assertEquals(productName, product.getName());
        Assert.assertFalse("Long id generated", product.getId() == 0);
    }

    @Test
    public void testFindSalesmanByLoginOrFullName(){
        String salesManName = null;
        int salesManQuantity = 10;

        for (int i = 0; i < salesManQuantity; i++) {
            Bill bill = new Bill();
            salesManName = StringGenerator.generateName();
            String salesManPassword = String.valueOf(
                                TerminalUtils.longIdGenerator());
            Bill addedBills = terminal.createBill(bill);
            Salesman salesman = new Salesman(salesManName, salesManPassword);
            salesman.setFullname(salesManName);
            addedBills.setSalesman(salesman);
        }

        Salesman salesman = terminal.
                    findSalesmanByLoginOrFullName(salesManName, null);
        Assert.assertNotNull("Salesman found", salesman);
        Assert.assertEquals(salesManName, salesman.getFullname());
    }

    @Test
    public void testSortBillsByCreatedDate(){
        for (int i = 0; i < 100; i++) {
            terminal.createBill(new Bill());
        }
        List<Bill> sortedBills = terminal.sortBillListByDateCreation();
        Assert.assertTrue(isListSorted(sortedBills));
    }

    @Test
    public void testFindBillById(){
        Set<Bill> billSet = terminal.getBillSet();
        Assert.assertTrue("Empty set", billSet.size() == 0);
        for (int i = 0; i < 10; i++) {
            terminal.createBill(new Bill());
        }
        Assert.assertTrue(terminal.getBillSet().size() == 10);
    }

    @Test
    public void testGetBillsByStartAndEndDates(){
        TerminalController terminalController = generateBills();
        List<Bill> billList = terminalController.
                getBillsByStartAndEndDates(new Date(System.currentTimeMillis()),
                                    new Date(System.currentTimeMillis() +
                                        Integer.MAX_VALUE));
        Assert.assertTrue(billList.size() == 0);
    }

    @Test
    public void testGetBillsByCreator(){
        TerminalController terminalController = generateBills();
        List<Bill> billList = terminalController.
                getBillsByCreator(new Salesman("", ""));
        Assert.assertTrue(billList.isEmpty());
    }

    private TerminalController generateBills() {
        TerminalController terminalController =
                new TerminalController(new BillController());
        for (int i = 0; i < 100; i++) {
            Bill bill = terminalController.createBill(new Bill());
            bill.setSalesman(
                    new Salesman(StringGenerator.generateName(),
                            StringGenerator.generateName()));
        }
        return terminalController;
    }

    private boolean isListSorted(List<Bill> list) {
        boolean sorted = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
        }
        return sorted;
    }

}
