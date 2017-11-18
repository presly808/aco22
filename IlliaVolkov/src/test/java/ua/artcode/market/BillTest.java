package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillTest {
    @Test
    public void addProduct() throws Exception {

        Bill currentBill = new Bill(1, 100, "Vova", Product.initProductsList(5));

        int num = currentBill.getQuantityGoods();
        currentBill.addProduct( 2, 1);

        Assert.assertEquals((""+(num+1)), (""+currentBill.getQuantityGoods()));
    }

    @Test
    public void changeProduct() throws Exception {

        Bill currentBill = new Bill(1, 100, "Vova", Product.initProductsList(5));

        currentBill.addProduct( 2, 8);
        currentBill.addProduct( 4, 1);

        int num = currentBill.getQuantityGoods();
        currentBill.changeProduct( 2, 0);

        Assert.assertEquals(""+num, ""+(currentBill.getQuantityGoods()+1));
    }

    @Test
    public void calculateAmountPrice() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        double amountPrice = currentBill.getAmountPrice();
        currentBill.addProduct( 2, 1);

        currentBill.calculateAmountPrice();

        Assert.assertEquals((""+(amountPrice+productList[1].price)), (""+currentBill.getAmountPrice()));
    }

    @Test
    public void GetProductsForPrint() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        currentBill.addProduct( 2, 8);
        currentBill.addProduct( 4, 1);
        currentBill.addProduct( 5, 6);
        currentBill.addProduct( 1, 4);
        currentBill.addProduct( 3, 3);

        Assert.assertNotEquals("", ""+Bill.GetProductsForPrint(currentBill));
    }

    @Test
    public void getBillInfoForPrint() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        currentBill.addProduct( 2, 8);
        currentBill.addProduct( 4, 1);
        currentBill.addProduct( 5, 6);
        currentBill.addProduct( 1, 4);
        currentBill.addProduct( 3, 3);

        currentBill.calculateAmountPrice();

        Assert.assertNotEquals("", ""+Bill.getBillInfoForPrint(currentBill));
    }

    @Test
    public void closeBill() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        currentBill.closeBill();

        Assert.assertEquals(true, currentBill.closed);
    }
}