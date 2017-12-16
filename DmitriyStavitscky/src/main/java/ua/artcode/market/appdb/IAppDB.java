package ua.artcode.market.appdb;

import ua.artcode.market.exceptions.AppDBExceptions;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.List;

public interface IAppDB {

    int genId();

    void addActionToHistory(String message);

    Salesman findSalesmanByLoginOrName(String loginOrName) throws AppDBExceptions;

    void addProductToDataBase(String name, double price);

    List<Bill> getAllBills();

    Bill findBillById(int id) throws AppDBExceptions;

    Salesman findSalesmanById(int id) throws AppDBExceptions;

    Product findProductById(int id) throws AppDBExceptions;

    Bill removeBill(int id) throws AppDBExceptions;

    Product removeProduct(int id) throws AppDBExceptions;

    Salesman removeSalesman(int id) throws AppDBExceptions;

    Bill update(Bill bill) throws AppDBExceptions;
}
