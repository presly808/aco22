package ua.artcode.market.interf;

import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Salesman;
import ua.artcode.market.model.Time;

import java.util.Comparator;

public interface ITerminal {

    int MAX_COUNT_OF_BILLS = 10;
    int MAX_COUNT_OF_SALESMANS = 10;

    void addSalesman(String fullName, String login, int pass);

    void signIn(boolean isLogin, String loginOrName, int password);

    void createBill(int id);

    void closeAndSaveBill(int hours, int minutes, int seconds);

    void addProduct(String name, int id, double price);

    Bill findBillById(int id);

    Salesman findSalesman(String loginOrName, boolean isLogin);

    Object getTopNofSalesMan();

    void doSomeStatisticStuff();

    Bill[] filter (Bill[] bills, Time startTime, Time endTime, Comparator<Bill> comparator);
}
