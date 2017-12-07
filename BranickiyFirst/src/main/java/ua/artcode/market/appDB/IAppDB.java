package src.main.java.ua.artcode.market.appDB;

import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.Product;
import main.java.ua.artcode.market.models.Salesman;


public interface IAppDB{

    int genId();

    void addActionToHistory(String messege);

    Salesman findSalesman(String loginOrName);

    void addProductToDataBase(String name, double price);

    Bill findBillById(int id);

    Product findProductById();

    Bill removeBill(int id);

    Salesman removeSalesman(int id);

    Product removeproduct(int id);

    Bill update(Bill bill);

}
