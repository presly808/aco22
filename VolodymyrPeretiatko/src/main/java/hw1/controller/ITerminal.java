package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITerminal {

    boolean addSalesman(Salesman salesman);
    Bill createBill(int id, Salesman salesman);
    boolean closeAndSaveBill(Bill bill);
    ArrayList<Bill> getBills();
    boolean addProduct(Product p);
    Bill findBillById(int id);
    Salesman getTopNofSalesMan();
    HashMap<Salesman, Double> getSalesAmountBySalesman();

}
