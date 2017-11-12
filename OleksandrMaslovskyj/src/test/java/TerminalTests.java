import Utils.StringGenerator;
import Utils.TerminalUtils;
import controllers.TerminalController;
import models.Bill;
import models.Product;
import models.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

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
            String salesManPassword = String.valueOf(TerminalUtils.longIdGenerator());
            Bill addedBills = terminal.createBill(bill);
            Salesman salesman = new Salesman(salesManName, salesManPassword);
            salesman.setFullname(salesManName);
            addedBills.setSalesman(salesman);
        }

        Salesman salesman = terminal.findSalesmanByLoginOrFullName(salesManName, null);
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

    private boolean isListSorted(List<Bill> list) {
        boolean sorted = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
        }
        return sorted;
    }

}
