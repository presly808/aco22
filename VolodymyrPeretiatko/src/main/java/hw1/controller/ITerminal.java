package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;

import java.util.*;

public interface ITerminal {

    List<Salesman> getSalesmen();
    List<Product> getProducts();
    boolean login(String name, String password);
    boolean addSalesman(Salesman salesman);
    Bill createBill();
    boolean closeAndSaveBill(Bill bill);
    List<Bill> getBills();
    boolean addProduct(Product p);
    Bill findBillById(int id);
    Salesman getTopNofSalesMan();
    Map<Salesman, Double> getSalesAmountBySalesman();
    ArrayList<Bill> filter(List<Salesman> salesmen, List<Product> products,
                           Date startDate, Date endDate, Comparator<Bill> comparator);

}
