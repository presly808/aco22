package ua.artcode.market.controller;

import ua.artcode.market.interf.ITerminal;
import ua.artcode.market.models.*;
import ua.artcode.market.utils.TerminalUtils;
import ua.artcode.market.appdb.AppDB;

import java.util.*;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    private Salesman loggedSalesman;
    private boolean logged;

    private Bill newBill;

    public TerminalController(AppDB appDB) { this.appDB = appDB; }

    @Override
    public void addSalesman(String fullName, String login, int pass, int id) {

        if (fullName.isEmpty() || login.isEmpty() || pass <= 0 || id <= 0) {
            System.out.println("wrong data");
        } else {
            appDB.getSalesmans().add(new Salesman(fullName, login, pass, id));
        }
    }

    @Override
    public void signIn(boolean isLogin, String loginOrName, int password) {
        if (appDB.findSalesman(loginOrName, isLogin) == null ||
                appDB.findSalesman(loginOrName, isLogin).getPass() != password) {
            System.out.println("wrong data");

        } else {
            this.loggedSalesman = appDB.findSalesman(loginOrName, isLogin);
            logged = true;
        }
    }

    public void logOut() {
        logged = false;
        loggedSalesman = null;
    }

    @Override
    public void createBill() {

        if (!logged) {
            System.out.println("please log in");

        } else {
            newBill = new Bill(loggedSalesman, appDB.genId());
        }
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {

        if (newBill.getProducts().size() == 0) {
            System.out.println("you did not make a single sale, the bill wil be deleted");

        } else {
            newBill.calculateAmountPrice();
            newBill.setTime(new Time(hours, minutes, seconds));
            appDB.getBills().add(newBill);

        }

        newBill = null;
    }

    @Override
    public void addProductToBill(int id) {
        if (logged && appDB.findProductById(id) != null) {
            newBill.getProducts().add(appDB.findProductById(id));

        } else {
            System.out.println("please log in");
        }
    }

    @Override
    public Statistics makeStatistics() {
        if (appDB.getBills().size() == 0) {
            System.out.println("count of bills = 0");
            return null;
        } else {

            //search average amount and sum of all sales
            double sumOfAllSalles = appDB.getBills().get(0).getAmountPrice();

            for (int i = 1; i < appDB.getBills().size(); i++) {
                sumOfAllSalles += appDB.getBills().get(i).getAmountPrice();
            }

            double averageAmountInOneChek = sumOfAllSalles / appDB.getBills().size();

            // search bil with max and min amount
            Bill billWithMaxAmount = TerminalUtils.billWithMaxAmount(appDB.getBills());
            Bill billWithMinAmount = TerminalUtils.billWithMinAmount(appDB.getBills());

            return new Statistics(billWithMaxAmount.getAmountPrice(),
                    billWithMaxAmount.getSalesman(),
                    billWithMinAmount.getAmountPrice(),
                    billWithMinAmount.getSalesman(),
                    averageAmountInOneChek,
                    sumOfAllSalles);
        }
    }

    @Override
    public Salesman getTopNofSalesMan() {
        int topSalemanId = 0;

        if (appDB.getSalesmans().size() == 0 || appDB.getBills().size() == 0) return null;

        double max = appDB.getSalesmans().get(0).getSumOfAllSales();
        for (int i = 0; i < appDB.getSalesmans().size(); i++) {

            if (appDB.getSalesmans().get(i).getSumOfAllSales() > max) {
                max = appDB.getSalesmans().get(i).getSumOfAllSales();
                topSalemanId = i;
            }
        }

        return appDB.getSalesmans().get(topSalemanId);
    }

    @Override
    public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
        List<Bill> billsFilt = new ArrayList<>();

        for (Bill bill : bills) {

            if (bill.getTime().compareTo(startTime) > 0 &&
                    bill.getTime().compareTo(endTime) < 0) {
                billsFilt.add(bill);
            }
        }

        appDB.getBills().sort(comparator);
        return billsFilt;
    }

    public List<Bill> getBills() {
        return appDB.getBills();
    }

    public int getCountOfBills() {
        return appDB.getBills().size();
    }

    public int getCountOfSalesman() {
        return appDB.getSalesmans().size();
    }

    public Salesman getLoggedSalesman() {
        return loggedSalesman;
    }

    public boolean getIsLogged() { return logged; }

    public AppDB getAppDB() {
        return appDB;
    }

    public Bill getNewBill() {
        return newBill;
    }

    public List<Salesman> getSalesmans() {
        return appDB.getSalesmans();
    }
}
