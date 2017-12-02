package ua.artcode.market.model;

import
        org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;

import java.util.List;

public class TerminalTest {

//    private BillController bc = new BillController();
//
//    @Test
//    public void createBill() throws Exception {
//
//        Terminal currentTerminal = new Terminal(bc);
//        List<Product> productList = Product.initProductsPrice(5);
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), productList);
//
//        Assert.assertEquals(new Bill(1, new SalesMan( "salesMan1", "", ""), productList).getClass(), currentBill.getClass());
//    }
//
//    @Test
//    public void saveBill() throws Exception {
//
//        Terminal currentTerminal = new Terminal(bc);
//        List<Product> productList = Product.initProductsPrice(5);
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), productList);
//
//        bc.closeBill(currentBill);
//
//        int quontityBill = currentTerminal.countQuontityBills();
//        currentTerminal.saveBill(currentBill);
//
//        Assert.assertEquals(quontityBill, currentTerminal.countQuontityBills()-1);
//
//    }

}