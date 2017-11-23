package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.List;

/**
 * Created by ENIAC on 19.11.2017.
 */
public interface ITerminalController {

    void login(String login, String pass);

    Bill createBill();

    Bill findBillById(int billId);

    Salesman findSalesmanByLogin(String Login);


    Bill addProduct(int billId, Product product);

    List<Bill> getAllBills();

    Bill closeBill(int id);

    List<Product> getAllProducts();


}

