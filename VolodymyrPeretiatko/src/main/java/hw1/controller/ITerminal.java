package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;

import java.util.List;
import java.util.Map;

public interface ITerminal {

    boolean addSalesman(Salesman salesman);
    Bill createBill(Salesman salesman);
    boolean closeAndSaveBill(Bill bill);
    List<Bill> getBills();
    boolean addProduct(Product p);
    Bill findBillById(int id);
    Salesman getTopNofSalesMan();
    Map<Salesman, Double> getSalesAmountBySalesman();
}
