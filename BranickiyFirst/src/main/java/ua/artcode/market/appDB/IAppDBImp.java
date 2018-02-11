package src.main.java.ua.artcode.market.appDB;

import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Product;
import src.main.java.ua.artcode.market.models.Salesman;


import java.util.List;

public class IAppDBImp implements IAppDB {

    private List<Bill> bills;

    private List<Salesman> salesmans;

    private List<Product> products;

    private List<String> historyOfActions;

    private int countOfId;



    public IAppDBImp(List<Bill> bills, List<Salesman>salesmans,
                     List<Product> products, List<String> historyOfActions, int countOfId) {
        this.bills = bills;
        this.salesmans = salesmans;
        this.products = products;
        this.historyOfActions = historyOfActions;
        this.countOfId = countOfId;
    }

    @Override
    public int genId() {

        return ++countOfId;
    }

    @Override
    public void addActionToHistory(String message) {

        this.historyOfActions.add(message);
    }

    @Override
    public Salesman findSalesmanByLoginOrName(String loginOrName) {
        return null;
    }


    @Override
    public void addProductToDataBase(String name, double price) {
        if (name == null || name.isEmpty()) {
            System.out.println("name is empty or null");

        } else if (price <= 0) {
            System.out.println("price must be > 0");

        } else {
            products.add(new Product(name, price, genId()));
        }
    }

    public Salesman findSalesmanById(int id) {
        if (id == 0) {
            return null;
        }

        return salesmans.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bill findBillById(int id) {
        if (id == 0) {
            return null;
        }

        return bills.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product findProductById(int id) {
        if (id == 0) return null;

        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bill removeBill(int id) {
        return bills.remove(bills.indexOf(findBillById(id)));
    }


    @Override
    public Salesman removeSalesman(int id) {
        return salesmans.remove(salesmans.indexOf(findSalesmanById(id)));
    }

    @Override
    public Product removeProduct(int id) {
        return products.remove(products.indexOf(findProductById(id)));
    }

    @Override
    public Bill update(Bill bill) {
        int index = bills.indexOf(findBillById(bill.getId()));

        if (index == -1) {
            System.out.println("bill with id not found" + bill);
            return null;
        }

        return bills.set(index, bill);
    }


    public List<Bill> getAllBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Salesman> getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(List<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<String> getHistoryOfActions() {
        return historyOfActions;
    }

    public void setHistoryOfActions(List<String> historyOfActions) {
        this.historyOfActions = historyOfActions;
    }
}
