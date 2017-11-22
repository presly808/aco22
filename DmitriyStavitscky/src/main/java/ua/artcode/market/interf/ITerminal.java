package ua.artcode.market.interf;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;

import java.util.Comparator;
import java.util.List;

public interface ITerminal {

    void addSalesman(String fullName, String login, int pass, int id);

    void signIn(boolean isLogin, String loginOrName, int password);

    void createBill();

    void closeAndSaveBill(int hours, int minutes, int seconds);

    void addProductToBill(int id);

    Object getTopNofSalesMan();

    Statistics makeStatistics();

    List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator);

    void rememberActionAndPrint();

    List <Bill> getAllBills();
}
