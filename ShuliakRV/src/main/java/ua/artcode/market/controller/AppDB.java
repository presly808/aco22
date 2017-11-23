package ua.artcode.market.controller;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.*;


import java.util.ArrayList;
import java.util.List;

public class AppDB implements IAppDb {

    private int nextBillId;
    private int nextProductId;

    private List<Product> products;
    private List<Salesman> salesmen;
    private List<Bill> bills;


    public AppDB() {

        products = new ArrayList<>();
        salesmen = new ArrayList<>();
        bills = new ArrayList<>();


    }


    @Override
    public List<Bill> getAllBills() {
        return bills;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Salesman> getAllSalesman() {
        return salesmen;
    }

    @Override
    public Bill findByBillId(int billId) {

        for (Bill bill : bills) {
            if (bill.getId()==billId)
                return bill;
        }

        return null;
    }

    @Override
    public Product findByProductId(int productId) {

        for (Product product : products) {
            if (product.getId()==productId)
                return product;
        }

        return null;
    }

    @Override
    public Bill saveBill (Bill bill) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Bill removeBill(int bill) {
        return null;
    }

    @Override
    public Product removeProduct(int remove) {
        return null;
    }

    @Override
    public Bill update(Bill bill) {
        return null;
    }
}
