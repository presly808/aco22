package week1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.controller.Terminal;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

public class TerminalTest {

    Product product;
    Product[] products;
    Bill bill;
    Bill bilss;
    Terminal terminal;
    Salesman salesman;
    Salesman [] sales;


    @Before
    public void initData() {

        product = new Product(1,"Grecha", 40.50);
        products = new Product[10];
        salesman = new Salesman("Marusia", "maria", "pass", false);
        sales = new Salesman[10];
       // bill = new Bill(1,products,salesman,0.0d,"12.50");
        terminal = new Terminal();

       // terminal.createBill(bill, salesman);
    }

    @Test
    public void createBill()  {

        //Assert.assertFalse(terminal.createBill(bill, salesman));
        salesman.setStatus(true);
       // Assert.assertTrue(terminal.createBill(bill, salesman));

    }

    @Test
    public void closeAndSaveBill(){
       // Assert.assertFalse(terminal.closeAndSaveBill(bill));
       // bill.setCloseTime(null);
       // Assert.assertTrue(terminal.closeAndSaveBill(bill));

    }

    @Test
    public void minBill(){

        Assert.assertEquals(0,terminal.minBill());
    }


}