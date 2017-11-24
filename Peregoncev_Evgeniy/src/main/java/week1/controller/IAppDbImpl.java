package week1.controller;

import week1.interfaces.IAppDb;
import week1.model.Bill;

import week1.model.Product;
import week1.model.Salesman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class IAppDbImpl implements IAppDb {

    private int billNextId;

    private List<Bill> billList;
    private List<Salesman> salesmanList;
    private List<Product> productList;

    public IAppDbImpl() {
        this.billList = new ArrayList<>();

        this.salesmanList = new ArrayList<>();

        salesmanList.add(new Salesman("Inna", "login", "pass", 0));
        salesmanList.add(new Salesman("Zina", "login1", "pass", 1));
        salesmanList.add(new Salesman("1", "2", "3", 2));

        this.productList = new ArrayList<>();

        productList.add(new Product("apricot", 16.5, 1));
        productList.add(new Product("banana", 20.0, 2));
        productList.add(new Product("watermelon", 50.5, 3));
        productList.add(new Product("apple", 8.5, 4));

    }

    @Override
    public List<Bill> getAllBills() {
        return billList;
    }

    @Override
    public List<Salesman> getAllSalesMans() {
        return salesmanList;
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {

        for (Salesman salesman : salesmanList) {
            if (salesman.getLogin().equals(login)) {
                return salesman;
            }
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product findByProductId(int productId) {
        for (Product product : productList) {
            if (productId == product.getId()) {
                return product;
            }
        }

        return null;
    }

    @Override
    public Bill findByBillId(int billId) {

        for (Bill bill : billList) {
            if (billId == bill.getId()) {
                return bill;
            }
        }
        return null;
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(billNextId++);

        billList.add(bill);

        return bill;
    }

    @Override
    public Bill removeBill(int billId) {

        Bill found = findByBillId(billId);

        if (found == null) {
            System.out.println("Not found with id " + billId);
            return null;
        }

        billList.remove(found);

        return found;
    }

    @Override
    public Bill update(Bill bill) {
        int index = billList.indexOf(bill);

        if (index == -1) {
            System.out.println("Not found with id " + bill);
            return null;
        }

        return billList.set(index, bill);
    }
}
