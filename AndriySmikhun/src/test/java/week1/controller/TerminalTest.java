package week1.controller;


import org.junit.*;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

public class TerminalTest {


    private Product product1, product2;
    private String myDataTime1, myDataTime2;
    private Bill bill1, bill2, bill3;
    private Salesman salesman1;
    private Terminal terminal;


    @Before
    public void initData() {

        product1 = new Product(1, "Grecha", 40.00);
        product2 = new Product(2, "juse", 20.00);

        salesman1 = new Salesman("Marusia", "maria", "pass", true);
        //salesman2 = new Salesman("Oleg","oleg","pass",true);

        bill1 = new Bill(1, salesman1);
        bill2 = new Bill(2, salesman1);
        bill3 = new Bill(3, salesman1);

        myDataTime1 = "17:10:10 23/7";
        myDataTime2 = "22:10:10 27/7";

        terminal = new Terminal();

        terminal.addSalesman(salesman1);
        terminal.addSalesman(salesman1);

        terminal.createBill(0, salesman1);

        terminal.addBill(bill1);
        terminal.addBill(bill2);
        terminal.addBill(bill3);

        bill1.addProduct(product1);
        bill1.addProduct(product2);
        bill2.addProduct(product2);
        bill3.addProduct(product1);

        bill1.setCloseBill();
        bill2.setCloseBill();
        bill3.setCloseBill();

    }

    @Test
    public void login() {

        Assert.assertTrue(terminal.login("oleg", "pass"));
    }

    @Test
    public void createBill() {

        Bill bill1 = terminal.createBill(7, salesman1);
        Assert.assertTrue(bill1 != null);
        Assert.assertTrue(bill1.getSalesman().equals(salesman1));


    }

    @Test
    public void minBill() {

        Assert.assertEquals(0, terminal.minBill());
    }

    @Test
    public void getSalemanByName() {

        Salesman s1 = terminal.getSalemanByName("Marusia");
        Assert.assertTrue(s1.getFullname().equals("Marusia"));

    }

    @Test
    public void getTopNofSalesMan() {
        Assert.assertEquals(salesman1, terminal.getTopNofSalesMan());
    }

    @Test
    public void averageBill() {
        bill1.setAmountPrice(10.0);
        bill2.setAmountPrice(10.0);
        bill3.setAmountPrice(10.0);
        double everage = terminal.averageBill();
        Assert.assertEquals(everage, 10.0, 1);
    }

    @Test
    public void filterByParameter() {
        Product[] pr = new Product[2];
        pr[0] = product1;
        pr[1] = product2;
        Salesman[] sl = new Salesman[1];
        sl[0] = salesman1;
        Bill[] b1 = terminal.filterByParameter(sl, pr, myDataTime1, myDataTime2);
        Assert.assertEquals(b1[0].getProducts()[0], product1);

    }
}