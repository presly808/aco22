import Utils.StringGenerator;
import Utils.TerminalUtils;
import controllers.BillController;
import controllers.TerminalController;
import models.Bill;
import models.Product;
import models.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TerminalTests {

    private TerminalController terminal;

    @Before
    public void prepareData(){
        this.terminal = new TerminalController();
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
        Assert.assertNull("BillController no created", product);

        Bill addedBill = terminal.createBill(bill2);
        product = terminal.addProduct(addedBill, productName);

        Assert.assertNotNull("Product created", product);
        Assert.assertEquals(productName, product.getName());
        Assert.assertFalse("Long id generated", product.getId() == 0);
    }

    @Test
    public void testFindSalesmanByLoginOrFullName(){
        Bill bill = new Bill();


        String salesManName = null;
        int salesManQuantity = 10;

        for (int i = 0; i < salesManQuantity; i++) {
            salesManName = StringGenerator.generateName();
            String salesManPassword = String.valueOf(TerminalUtils.longIdGenerator());
            Bill addedBills = terminal.createBill(bill);
            bill.setSalesman(new Salesman(salesManName, salesManPassword));
        }

        Salesman salesman = terminal.findSalesmanByLoginOrFullName(salesManName);
        Assert.assertNotNull("Salesman found", salesman);
        Assert.assertEquals(salesManName, salesman.getFullname());
    }

    @Test
    public void testSortBillsByCreatedDate(){
        for (int i = 0; i < 100; i++) {
            terminal.createBill(new Bill());
        }
        List<Bill> sortedBills = terminal.sortBillListByDateCreation();
        Assert.assertTrue(isListSorted());
    }

    private boolean isListSorted() {
        List<Bill> list = new ArrayList<>();
        list.addAll(terminal.getBillSet());
        boolean sorted = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
        }
        return sorted;
    }

}
