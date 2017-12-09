package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;
import hw1.utils.ILogger;
import hw1.utils.LogerPrntln;

import java.util.*;

public class ProxyLoggerTerminal implements ITerminal {

    private ITerminal realTerminal;
    private ILogger logger = LogerPrntln.getInstance();

    public ProxyLoggerTerminal(){
        realTerminal = new Terminal();
    }

    @Override
    public List<Salesman> getSalesmen() {
        return realTerminal.getSalesmen();
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen, List<Product> products, Date startDate, Date endDate, Comparator<Bill> comparator) {
        return realTerminal.filter(salesmen, products, startDate, endDate, comparator);
    }

    @Override
    public List<Product> getProducts() {
        return realTerminal.getProducts();
    }

    @Override
    public boolean login(String name, String password) {
        logger.info("Try to loggin: " + name);
        boolean result = realTerminal.login(name, password);
        if (result){
            logger.info("User logged: " + name);
        } else {
            logger.error("Loggin faild: " + name);
        }
        return result;
    }

    @Override
    public boolean addSalesman(Salesman salesman) {
        return realTerminal.addSalesman(salesman);
    }

    @Override
    public Bill createBill() {
        return realTerminal.createBill();
    }

    @Override
    public boolean closeAndSaveBill(Bill bill) {
        return realTerminal.closeAndSaveBill(bill);
    }

    @Override
    public List<Bill> getBills() {
        return realTerminal.getBills();
    }

    @Override
    public boolean addProduct(Product p) {
        return realTerminal.addProduct(p);
    }

    @Override
    public Bill findBillById(int id) {
        return realTerminal.findBillById(id);
    }

    @Override
    public Salesman getTopNofSalesMan() {
        return realTerminal.getTopNofSalesMan();
    }

    @Override
    public Double getSalesmanSallary(Salesman salesman, Date startDate, Date endDate) {
        return realTerminal.getSalesmanSallary(salesman, startDate, endDate);
    }

    @Override
    public Map<Salesman, Double> getSalesAmountBySalesman() {
        return realTerminal.getSalesAmountBySalesman();
    }
}
