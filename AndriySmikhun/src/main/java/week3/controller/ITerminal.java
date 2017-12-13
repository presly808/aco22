package week3.controller;

import week3.model.Product;
import week3.model.Bill;
import week3.model.Salesman;

public interface ITerminal {


    boolean login(String login, String password);
    boolean logOut();

    boolean addProduct(int id,Product product);
    boolean deleteProduct(int idBill, Product product);

    boolean openBill(Bill bill);
    boolean closeBill(Bill bill);
    Bill[] filterBill();


    boolean createSalesMan(String login, String password, String fullName);
    void getTopSalesman();


}
