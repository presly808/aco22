package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;

public interface IBill {

    boolean addProduct(Product product);

    String showInfo();

    void closeBill();
}
