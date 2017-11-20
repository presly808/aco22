package week1.database;

import week1.interfaces.IAppDB;
import week1.model.Bill;
import week1.model.Product;

import java.util.ArrayList;
import java.util.List;

public class IAppDBImpl implements IAppDB {

    private int billNextId;
    private int productNextId;

    private List<Bill> bills;
    private List<Product> products;

    public IAppDBImpl() {
        this.bills = new ArrayList<>();
        this.products = new ArrayList<>();
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
            if (billId == bill.getId()) return bill;
        }
        return null;
    }

    @Override
    public Product findByProductId(int productId) {
        for (Product product : products) {
            if (productId == product.getId()) return product;
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
    public Bill removeBill(int billId) {

        Bill found = findByBillId(billId);

        if (found == null) {
            System.out.println("Not found bill with such id");
            return null;
        }

        bills.remove(found);

        return found;
    }

    @Override
    public Product removeProduct(int productId) {

        Product found = findByProductId(productId);

        if (found == null) {
            System.out.println("Not found product with such id");
            return null;
        }

        products.remove(found);

        return found;
    }

    @Override
    public Bill update(Bill bill) {

        int index = bills.indexOf(bill);

        if (index == -1) {
            System.out.println("Not found bill with such id");
            return null;
        }

        return bills.set(index,bill);
    }
}
