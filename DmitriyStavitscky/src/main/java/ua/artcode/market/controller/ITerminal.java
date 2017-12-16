package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.exceptions.AppDBExceptions;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalExceptions;
import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public interface ITerminal {

    Salesman addSalesman(String fullName, String login, int pass) throws AppDBExceptions;

    void signIn(String loginOrName, int password) throws AppDBExceptions;

    void logOut();

    void createBill() throws TerminalExceptions;

    void closeAndSaveBill() throws SaveBillException;

    void addProductToBill(int id) throws AppDBExceptions;

    Salesman getTopNofSalesMan();

    Statistics makeStatistics();

    List<Bill> filterByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<Bill> getAllBills();

    AppDB getAppDB();

    void addSubSalesman(Salesman chief, Salesman subordinate) throws WrongSubordinateException, AppDBExceptions;

    double calculateSalesmanSalary(Salesman chief);

    double requiredAmountFromTheDepartment(Salesman chief);

    Salesman getLoggedSalesman();
}
