package main.java.ua.artcode.market.appDB;

import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.Product;
import main.java.ua.artcode.market.models.SalesMan;


public interface IAppDB{

    int genId();

    void addActionToHistory(String messege);

    SalesMan findSalesMan(String loginOrName);

    void addProductToDataBase(String name, double price);

    Bill findBillById(int id);

    Product findProductById();

    Bill removeBill(int id);

    SalesMan removeSalesMan(int id);

    Product removeproduct(int id);

    Bill update(Bill bill);

}
