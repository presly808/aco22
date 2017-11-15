package tests;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.models.Bill;

public class TestBillController {

    @Test
    public void testCloseBill() {
        BillController bc = new BillController();
        Bill bill = new Bill();
        bc.closeBill(bill);
        System.out.println(bill.getCloseTime());
        Assert.assertTrue(bill.getCloseTime() != null);
    }
}
