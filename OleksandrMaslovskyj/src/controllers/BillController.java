package controllers;


import models.Product;

public interface BillController {

    Product addProduct(String name);

    void closeBill();

    double calculateAmountPrice();

    void printBill();

}
