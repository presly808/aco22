package ua.artcode.market.controller;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalException;
import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.utils.TerminalStatisticsUtils;
import ua.artcode.market.utils.TerminalUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalController implements ITerminal {

    private final static int PERCENTAGE_OF_SALES = 5;
    private final static int PERCENTAGE_OF_SUBORDINATES_SALARY = 2;

    private AppDB appDB;

    private Salesman loggedSalesman;
    private boolean logged;

    private Bill currentBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public Salesman addSalesman(String fullName, String login, int pass) throws AppDBException, TerminalException {
        if (fullName.isEmpty() || login.isEmpty() || pass <= 0) {
            throw new TerminalException("can not add salesman, wrong data");

        } else {
            appDB.getSalesmans().add(new Salesman(fullName, login, pass, appDB.genId()));
            return appDB.findSalesmanByLoginOrName(login);
        }
    }

    @Override
    public void signIn(String loginOrName, int password) throws AppDBException {
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
    public void createBill() throws TerminalException {
        if (!logged) {
            throw new TerminalException("You are not logged in");

        } else {
            currentBill = new Bill(loggedSalesman, appDB.genId());
        }
    }

    @Override
    public void closeAndSaveBill() throws SaveBillException {
        if (currentBill.getProducts().size() == 0) {
            currentBill = null;
            throw new SaveBillException("you did not make a single sale, the bill wil be deleted");

        } else {
            currentBill.calculateAmountPrice();
            appDB.getBills().add(currentBill);
            currentBill.getSalesman().addSum(currentBill.getAmountPrice());
            currentBill = null;
        }
    }

    @Override
    public void addProductToBill(int id) throws AppDBException {
        if (logged) {
            currentBill.getProducts().add(appDB.findProductById(id));
            System.out.println("Added product: " + appDB.findProductById(id).toString());

        } else {
            System.out.println("please log in");
        }
    }

    @Override
    public Salesman getTopNofSalesMan() throws TerminalException {
        if (appDB.getBills().size() == 0) {
            throw new TerminalException("can not find a better salesman, count of bills = 0");

        } else if (appDB.getSalesmans().size() == 0) {
            throw new TerminalException("can not find a better salesman, count of salesman = 0");

        } else {
            return getSalesmans().stream().max(new Salesman.SumOfAllSalesComparator()).orElse(null);
        }
    }

    @Override
    public Statistics makeStatistics() throws TerminalException {
        if (appDB.getBills().size() == 0) {
            System.out.println("count of bills = 0");
            throw new TerminalException("can not make statistics, count of bills = 0");

        } else {

            //search average amount and sum of all sales

            double sumOfAllSalles = appDB.getBills().stream().mapToDouble(Bill::getAmountPrice).sum();

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
    public List<Bill> filterByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return getAllBills().stream()
                .filter(b -> b.getCloseTime().compareTo(startTime) < 0 && b.getCloseTime().compareTo(endTime) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public void addSubSalesman(Salesman chief, Salesman subordinate) throws WrongSubordinateException, AppDBException {
        if (!loggedSalesman.isDirector()) {
            System.out.println("Only a director can add a subordinate");

        }
        try {
            TerminalUtils.isNotBoss(loggedSalesman, chief, subordinate);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        appDB.findSalesmanById(chief.getId()).getSubordinates().add(subordinate);
    }

    @Override
    public double calculateSalesmanSalary(Salesman salesman) {
        if (salesman == null) {
            return 0;
        }

        double salaryForTheirSales = (salesman.getSumOfAllSales() / 100) * PERCENTAGE_OF_SALES;
        double subordinatesSalary = 0;

        if (salesman.getSubordinates().size() != 0) {
            subordinatesSalary = salesman.getSubordinates().stream()
                    .mapToDouble(s -> (calculateSalesmanSalary(s) / 100) * PERCENTAGE_OF_SUBORDINATES_SALARY)
                    .sum();
        }

        salesman.setSalary(salaryForTheirSales + subordinatesSalary);

        return salesman.getSalary();
    }

    @Override
    public double requiredAmountFromTheDepartment(Salesman chief) {
        if (chief == null) return 0;

        double allSalary = 0;
        if (chief.getSubordinates().size() != 0) {

            allSalary = chief.getSubordinates().stream()
                    .mapToDouble(this::requiredAmountFromTheDepartment)
                    .sum();
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

    @Override
    public Salesman getLoggedSalesman() {
        return loggedSalesman;
    }

    public int getCountOfBills() {
        return appDB.getBills().size();
    }

    public int getCountOfSalesman() {
        return appDB.getSalesmans().size();
    }

    public boolean getIsLogged() {
        return logged;
    }

    public Bill getCurrentBill() {
        return currentBill;
    }

    public List<Salesman> getSalesmans() {
        return appDB.getSalesmans();
    }
}
