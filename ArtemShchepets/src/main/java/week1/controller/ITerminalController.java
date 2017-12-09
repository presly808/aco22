package week1.controller;

import week1.exceptions.*;
import week1.model.Bill;
import week1.model.Product;
import week1.model.SalesStatistic;
import week1.model.Seller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface ITerminalController {

    boolean login(String login, String password) throws UnableToLogInException;

    Bill createBill();

    Bill addProduct(int billId, Product product) throws UnableToFindABillException, InvalidBillIdException;

    int getCurrentSellerId();

    void setCurrentSeller(int currentSellerId);

    List<Bill> getAllBills();

    Bill closeBill(int billId) throws UnableToFindABillException;

    Bill findBillById(int billId) throws InvalidBillIdException;

    Seller findSellerByLoginOrFullName(String loginOrFullName);

    Seller getTopOfSalesman() throws UnableToGetTopSellersException;

    SalesStatistic doSomeStatisticStuff() throws UnableToDoStatisticException, UnableToGetTopSellersException;

    List<Bill> filter(LocalDateTime startTime, LocalDateTime endTime, Comparator<Bill> comparator) throws UnableToFilterException;

    void turnOnTerminalLogger();

    void turnOnDatabaseLogger();
}
