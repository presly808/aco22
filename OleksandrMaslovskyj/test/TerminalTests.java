import Utils.StringGenerator;
import Utils.TerminalUtils;
import implementation.Bill;
import implementation.Terminal;
import models.Product;
import models.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalTests {

    private Terminal terminal;

    @Before
    public void prepareData(){
        this.terminal = new Terminal();
    }

    @After
    public void clearData(){
        this.terminal = null;
    }

    @Test
    public void testCreateBillMethod(){
        Bill bill = terminal.createBill();

        Assert.assertNotNull("Bill created", bill);
        Assert.assertTrue("Long id generated", bill.getId() != 0);
    }

    @Test
    public void testAddProduct(){
        String productName = StringGenerator.generateName();
        Product product = terminal.addProduct(productName);

        Assert.assertNull("Bill no created", product);

        Bill bill = terminal.createBill();
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
            Bill bill = terminal.createBill();
            bill.setSalesman(new Salesman(salesManName, salesManPassword));
        }

        Salesman salesman = terminal.findSalesmanByLoginOrFullName(salesManName);
        Assert.assertNotNull("Salesman found", salesman);
        Assert.assertEquals(salesManName, salesman.getFullname());
    }

}
