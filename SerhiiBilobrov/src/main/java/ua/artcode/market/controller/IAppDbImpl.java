package ua.artcode.market.controller;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 19.11.17.
 */
public class IAppDbImpl implements IAppDb {

    private int billNextId;
    private int productNextId;

    private List<Product> products;
    private List<Bill> bills;

    public IAppDbImpl() {
        this.products = new ArrayList<>();
        this.bills = new ArrayList<>();

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
    public Bill findByBillId(int billId) {

        for (Bill bill : bills) {
            if(billId == bill.getId()){
                return bill;
            }
        }
        return null;
    }

    @Override
    public Product findByProductId(int billId) {

        for (Product product : products) {
            if(billId == product.getId()){
                return product;
            }
        }
        return null;
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(billNextId++);

        bills.add(bill);

        return bill;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(productNextId++);

        products.add(product);

        return product;
    }

    @Override
    public Bill removeBill(int bill) {

        Bill found = findByBillId(bill);

        if(found == null){
            System.out.println("Not found with id " + bill);
            return null;
        }

        bills.remove(found);

        return found;
    }

    @Override
    public Product removeProduct(int remove) {
        return null;
    }


    @Override
    public Bill update(Bill bill) {
        int index = bills.indexOf(bill);

        if(index == -1){
            System.out.println("Not found with id " + bill);
            return null;
        }

        return bills.set(index,bill);
    }
}
