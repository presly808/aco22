package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.SalesStatistic;
import week1.model.Seller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface ITerminalController {

    boolean login(String login, String password);

    Bill createBill();

    Bill addProduct(int billId, Product product);

    int getCurrentSellerId();

    void setCurrentSeller(int currentSellerId);

    List<Bill> getAllBills();

    Bill closeBill(int billId);

    Bill findBillById(int billId);

    Seller findSellerByLoginOrFullName(String loginOrFullName);

    Seller getTopOfSalesman();

    SalesStatistic doSomeStatisticStuff();

    List<Bill> filter(LocalDateTime startTime, LocalDateTime endTime, Comparator<Bill> comparator);

    void turnOnTerminalLogger();

    void turnOnDatabaseLogger();
}
