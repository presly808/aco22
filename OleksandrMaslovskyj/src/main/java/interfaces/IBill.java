package main.java.interfaces;


import main.java.models.Product;

public interface IBill {

    Product addProduct(String name);

    void closeBill();

    double calculateAmountPrice();

    void printBill();

}
