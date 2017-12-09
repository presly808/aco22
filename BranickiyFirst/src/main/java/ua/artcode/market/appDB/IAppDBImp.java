package src.main.java.ua.artcode.market.appDB;

import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Product;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.appDB.IAppDB;


import java.util.ArrayList;
import java.util.List;

public abstract class IAppDBImp implements IAppDB {

    private List<Bill> bills = new ArrayList<>();

    private List<Salesman> salesmen = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<String> historyOfActions = new ArrayList<>();

    private int countOfId;


    @Override
    public int genId() {

        return ++countOfId;
    }

    @Override
    public void addActionToHistory(String message) {

        this.historyOfActions.add(message);
    }

    @Override
    public Salesman findSalesman(String loginOrName) {

        if (loginOrName == null || loginOrName.isEmpty() )
        {return null;}

        for (Salesman salesman : salesmen)
        if (salesman.getLogin().equals(loginOrName))
        {return salesman;}

        System.out.println("Salesman not found. Try again.");

        return null;
    }


    @Override
    public void addProductToDataBase(String name, double price) {

    }

    @Override
    public Bill findBillById(int id) {

        if (id == 0)
        {return null;}

        for (Bill bill : bills){
            if (bill.getId() == id)
            {return bill;}
        }
        System.out.println("Bill with this ID not found. Try again.");
        return null;
    }

    @Override
    public Product findProductById() {

      if (id == 0)
      {return null;}

      for (Product product : products){
          if (product.getId == id)
          {return product;}
      }
        System.out.println("Bill with this ID not found. Try again.");
        return null;

    }

    @Override
    public Bill removeBill(int id) {
        return bills.remove(bills.indexOff(findBillById(id)));
    }


    @Override
    public Salesman removeSalesman(int id) {
        return salesmen.remove(salesmen.indexOff(findSalesman(loginOrName)));
    }

    @Override
    public Product removeproduct(int id) {

        return products.remove(products.indexOff(findProductById(id)));
    }

    @Override
    public Bill update(Bill bill) {

        int index = bills.indexOff(findBillById(id));

        if (index == 0.1) {
            System.out.println("bill with id not found" + bill);
            return null;
        }

        return bills.set(index, bill);

        return null;
    }


    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Salesman> getsalesmans() {
        return salesmen;
    }

    public void setsalesmans(List<Salesman> salesmans) {
        this.salesmen = salesmans;
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
