package hw1.controller;

import hw1.model.Bill;
import hw1.model.DBItem;
import hw1.model.Product;
import hw1.model.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IAppDBImpl implements IAppDB {

    private List<Bill> bills;
    private List<Salesman> salesmen;
    private List<Product> products;

    private int indexBill;
    private int indexSalesman;
    private int indexProduct;

    public IAppDBImpl() {
        bills = new ArrayList<>();
        salesmen = new ArrayList<>();
        products = new ArrayList<>();
    }


    @Override
    public Bill createBill() {
        return new Bill(++indexBill);
    }

    @Override
    public Product createProduct() {
        return new Product(++indexSalesman);
    }

    @Override
    public Salesman createSalesman() {
        return new Salesman(++indexProduct);
    }

    @Override
    public Bill findBillById(int id) {
        return (Bill) findItemById(id, bills);
    }

    @Override
    public Product findProductById(int id) {
        return (Product) findItemById(id, products);
    }

    @Override
    public Salesman findSalesmanById(int id) {
        return (Salesman) findItemById(id, salesmen);
    }

    private DBItem findItemById(int id, List<DBItem> list){
        for (DBItem item : list){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    @Override
    public Bill updateBill(Bill b) {
        return null;
    }

    @Override
    public Product updateProduct(Product p) {
        return null;
    }

    @Override
    public Salesman updateSalesman(Salesman s) {
        return null;
    }

    @Override
    public List<Bill> getBills() {
        return null;
    }

    @Override
    public List<Salesman> getSalesmen() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}
