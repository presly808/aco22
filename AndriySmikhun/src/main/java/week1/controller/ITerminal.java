package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

public interface ITerminal {

    boolean login(String login, String password); //rebuild
    Bill createBill(int id, Salesman salesman); //rebild
    void addProduct(Bill bill, Product product);
    Bill getBillById(int id);
    Salesman getSalemanByName(String salesman);
    Salesman getTopNofSalesMan();





}
