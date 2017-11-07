package models;


import controllers.BillController;

import java.util.Date;
import java.util.*;


public class Bill implements BillController{

    public static final int PRODUCTS_CAPACITY = 10;
    private long id;
    private Product[] productses;
    private Salesman salesman;
    private double amountPrice;
    private Date closeTime;

    public Bill() {
        this.productses = new Product[PRODUCTS_CAPACITY];
    }

    public Product addProduct(String name){
        return new Product(UUID.randomUUID().getMostSignificantBits(), name);
    }

    public void closeBill() {

    }

    public double calculateAmountPrice() {
        return 0;
    }

    public void printBill() {

    }

}


