package week1.controller;


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
    private int productNextId;

    private List<Product> productList;
    private List<Bill> billList;
    private List<Salesman> salesmanList;

    public IAppDbImpl() {
        this.productList = new ArrayList<>();
        this.billList = new ArrayList<>();
        this.salesmanList = new ArrayList<>();
    }

    @Override
    public List<Bill> getAllBills() {
        return billList;
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public List<Salesman> getAllSalesmans() {
        return salesmanList;
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
    public Product findByProductId(int billId) {

        for (Product product : productList) {
            if (billId == product.getId()) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Salesman findBySalesmanId(int billId) {

        for (Salesman salesman : salesmanList) {
            if (billId == salesman.getId()) {
                return salesman;
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
    public Product saveProduct(Product product) {
        product.setId(productNextId++);

        productList.add(product);

        return product;
    }

    @Override
    public Bill removeBill(int bill) {

        Bill found = findByBillId(bill);

        if (found == null) {
            System.out.println("Not found with id " + bill);
            return null;
        }

        billList.remove(found);

        return found;
    }

    @Override
    public Product removeProduct(int remove) {
        return null;
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
