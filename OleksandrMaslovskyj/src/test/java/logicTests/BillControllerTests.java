package logicTests;

import controllers.BillController;
import models.Bill;
import models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BillControllerTests {

    private BillController billController;

    @Before
    public void dataPreparation(){
        billController = new BillController();
    }

    @Test
    public void testCloseBillMethod(){
        Bill bill = new Bill();
        billController.closeBill(bill);
        Assert.assertTrue(bill.getCloseTime() != null);

    }

    @Test
    public void testAddProductToBillMethod(){
        Bill bill = new Bill();
        billController.addProductToBill(bill, "Milk");
        billController.addProductToBill(bill, "Coffee");
        Assert.assertTrue(bill.getProducts().size() == 2);
        Assert.assertTrue(bill.getProducts().get(0).getName().equals("Milk"));
    }

    @Test
    public void testCalculateAmountPrice(){
        Bill bill = new Bill();
        billController.addProductToBill(bill, "Milk");
        billController.addProductToBill(bill, "Coffee");
        double calculatedPrice = billController.calculateAmountPrice(bill);
        Assert.assertTrue(calculatedPrice == 0);
        for (Product product : bill.getProducts()) {
            product.setPrice(10);
        }
        Assert.assertTrue(billController.calculateAmountPrice(bill) == 20);

    }

}
