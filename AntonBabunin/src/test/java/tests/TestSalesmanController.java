package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.SalesmanController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

import java.util.Arrays;


public class TestSalesmanController {

   private SalesmanController sc;

   @Before
   public void initData() {
        this.sc = new SalesmanController();
    }

   @After
   public void erraseData(){
        this.sc = null;
    }

   @Test
   public void testCreateBillNull1() {
       Terminal terminal = new Terminal();
       Salesman salesman = null;
       Bill expected = null;
       Bill actual = sc.createBill(terminal, salesman);
//       System.out.printf("testCreateBillNull1: Result = %s | Terminal = %s |
// Bill = null \n", bool, terminal);
       Assert.assertEquals(expected, actual);
   }

   @Test
   public void testCreateBillNull2() {
       Terminal terminal = null;
       Salesman salesman = new Salesman("salesman1",
               "salesman1","salesman1");
       Bill expected = new Bill();
       Bill actual = sc.createBill(terminal, salesman);
//       System.out.printf("testCreateBillNull2: Result = %s | Terminal = null
// | Bill = %s \n", bool, bill);
       Assert.assertNotEquals(expected, actual);
   }

   @Test
   public void testCreateBillNotNull() {
       Terminal terminal = new Terminal();
       Salesman salesman = new Salesman("salesman1", "salesman1",
               "salesman1");
       Bill bill = sc.createBill(terminal, salesman);
       boolean bool = false;
       System.out.println(bill);
       System.out.println(bill.getBillId());
       System.out.println(bill.getSalesman());
       bool = true;
       System.out.printf("testCreateBillNotNull: Result = %s | Terminal = %s | " +
               "Bill = %s \n", bool, terminal, bill);
       Assert.assertNotEquals(0, bill.getBillId());
   }

   @Test
   public void testAddProductNull1() {
       Terminal terminal = null;
       Bill bill = new Bill();
       Product product = new Product();
       boolean bool = sc.addProduct(terminal,bill,product);
//       System.out.printf("testAddProductNull1: Result = %s | Terminal = null |
// Bill = %s | Product = %s \n", bool, bill, product);
       Assert.assertFalse(bool);
   }

   @Test
   public void testAddProductNullAll() {
       Terminal terminal = null;
       Bill bill = null;
       Product product = null;
       boolean bool = sc.addProduct(terminal,bill,product);
//       System.out.printf("testAddProductAllNull: Result = %s | Terminal = %s |
// Bill = %s | Product = %s \n", bool, terminal, bill, product);
       Assert.assertFalse(bool);
   }

   @Test
   public void testAddProductSomePeaces() {
       Terminal terminal = new Terminal();
       Salesman salesman = new Salesman("salesman1", "salesman1",
               "salesman1");
       Bill bill = sc.createBill(terminal, salesman);
       Product product1 = new Product();
       Product product2 = new Product();
       boolean booll = sc.addProduct(terminal,bill,product1);
       boolean bool2 = sc.addProduct(terminal,bill,product2);
       boolean bool = booll && bool2;
       System.out.printf("testAddProductSomePeaces: Result = %s | %s\n", bool,
               terminal);
       Assert.assertFalse(Arrays.equals(bill.getProducts().keySet().toArray(),
               new Product[]{null, null}));
   }

   @Test
   public void testCloseAndSafeBill() throws InterruptedException {
       Terminal terminal = new Terminal();
       Salesman salesman = new Salesman("salesman1", "salesman1",
               "salesman1");
       Bill bill = sc.createBill(terminal, salesman);
       Thread.sleep(1000);
       boolean bool = sc.closeAndSafeBill(terminal,bill);
       System.out.printf("testCloseAndSafeBill: Result = %s | %s\n", bool,
               terminal);
       Assert.assertTrue(bool);
   }

   @Test
   public void testCloseAndSafeBill1() {
       Terminal terminal = new Terminal();
       Salesman salesman = new Salesman("salesman1", "salesman1",
               "salesman1");
       Bill bill = sc.createBill(terminal, salesman);
       Product product1 = new Product();
       Product product2 = new Product();
       sc.addProduct(terminal,bill,product1);
       sc.addProduct(terminal,bill,product2);
       boolean bool = sc.closeAndSafeBill(terminal,bill);
       System.out.printf("testCloseAndSafeBill: Result = %s | %s\n", bool,
               terminal);
       Assert.assertTrue(bool);
   }
}
