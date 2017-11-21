package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IAppDbImpl implements IAppDb {

    private int billNextId;
    private int productNextId;

    private List<Bill> bills;
    private Map<Product,Integer> products;

    public IAppDbImpl() {
        this.bills = new ArrayList<>();
        this.products = Generator.randomProducts();
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public List<Bill> getBills() {
        return bills;
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return products;
    }

    @Override
    public Bill findBillById(int id) {
        for (Bill bill : bills) {
            if (bill.getId() == id) return bill;
        }
        return null;
    }

    @Override
    public Product findProductById(int id) {
        for (Product product : products.keySet()) {
            if (product.getId() == id) return product;
        }
        return null;
    }

    @Override
    public Bill removeBill(int id) {
        Bill bill = findBillById(id);

        if (bill == null) return null;

        bills.remove(bill);

        return bill;
    }

    @Override
    public Product removeProduct(int id) {
        Product product = findProductById(id);
        if (product != null) {
            products.replace(product, products.get(product) - 1);
        }
        return product;
    }

    @Override
    public Bill saveBill(Bill bill) {
        if (bill != null) {
            bill.setId(billNextId++);
            bills.add(bill);
            return bill;
        } return null;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(productNextId++);
        products.put(product, 0);
        return product;
    }

    @Override
    public Bill update(Bill bill) {
        int index = bills.indexOf(bill);

        if (index == -1) return null;

        return bills.set(index, bill);
    }

    @Override
    public Salesman createSalesman(String fullName, String login, String password) throws IOException {
        return null;
    }

    @Override
    public Salesman login(String login, String password) throws IOException {
        return null;
    }

    @Override
    public Salesman logout(Salesman salesman) {
        return null;
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        return null;
    }


}
