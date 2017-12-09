package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;

import java.util.Comparator;
import java.util.List;

public interface ITerminal {

    Salesman addSalesman(String fullName, String login, int pass);

    void signIn(String loginOrName, int password);

    void logOut();

    void createBill();

    void closeAndSaveBill(int hours, int minutes, int seconds);

    void addProductToBill(int id);

    Salesman getTopNofSalesMan();

    Statistics makeStatistics();

    List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator);

    List<Bill> getAllBills();

    AppDB getAppDB();

    void addSubSalesman(Salesman chief, Salesman subordinate);

    double calculateSalesmanSalary(Salesman chief);

    double requiredAmountFromTheDepartment(Salesman chief);
}
