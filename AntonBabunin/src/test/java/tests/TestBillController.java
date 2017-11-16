package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.models.Bill;

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
//        System.out.println(bill.getCloseTime());
        Assert.assertTrue(bill.getCloseTime() != null);
    }
}
