package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;

import java.util.List;

public class BillControllerTest {

    private BillController bc = new BillController();

//    @Test
//    public void addProduct() throws Exception {
//
//
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), Product.initProductsPrice(5));
//
//        int num = currentBill.getQuantityGoods();
//
//        bc.addProduct(currentBill, 2, 1);
//
//        Assert.assertEquals((""+(num+1)), (""+currentBill.getQuantityGoods()));
//    }
//
//    @Test
//    public void changeProduct() throws Exception {
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), Product.initProductsPrice(5));
//
//        bc.addProduct(currentBill, 2, 8);
//        bc.addProduct(currentBill, 4, 1);
//
//        int num = currentBill.getQuantityGoods();
//        bc.changeProduct(currentBill, 2, 0);
//
//        Assert.assertEquals(""+num, ""+(currentBill.getQuantityGoods()+1));
//    }
//
//    @Test
//    public void calculateAmountPrice() throws Exception {
//
//        List<Product> productList = Product.initProductsPrice(5);
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), productList);
//
//        double amountPrice = currentBill.getAmountPrice();
//        bc.addProduct(currentBill, 2, 1);
//
//        bc.calculateAmountPrice(currentBill);
//
//        Assert.assertEquals((""+(amountPrice+productList.get(1).price)), (""+currentBill.getAmountPrice()));
//    }
//
//    @Test
//    public void GetBillProductsForPrint() throws Exception {
//
//        List<Product> productList = Product.initProductsPrice(5);
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), Product.initProductsPrice(5));
//
//        bc.addProduct(currentBill,2, 8);
//        bc.addProduct(currentBill, 4, 1);
//        bc.addProduct(currentBill, 5, 6);
//        bc.addProduct(currentBill, 1, 4);
//        bc.addProduct(currentBill, 3, 3);
//
//        Assert.assertNotEquals("", ""+bc.GetBillProductsForPrint(currentBill));
//    }
//
//    @Test
//    public void getBillHeadInfoForPrint() throws Exception {
//
//        List<Product> productList = Product.initProductsPrice(5);
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), Product.initProductsPrice(5));
//
//        bc.addProduct(currentBill,2, 8);
//        bc.addProduct(currentBill, 4, 1);
//        bc.addProduct(currentBill, 5, 6);
//        bc.addProduct(currentBill, 1, 4);
//        bc.addProduct(currentBill, 3, 3);
//
//        bc.calculateAmountPrice(currentBill);
//
//        Assert.assertNotEquals("", ""+bc.getBillHeadInfoForPrint(currentBill));
//    }
//
//    @Test
//    public void closeBill() throws Exception {
//
//        List<Product> productList = Product.initProductsPrice(5);
//
//        Bill currentBill = new Bill(1, new SalesMan( "salesMan1", "", ""), Product.initProductsPrice(5));
//
//        bc.closeBill(currentBill);
//
//        Assert.assertEquals(true, currentBill.closed);
//    }
}