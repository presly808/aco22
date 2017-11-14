package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

public interface IBill {
    boolean addProduct(Bill bill, Product product);
    double calculateAmountPrice (Bill bill);
    boolean closeBill(Bill bill);
    String printBill(Bill bill);
}
