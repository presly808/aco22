package week3.controller;

import week1.model.Product;
import week3.model.Bill;
import week3.model.Salesman;

public interface ITerminal {


    boolean login(String login, String password);
    //boolean logOut(Salesman salesman);

    boolean addProduct(int id,Product product);
    boolean deleteProduct(int idBill, Product product);

    Bill maxBill();
    Bill minBill();
    Bill[] filterBill();
    boolean closeBill(Bill bill);

    boolean createSalesMan();
    Salesman getTopSalesman();


}
