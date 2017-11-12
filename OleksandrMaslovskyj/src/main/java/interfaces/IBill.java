package interfaces;

import models.Bill;
import models.Product;

public interface IBill {

    Product addProductToBill(Bill bill, String name);

    void closeBill(Bill bill);

    double calculateAmountPrice(Bill bill);

    void printBill(Bill bill);

}
