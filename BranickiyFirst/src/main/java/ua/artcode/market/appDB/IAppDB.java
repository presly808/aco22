package src.main.java.ua.artcode.market.appDB;

import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Product;
import src.main.java.ua.artcode.market.models.Salesman;


public interface IAppDB{

    int genId();

    void addActionToHistory(String message);

    Salesman findSalesmanByLoginOrName(String loginOrName);

    void addProductToDataBase(String name, double price);

    Bill findBillById(int id);

    Product findProductById();

    Bill removeBill(int id);

    Salesman removeSalesman(int id);

    Product removeproduct(int id);

    Bill update(Bill bill);

}
