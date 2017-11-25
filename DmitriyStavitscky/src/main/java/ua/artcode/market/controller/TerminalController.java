package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.interf.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;
import ua.artcode.market.utils.TerminalUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    private Salesman loggedSalesman;
    private boolean logged;

    private Bill newBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public void addSalesman(String fullName, String login, int pass) {
        appDB.getSalesmans().add(new Salesman(fullName, login, pass, appDB.genId()));
    }

    @Override
    public void signIn(boolean isLogin, String loginOrName, int password) {
        this.loggedSalesman = appDB.findSalesman(loginOrName, isLogin);
        logged = true;
    }

    @Override
    public void logOut() {
        logged = false;
        loggedSalesman = null;
    }

    @Override
    public void createBill() {
        newBill = new Bill(loggedSalesman, appDB.genId());
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {
        newBill.calculateAmountPrice();
        newBill.setCloseTime(new Time(hours, minutes, seconds));
        appDB.getBills().add(newBill);
        newBill.getSalesman().addSum(newBill.getAmountPrice());
        newBill = null;
    }

    @Override
    public void addProductToBill(int id) {
        if (logged && appDB.findProductById(id) != null) {
            newBill.getProducts().add(appDB.findProductById(id));
            System.out.println("Added product: " + appDB.findProductById(id).toString());

        } else {
            System.out.println("please log in");
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
    public Statistics makeStatistics() {

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

    @Override
    public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
        List<Bill> billsFilt = new ArrayList<>();

        for (Bill bill : bills) {

            if (bill.getCloseTime().compareTo(startTime) > 0 &&
                    bill.getCloseTime().compareTo(endTime) < 0) {
                billsFilt.add(bill);
            }
        }

        appDB.getBills().sort(comparator);
        return billsFilt;
    }

    @Override
    public List<Bill> getAllBills() {

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

    public boolean getIsLogged() {
        return logged;
    }

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
