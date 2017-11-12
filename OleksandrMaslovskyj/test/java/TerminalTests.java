package java;

import main.java.Utils.StringGenerator;
import main.java.Utils.TerminalUtils;
import main.java.controllers.BillController;
import main.java.controllers.TerminalController;
import main.java.models.Product;
import main.java.models.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        BillController bill = terminal.createBill();

        Assert.assertNotNull("BillController created", bill);
        Assert.assertTrue("Long id generated", bill.getId() != 0);
    }

    @Test
    public void testAddProduct(){
        String productName = StringGenerator.generateName();
        Product product = terminal.addProduct(productName);

        Assert.assertNull("BillController no created", product);

        BillController bill = terminal.createBill();
        product = bill.addProduct(productName);

        Assert.assertNotNull("Product created", product);
        Assert.assertEquals(productName, product.getName());
        Assert.assertFalse("Long id generated", product.getId() == 0);
    }

    @Test
    public void testFindSalesmanByLoginOrFullName(){

        String salesManName = null;
        int salesManQuantity = 10;

        for (int i = 0; i < salesManQuantity; i++) {
            salesManName = StringGenerator.generateName();
            String salesManPassword = String.valueOf(TerminalUtils.longIdGenerator());
            BillController bill = terminal.createBill();
            bill.setSalesman(new Salesman(salesManName, salesManPassword));
        }

        Salesman salesman = terminal.findSalesmanByLoginOrFullName(salesManName);
        Assert.assertNotNull("Salesman found", salesman);
        Assert.assertEquals(salesManName, salesman.getFullname());
    }

}
