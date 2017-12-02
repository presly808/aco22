package main.java.ua.artcode.market.appDB;

import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.Product;
import main.java.ua.artcode.market.models.SalesMan;


import java.util.ArrayList;
import java.util.List;

public class IAppDBImp implements IAppDB{

    private List<Bill> bills = new ArrayList<>();

    private List<SalesMan> salesmans = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<String> historyOfActions = new ArrayList<>();

    private int countOfId;


    @Override
    public int genId() {

        return ++countOfId;
    }

    @Override
    public void addActionToHistory(String messege) {

        this.historyOfActions.add(message);
    }

    @Override
    public SalesMan findSalesMan(String loginOrName) {

        if (loginOrName == null || loginOrName.isEmpty() )
        {return null;}

        for (SalesMan salesMan : salesMans)
        if (salesMan.getLogin().equals(loginOrName))
        {return salesMan;}

        System.out.println("SalesMan not found. Try again.");

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
    public SalesMan removeSalesMan(int id) {
        return salesMans.remove(salesMans.indexOff(findSalesMan(loginOrName)));
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
}
