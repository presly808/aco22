package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;
import ua.artcode.market.utils.TerminalStatisticsUtils;
import ua.artcode.market.utils.TerminalUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    private Salesman loggedSalesman;
    private boolean logged;

    private Bill currentBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public Salesman addSalesman(String fullName, String login, int pass) {
        if (fullName.isEmpty() || login.isEmpty() || pass <= 0) {
            System.out.println("wrong data");
            return null;

        } else {
            appDB.getSalesmans().add(new Salesman(fullName, login, pass, appDB.genId()));
            return appDB.findSalesmanByLoginOrName(login);
        }
    }

    @Override
    public void signIn(String loginOrName, int password) {
        if (logged) {
            System.out.println("You already logged");

        } else if (appDB.findSalesmanByLoginOrName(loginOrName) == null ||
                appDB.findSalesmanByLoginOrName(loginOrName).getPass() != password) {
            System.out.println("wrong data");

        } else {
            this.loggedSalesman = appDB.findSalesmanByLoginOrName(loginOrName);
            logged = true;
        }
    }

    @Override
    public void logOut() {
        logged = false;
        loggedSalesman = null;
    }

    @Override
    public void createBill() {
        if (!logged) {
            System.out.println("please log in");

        } else {
            currentBill = new Bill(loggedSalesman, appDB.genId());
        }
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {
        if (currentBill.getProducts().size() == 0) {
            System.out.println("you did not make a single sale, the bill wil be deleted");

        } else {
            currentBill.calculateAmountPrice();
            currentBill.setCloseTime(new Time(hours, minutes, seconds));
            appDB.getBills().add(currentBill);
            currentBill.getSalesman().addSum(currentBill.getAmountPrice());
            currentBill = null;
        }
    }

    @Override
    public void addProductToBill(int id) {
        if (logged && appDB.findProductById(id) != null) {
            currentBill.getProducts().add(appDB.findProductById(id));
            System.out.println("Added product: " + appDB.findProductById(id).toString());

        } else {
            System.out.println("please log in");
        }
    }

    @Override
    public Salesman getTopNofSalesMan() {
        if (appDB.getBills().size() == 0) {
            System.out.println("count of bills = 0");
            return null;

        } else {
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
            Bill billWithMaxAmount = TerminalStatisticsUtils.billWithMaxAmount(appDB.getBills());
            Bill billWithMinAmount = TerminalStatisticsUtils.billWithMinAmount(appDB.getBills());

            return new Statistics(billWithMaxAmount.getAmountPrice(),
                    billWithMaxAmount.getSalesman(),
                    billWithMinAmount.getAmountPrice(),
                    billWithMinAmount.getSalesman(),
                    averageAmountInOneChek,
                    sumOfAllSalles);
        }
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
    public void addSubSalesman(Salesman chief, Salesman subordinate) {
        if (!loggedSalesman.isDirector()) {
            System.out.println("Only a director can add a subordinate");

        } else if (!TerminalUtils.isBoss(loggedSalesman, chief, subordinate)) {
            appDB.findSalesmanById(chief.getId()).getSubordinates().add(subordinate);

        } else {
            System.out.println("You can not make a boss subordinate");
        }
    }

    @Override
    public double calculateSalesmanSalary(Salesman salesman) {
        if (salesman == null) {
            return 0;
        }

        double salaryForTheirSales = salesman.getSumOfAllSales() / 20;
        double subordinatesSalary = 0;

        if (salesman.getSubordinates().size() != 0) {
            for (int i = 0; i < salesman.getSubordinates().size(); i++) {
                subordinatesSalary += calculateSalesmanSalary(salesman.getSubordinates().get(i)) / 50;
            }
        }

        salesman.setSalary(salaryForTheirSales + subordinatesSalary);

        return salesman.getSalary();
    }

    @Override
    public double requiredAmountFromTheDepartment(Salesman chief) {
        if (chief == null) return 0;

        double allSalary = 0;
        if (chief.getSubordinates().size() != 0) {
            for (int i = 0; i < chief.getSubordinates().size(); i++) {
                allSalary += requiredAmountFromTheDepartment(chief.getSubordinates().get(i));
            }
        }
        return allSalary + chief.getSalary();
    }

    @Override
    public List<Bill> getAllBills() {

        return appDB.getBills();
    }

    @Override
    public AppDB getAppDB() {
        return appDB;
    }

    public int getCountOfBills() { return appDB.getBills().size(); }

    public int getCountOfSalesman() { return appDB.getSalesmans().size(); }

    public Salesman getLoggedSalesman() { return loggedSalesman; }

    public boolean getIsLogged() { return logged; }

    public Bill getCurrentBill() { return currentBill; }

    public List<Salesman> getSalesmans() {
        return appDB.getSalesmans();
    }
}
