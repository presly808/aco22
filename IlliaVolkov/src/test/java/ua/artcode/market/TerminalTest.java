package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {
    @Test
    public void createBill() throws Exception {

        Terminal currentTerminal = new Terminal();
        Product[] productList = Product.initProductsList(1);

        Bill currentBill = currentTerminal.createBill( "Vovon", productList);

        Assert.assertEquals(new Bill(1, 1,"Vovan", productList).getClass(), currentBill.getClass());
    }

    @Test
    public void saveBill() throws Exception {

        Terminal currentTerminal = new Terminal();
        Product[] productList = Product.initProductsList(1);

        Bill currentBill = currentTerminal.createBill( "Vovon", productList);

        currentBill.closeBill();

        int quontityBill = currentTerminal.countQuontityBills();
        currentTerminal.saveBill(currentBill);

        Assert.assertEquals(quontityBill, currentTerminal.countQuontityBills()-1);

    }

}