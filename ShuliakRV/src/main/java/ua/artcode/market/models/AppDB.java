package ua.artcode.market.models;

import ua.artcode.market.utils.Utils;

public class AppDB {

    public static final int DEFAULT_COUNT_PRODUCTS = 3;
    public static final int DEFAULT_COUNT_SALESMEN = 3;
    public static final int DEFAULT_COUNT_BILLS = 3;

    private Product[] products = new Product[DEFAULT_COUNT_PRODUCTS];
    private Salesman[] sales = new Salesman[DEFAULT_COUNT_SALESMEN];
    private Bill[] bills = new Bill[DEFAULT_COUNT_BILLS];

    //private int countProducts;
    //private int countSalesmen;
    private int countBill;

    public AppDB() {

        for (int i = 0; i < products.length; i++) {
            products[i] = Utils.generateProduct();
        }

        for (int i = 0; i < sales.length; i++) {
            sales[i] = Utils.generateSalesman();
        }

    }

    public Product[] getProducts() {
        return products;
    }

    public Salesman[] getSales() {
        return sales;
    }

    public Bill[] getBills() {
        return bills;
    }

    public int getCountBill() {
        return countBill;
    }

    public void incCountBill() {
        countBill++;
    }
}
