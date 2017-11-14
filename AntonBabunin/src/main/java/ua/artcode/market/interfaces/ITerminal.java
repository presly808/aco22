package ua.artcode.market.interfaces;


import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

public interface ITerminal {
    boolean createBill(Bill bill);
    boolean addProduct(Bill bill, Product product);
    boolean closeAndSafeBill(Bill bill);
}