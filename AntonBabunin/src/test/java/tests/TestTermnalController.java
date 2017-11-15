package tests;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public class TestTermnalController {
    @Test
    public void testCreateBill() {
        TerminalController tc = new TerminalController();
        Bill bill = new Bill();
        tc.createBill(bill);
        System.out.println(bill.getBillId());
        Assert.assertNotEquals(0, bill.getBillId());

    }
}
