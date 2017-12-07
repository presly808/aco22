package src.main.java.ua.artcode.market.controllers;

import main.java.ua.artcode.market.appDB.IAppDB;
import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.Statistics;

import java.sql.Time;
import java.util.List;

public interface ITerminal {

    void signIn (String login, String password);
    void logOut();
    void creareBill();
    void closeAndSaveBill();
    void addProdutToBill();

    Statistics makeStatistic();

    List <Bill> filterByTime (List<Bill> bills, Time startTime, Time endTime);

    List<Bill> getAllBills();

    IAppDB getIAppDB();

}
