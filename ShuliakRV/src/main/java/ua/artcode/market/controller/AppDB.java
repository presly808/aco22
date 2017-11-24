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
    public Salesman findSalesmanByLoginOrFullname(String loginOrFullname) {

        Salesman[] s = new Salesman[appDB.getSales().length];
        int index = 0;

        if ((loginOrFullname == null || loginOrFullname.isEmpty()) ||
                (appDB.getSales().length == 0)) return null;

        for (int i = 0; i < appDB.getSales().length; i++) {
            if (appDB.getSales()[i].getLogin().equals(loginOrFullname) ||
                    appDB.getSales()[i].getFullname().equals(loginOrFullname))
                s[index++] = appDB.getSales()[i];
        }

        if (index > 0) return s;

        return null;
    }

    @Override
    public Bill saveBill (Bill bill) {

        bill.setId(nextBillId++);
        bills.add(bill);

        return bill;
    }

    @Override
    public Product saveProduct(Product product) {

        product.setId(nextProductId++);
        products.add(product);

        return product;
    }

    public Salesman saveSalesman(Salesman salesman) {

        salesmen.add(salesman);

        return salesman;
    }

    @Override
    public boolean removeBill(Bill bill) {

        return bills.remove(bill);
    }

    @Override
    public boolean removeProduct(Product product) {

        return products.remove(product);
    }

    @Override
    public boolean removeSalesman(Salesman salesman) {

        return salesmen.remove(salesman);
    }

    @Override
    public Bill update(Bill bill) {

        return null;
    }
}
