package ua.artcode.market.appdb;

import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.List;

public interface IAppDB {

    int genId();

    void addActionToHistory(String message);

    Salesman findSalesmanByLoginOrName(String loginOrName) throws AppDBException;

    void addProductToDataBase(String name, double price);

    List<Bill> getAllBills();

    Bill findBillById(int id) throws AppDBException;

    Salesman findSalesmanById(int id) throws AppDBException;

    Product findProductById(int id) throws AppDBException;

    Bill removeBill(int id) throws AppDBException;

    Product removeProduct(int id) throws AppDBException;

    Salesman removeSalesman(int id) throws AppDBException;

    Bill update(Bill bill) throws AppDBException;
}
