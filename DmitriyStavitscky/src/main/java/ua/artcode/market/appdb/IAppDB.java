package ua.artcode.market.appdb;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.List;

public interface IAppDB {

    int genId();

    void addActionToHistory(String message);

    Salesman findSalesmanByLoginOrName(String loginOrName);

    void addProductToDataBase(String name, double price);

    List<Bill> getAllBills();

    Bill findBillById(int id);

    Salesman findSalesmanById(int id);

    Product findProductById(int id);

    Bill removeBill(int id);

    Product removeProduct(int id);

    Salesman removeSalesman(int id);

    Bill update(Bill bill);
}
