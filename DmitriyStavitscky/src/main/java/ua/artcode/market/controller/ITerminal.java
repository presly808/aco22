package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalException;
import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public interface ITerminal {

    Salesman addSalesman(String fullName, String login, int pass) throws AppDBException, TerminalException;

    void signIn(String loginOrName, int password) throws AppDBException;

    void logOut();

    void createBill() throws TerminalException;

    void closeAndSaveBill() throws SaveBillException;

    void addProductToBill(int id) throws AppDBException;

    Salesman getTopNofSalesMan() throws TerminalException;

    Statistics makeStatistics() throws TerminalException;

    List<Bill> filterByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<Bill> getAllBills();

    AppDB getAppDB();

    void addSubSalesman(Salesman chief, Salesman subordinate) throws WrongSubordinateException, AppDBException;

    double calculateSalesmanSalary(Salesman chief);

    double requiredAmountFromTheDepartment(Salesman chief);

    Salesman getLoggedSalesman();
}
