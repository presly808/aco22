package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

public class TestBillController {

    private BillController bc;

    @Before
    public void initData() {
        this.bc = new BillController();
    }

    @After
    public void erraseData(){
        this.bc = null;
    }

    @Test
    public void testCloseBill() {
        Bill bill = new Bill();
        bc.closeBill(bill);
        Product product = new Product();
        bc.addProduct(bill, product);
        bc.closeBill(bill);
        bc.printBill(bill);


//        System.out.println(bill.getCloseTime());
        Assert.assertTrue(bill.getCloseTime() != null);
    }
}
