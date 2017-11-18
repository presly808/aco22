package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.Controller.BillController;
import ua.artcode.market.Model.Bill;
import ua.artcode.market.Model.Product;

public class BillControllerTest {

    private BillController bc = new BillController();

    @Test
    public void addProduct() throws Exception {

        Bill currentBill = new Bill(1, 100, "Vova", Product.initProductsList(5));

        int num = currentBill.getQuantityGoods();

        bc.addProduct(currentBill, 2, 1);

        Assert.assertEquals((""+(num+1)), (""+currentBill.getQuantityGoods()));
    }

    @Test
    public void changeProduct() throws Exception {

        Bill currentBill = new Bill(1, 100, "Vova", Product.initProductsList(5));

        bc.addProduct(currentBill, 2, 8);
        bc.addProduct(currentBill, 4, 1);

        int num = currentBill.getQuantityGoods();
        bc.changeProduct(currentBill, 2, 0);

        Assert.assertEquals(""+num, ""+(currentBill.getQuantityGoods()+1));
    }

    @Test
    public void calculateAmountPrice() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        double amountPrice = currentBill.getAmountPrice();
        bc.addProduct(currentBill, 2, 1);

        bc.calculateAmountPrice(currentBill);

        Assert.assertEquals((""+(amountPrice+productList[1].price)), (""+currentBill.getAmountPrice()));
    }

    @Test
    public void GetProductsForPrint() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        bc.addProduct(currentBill,2, 8);
        bc.addProduct(currentBill, 4, 1);
        bc.addProduct(currentBill, 5, 6);
        bc.addProduct(currentBill, 1, 4);
        bc.addProduct(currentBill, 3, 3);

        Assert.assertNotEquals("", ""+bc.GetProductsForPrint(currentBill));
    }

    @Test
    public void getBillInfoForPrint() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        bc.addProduct(currentBill,2, 8);
        bc.addProduct(currentBill, 4, 1);
        bc.addProduct(currentBill, 5, 6);
        bc.addProduct(currentBill, 1, 4);
        bc.addProduct(currentBill, 3, 3);

        bc.calculateAmountPrice(currentBill);

        Assert.assertNotEquals("", ""+bc.getBillInfoForPrint(currentBill));
    }

    @Test
    public void closeBill() throws Exception {

        Product[] productList = Product.initProductsList(5);

        Bill currentBill = new Bill(1, 100, "Vova", productList);

        bc.closeBill(currentBill);

        Assert.assertEquals(true, currentBill.closed);
    }
}