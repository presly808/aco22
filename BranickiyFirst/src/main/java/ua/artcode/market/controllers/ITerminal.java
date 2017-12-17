package src.main.java.ua.artcode.market.controllers;

import src.main.java.ua.artcode.market.appDB.IAppDB;
import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.models.Statistics;
import java.util.List;

public interface ITerminal {

    Salesman addSalesman(String fullName, String login, String password);
    void signIn (String loginOrName, String password);
    void logOut();
    void createBill();
    void closeAndSaveBill();
    void addProductToBill(int id);

    Statistics makeStatistic();

    List<Bill> getAllBills();

    IAppDB getIAppDB();


}
