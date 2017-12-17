package ua.artcode.market.interfaces;

import ua.artcode.market.databases.AppDB;
import ua.artcode.market.exceptions.AppException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface ITerminal {

    AppDB getAppDB();

    Salesman logIn(String login, String password) throws AppException;

    void logOut(Salesman salesman);

    Bill createBill(Salesman salesman) throws AppException;

    Bill addProduct(int billId, Product product) throws AppException;

    Bill closeAndSaveBill(int billId) throws AppException;

    List<Salesman> getTopNOfSalesMen(int n) throws AppException;

    Statistic doSomeStatisticStuff();

    List<Bill> filter(List<Salesman> salesmen, List<Product> products,
                      LocalDateTime startTime, LocalDateTime endTime,
                      Comparator<Bill> comparator);
}
