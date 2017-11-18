package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public class TestTermnalController {
    private TerminalController tc;

    @Before
    public void initData() {
        this.tc = new TerminalController();
    }

    @After
    public void eraseData(){
        this.tc = null;
    }

    @Test
    public void testCreateBill() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("1","1", "1");
        Bill bill = tc.createBill(terminal, salesman);
//        System.out.println(bill);
        Assert.assertNotEquals(0, bill.getBillId());
    }
    @Test
    public void testAddProduct() {
        Terminal terminal = new Terminal();
        Salesman salesman = new Salesman("1","1", "1");
        Product pr1 = new Product();
        Product pr2 = new Product();
        Product pr3 = new Product();
        Bill bill = tc.createBill(terminal, salesman);
        tc.addProduct(bill, pr1);
        tc.addProduct(bill, pr2);
        tc.addProduct(bill, pr3);
//        System.out.println(bill);
        Assert.assertNotEquals(0, bill.getBillId());
    }
}
