package src.main.java.ua.artcode.market.controllers;

import main.java.ua.artcode.market.Utils.TerminalUtils;
import main.java.ua.artcode.market.appDB.IAppDB;
import main.java.ua.artcode.market.models.Bill;
import main.java.ua.artcode.market.models.Salesman;
import main.java.ua.artcode.market.models.Statistics;
import src.main.java.ua.artcode.market.controllers.ITerminal;

import java.sql.Time;
import java.util.List;

public class ITerminalControllerImpl implements ITerminal {

    private IAppDB iAppDB;
    private Bill newBill;
    private Salesman loggedSalesman;
    private boolean logged;

    public ITerminalControllerImpl(IAppDB IappDB){
        this.iAppDB = IappDB;
    }


    @Override
    public void addSalesman(String fullName, String login, String password) {
        IAppDB.getSalesmans().add(new Salesman(fullName, login, password, iAppDB.genId()));
    }

    @Override
    public void signIn(String login, String password) {

        this.loggedSalesman = IAppDB.findSalesman(loginOrName);
        logged = true;

    }

    @Override
    public void logOut() {

        logged = false;
        loggedSalesman = null;

    }

    @Override
    public void creareBill() {

        newBill = new Bill(loggedSalesman,iAppDB.genID());

    }

    @Override
    public void closeAndSaveBill() {

        newBill.calculateAmountPrice();
        newBill.setCloseTime(new Time());
        IAppDB.getBills().add(newBill);
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
                amountOfAllSales);

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

    public Salesman getLoggedSalesman() {
        return loggedSalesman;
    }

    public boolean isLogged() {
        return logged;
    }
}
