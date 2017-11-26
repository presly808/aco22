package interfaces;

import models.Bill;
import models.Product;

public interface IBillLogic {

    Product addProductToBill(Bill bill, String name);

    void closeBill(Bill bill);

    double calculateAmountPrice(Bill bill);

    String printBill(Bill bill);

}
