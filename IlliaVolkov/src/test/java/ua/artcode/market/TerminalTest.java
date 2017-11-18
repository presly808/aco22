package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.Controller.BillController;
import ua.artcode.market.Model.Terminal;
import ua.artcode.market.Model.Bill;
import ua.artcode.market.Model.Product;

public class TerminalTest {

    private BillController bc = new BillController();

    @Test
    public void createBill() throws Exception {

        Terminal currentTerminal = new Terminal(bc);
        Product[] productList = Product.initProductsList(1);

        Bill currentBill = currentTerminal.createBill( "Vovon", productList);

        Assert.assertEquals(new Bill(1, 1,"Vovan", productList).getClass(), currentBill.getClass());
    }

    @Test
    public void saveBill() throws Exception {

        Terminal currentTerminal = new Terminal(bc);
        Product[] productList = Product.initProductsList(1);

        Bill currentBill = currentTerminal.createBill( "Vovon", productList);

        bc.closeBill(currentBill);

        int quontityBill = currentTerminal.countQuontityBills();
        currentTerminal.saveBill(currentBill);

        Assert.assertEquals(quontityBill, currentTerminal.countQuontityBills()-1);

    }

}