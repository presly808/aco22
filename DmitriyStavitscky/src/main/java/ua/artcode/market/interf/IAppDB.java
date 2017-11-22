package ua.artcode.market.interf;

import ua.artcode.market.models.*;

import java.util.List;

public interface IAppDB {

    int genId();

    void addActionToHistory(String message);

    Salesman findSalesman(String loginOrName, boolean isLogin);

    void addProductToDataBase(String name, double price);

    List<Bill> getAllBills();

    Bill findBillById(int id);

    Salesman findSalesmanById(int id);

    Product findProductById(int id);

    Bill removeBill (int id);

    Product removeProduct (int id);

    Salesman removeSalesman (int id);

    Bill update(Bill bill);


    /*getAll()
    findById(id)
    remove(id)
    update(object, id)*/
}
