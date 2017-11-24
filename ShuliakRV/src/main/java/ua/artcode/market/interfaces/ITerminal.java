package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;

import java.util.Comparator;
import java.util.Date;

public interface ITerminal {

    public Salesman login(String login, String password);

    public boolean createBill(Salesman s);

    public boolean addProduct(Product p);

    public Bill closeAndSaveBill();

    public Bill findBillById(int id);

    public Salesman[] findSalesmanByLoginOrFullname(String loginOrFullname);

    public Salesman[] getTopNofSalesMan(int n);

    public Statistic doSomeStatisticStuff();

    public double getMax();

    public double getMin();

    public double getAverage();

    public int countSoldProducts();

    public Bill calculateAmountPrice(Bill bill);

    Bill[] filter(Salesman[] sales, Product[] products, Date startTime, Date endTime, Comparator<Bill> comparator);
}
