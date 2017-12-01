package ua.artcode.market.databases;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.*;


import java.util.ArrayList;
import java.util.List;

import static ua.artcode.market.utils.Utils.*;

public class AppDB implements IAppDb {

    public static final int countProducts = 10;

    public static final int countSalesman = 10;

    private int nextBillId;
    private int nextProductId;

    private List<Product> products;
    private List<Salesman> salesmen;
    private List<Bill> bills;


    public AppDB() {

        products = new ArrayList<>();

        for (int i = 0; i < countProducts; i++) {
            saveProduct(generateProduct());
        }

        salesmen = new ArrayList<>();

        for (int i = 0; i < countSalesman; i++) {
            saveSalesman(generateSalesman());
        }


        int level = 0;
        int index = 0;

        outer:
        for (int i = 0; i < salesmen.size(); i ++) {

            for (int j = 1; j <= Math.pow(2, level); j++) {

                index = i + j;

                if (index < salesmen.size()) {
                    salesmen.get(i).getSubSalesmen().add(salesmen.get(index));
                } else {
                    break outer;
                } ;

                level++;

            }

        }

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

        if (billId <= 0) return null;

        for (Bill bill : bills) {
            if (bill.getId() == billId)
                return bill;
        }

        return null;
    }

    @Override
    public Product findByProductId(int productId) {

        if (productId <= 0) return null;

        for (Product product : products) {
            if (product.getId() == productId)
                return product;
        }

        return null;
    }

    @Override
    public Salesman findSalesmanByLoginOrFullname(String loginOrFullname) {


        if (loginOrFullname == null || loginOrFullname.isEmpty()) return null;

        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(loginOrFullname) ||
                    salesman.getFullname().equals(loginOrFullname)) {
                return salesman;
            }
        }

        return null;
    }

    @Override
    public Salesman findSalesmanByLoginOAndPassword(String login,
                                                    String password) {


        if (login == null || password == null ||
                login.isEmpty() || password.isEmpty()) return null;

        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(login) &&
                    salesman.getPassword().equals(password)) {
                return salesman;
            }
        }

        return null;
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(++nextBillId);
        bills.add(bill);

        return bill;
    }

    @Override
    public Product saveProduct(Product product) {

        product.setId(++nextProductId);
        products.add(product);

        return product;
    }

    public Salesman saveSalesman(Salesman salesman) {

        salesmen.add(salesman);

        return salesman;
    }

    @Override
    public Bill removeBill(int billId) {

        if (billId <= 0) return null;

        Bill bill = findByBillId(billId);

        if (bill == null) return null;

        bills.remove(bill);

        return bill;
    }

    @Override
    public Product removeProduct(int productId) {

        if (productId <= 0) return null;

        Product product = findByProductId(productId);

        if (product == null) return null;

        products.remove(product);

        return product;
    }

    @Override
    public boolean removeSalesman(Salesman salesman) {

        return salesmen.remove(salesman);
    }

    @Override
    public Bill updateBill(Bill bill) {

        int index = bills.indexOf(bill);

        if (index == -1) return null;

        return bills.set(index, bill);
    }

    @Override
    public Product updateProduct(Product product) {

        int index = products.indexOf(product);

        if (index == -1) return null;

        return products.set(index, product);
    }

    public Salesman updateSalesman(Salesman salesman) {

        int index = salesmen.indexOf(salesman);

        if (index == -1) return null;

        return salesmen.set(index, salesman);

    }


}
