package main.java.ua.artcode.market.controllers;

import main.java.ua.artcode.market.appDB.IAppDB;
import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.SalesMan;
import main.java.ua.artcode.market.models.Statistics;

import java.sql.Time;
import java.util.List;

public class ITerminalControllerImpl implements ITerminal {

    private IAppDB iAppDB;
    private Bill newBill;
    private  SalesMan loggedSalesMan;
    private boolean logged;

    public ITerminalControllerImpl(IAppDB IappDB){
        this.IappDB = IappDB;
    }


    @Override
    public void addSalesman(String fullName, String login, String password) {
        IappDB.getSalesMans().add(new SalesMan(fullName, login, password, IappDB.genId()));
    }

    @Override
    public void signIn(String login, String password) {

        this.loggedSalesMan = IappDB.findSalesman(loginOrName);
        logged = true;

    }

    @Override
    public void logOut() {

        logged = false;
        loggedSalesMan = null;

    }

    @Override
    public void creareBill() {

        newBill = new Bill(loggedSalesMan,iAppDB.genID());

    }

    @Override
    public void closeAndSaveBill() {

        newBill.calculateAmountPrice();
        newBill.setCloseTime(new Time());
        IappDB.getBills().add(newBill);
        newBill.getSalesMan().addSum(newBill.getAmountPrice());
        newBill = null;

    }

    @Override
    public void addProdutToBill() {

        if (logged && iAppDB.findProductById(id) != null) {
            newBill.getProducts().add(iAppDB.findProductById(id));
            System.out.println("Added product: " + iAppDB.findProductById(id).toString());

        } else {
            System.out.println("please log in");
        }

    }

    @Override
    public Statistics makeStatistic() {

        //search average amount and sum of all sales
        double sumOfAllSalles = iAppDB.getBills().get(0).getAmountPrice();

        for (int i = 1; i < iAppDB.getBills().size(); i++) {
            sumOfAllSalles += iAppDB.getBills().get(i).getAmountPrice();
        }

        double averageAmountInOneChek = sumOfAllSalles / iAppDB.getBills().size();

        // search bil with max and min amount
        Bill billWithMaxAmount = TerminalUtils.billWithMaxAmount(iAppDB.getBills());
        Bill billWithMinAmount = TerminalUtils.billWithMinAmount(iAppDB.getBills());

        return new Statistics(billWithMaxAmount.getAmountPrice(),
                billWithMaxAmount.getSalesMan(),
                billWithMinAmount.getAmountPrice(),
                billWithMinAmount.getSalesMan(),
                averageAmountInOneChek,
                sumOfAllSalles);

    }

    @Override
    public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime) {
        return null;
    }

    @Override
    public List<Bill> filterByTime(Time startTime, Time endTime) {
        return null;
    }

    @Override
    public List<Bill> getAllBills() {
        return null;
    }

    @Override
    public IAppDB getIAppDB() {
        return null;
    }

    public IAppDB getiAppDB() {
        return iAppDB;
    }

    public Bill getNewBill() {
        return newBill;
    }

    public SalesMan getLoggedSalesMan() {
        return loggedSalesMan;
    }

    public boolean isLogged() {
        return logged;
    }
}
