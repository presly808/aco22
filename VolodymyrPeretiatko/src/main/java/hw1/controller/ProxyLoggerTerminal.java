package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;
import hw1.utils.ILogger;
import hw1.utils.LogSOut;

import java.util.List;
import java.util.Map;

public class ProxyLoggerTerminal implements ITerminal {

    private static ProxyLoggerTerminal uniqueInstance;

    private ITerminal realTerminal;
    private ILogger logger = LogSOut.getInstance();

    public static synchronized ProxyLoggerTerminal getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new ProxyLoggerTerminal();
        }
        return uniqueInstance;
    }

    private ProxyLoggerTerminal(){}

    @Override
    public boolean addSalesman(Salesman salesman) {
        return false;
    }

    @Override
    public Bill createBill(Salesman salesman) {
        return null;
    }

    @Override
    public boolean closeAndSaveBill(Bill bill) {
        return false;
    }

    @Override
    public List<Bill> getBills() {
        return null;
    }

    @Override
    public boolean addProduct(Product p) {

        return false;
    }

    @Override
    public Bill findBillById(int id) {
        return null;
    }

    @Override
    public Salesman getTopNofSalesMan() {
        return null;
    }

    @Override
    public Map<Salesman, Double> getSalesAmountBySalesman() {
        return null;
    }
}
