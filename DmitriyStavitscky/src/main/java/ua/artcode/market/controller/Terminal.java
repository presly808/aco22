package ua.artcode.market.controller;

import ua.artcode.market.interf.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Time;

import java.util.Arrays;
import java.util.Comparator;

public class Terminal implements ITerminal {

    private Bill[] bills = new Bill[MAX_COUNT_OF_BILLS];
    private int countOfBills;

    private Salesman[] salesmans = new Salesman[MAX_COUNT_OF_SALESMANS];
    private int countOfSalesman;

    private Salesman loggedSalesman;
    private boolean logged;

    @Override
    public void addSalesman(String fullName, String login, int pass) {

        if (countOfSalesman == MAX_COUNT_OF_SALESMANS) {
            System.out.println("Sorry, maximum number of checks");
        } else if (fullName.isEmpty() || login.isEmpty() || pass <= 0) {
            System.out.println("wrong data");
        } else {
            salesmans[countOfSalesman++] = new Salesman(fullName, login, pass);
        }
    }

    @Override
    public void signIn(boolean isLogin, String loginOrName, int password) {
        if (findSalesman(loginOrName, isLogin) == null ||
                findSalesman(loginOrName, isLogin).getPass() != password) {
            System.out.println("wrong data");
        } else {
            this.loggedSalesman = findSalesman(loginOrName, isLogin);
            logged = true;
        }
    }

    public void logOut() {
        setLogged(false);
    }


    @Override
    public void createBill(int id) {

        if (!logged) {
            System.out.println("please log in");

        } else if (countOfBills == MAX_COUNT_OF_BILLS) {
            System.out.println("Sorry, maximum number of checks");

        } else {
            bills[countOfBills] = new Bill(loggedSalesman, id);
        }
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {

        if (!logged) {
            System.out.println("please log in");
        } else if (bills[countOfBills].getProducts() == null) {
            System.out.println("you did not make a single sale");
        } else {
            bills[countOfBills++].closeBill(hours, minutes, seconds);
        }
    }

    @Override
    public void addProduct(String name, int id, double price) {
        if (logged) {
            bills[countOfBills].addProduct(name, id, price);
        } else {
            System.out.println("please log in");
        }
    }

    @Override
    public Bill findBillById(int id) {
        if (id == 0) return null;

        for (int i = 0; i < countOfBills; i++) {
            if (bills[i].getId() == id) {
                return bills[i];
            }
        }

        return null;
    }

    @Override
    public Salesman findSalesman(String loginOrName, boolean isLogin) {
        if (loginOrName == null || loginOrName.isEmpty()) return null;

        if (isLogin) {
            for (int i = 0; i < countOfSalesman; i++) {
                if (salesmans[i].getLogin().equals(loginOrName)) {
                    return salesmans[i];
                }
            }
        } else {
            for (int i = 0; i < countOfSalesman; i++) {
                if (salesmans[i].getFullName().equals(loginOrName)) {
                    return salesmans[i];
                }
            }

        }

        return null;
    }

    @Override
    public void doSomeStatisticStuff() {
        if (countOfBills == 0) {
            System.out.println("count of bills = 0");
        } else {

            double maxAmount = bills[0].getAmountPrice();
            int billIdWithMaxAmount = 0;

            double minAmount = bills[0].getAmountPrice();
            int billIdWithMinAmount = 0;

            double averageAmountInOneChek = bills[0].getAmountPrice();
            double sumOfAllSalles = bills[0].getAmountPrice();

            for (int i = 1; i < countOfBills; i++) {
                if (bills[i].getAmountPrice() > maxAmount) {
                    maxAmount = bills[i].getAmountPrice();
                    billIdWithMaxAmount = i;
                }

                if (bills[i].getAmountPrice() < minAmount) {
                    minAmount = bills[i].getAmountPrice();
                    billIdWithMinAmount = i;
                }

                averageAmountInOneChek += bills[i].getAmountPrice();
                sumOfAllSalles += bills[i].getAmountPrice();
            }

            averageAmountInOneChek = averageAmountInOneChek / countOfBills;

            System.out.printf("Max amount: %.2f, saleman: %s \n" +
                            "Min amount: %.2f, saleman: %s \n" +
                            "Average amount in one chek: %.2f \n" +
                            "Sum of all salles: %.2f",
                    maxAmount, salesmans[billIdWithMaxAmount].getFullName(),
                    minAmount, salesmans[billIdWithMinAmount].getFullName(),
                    averageAmountInOneChek,
                    sumOfAllSalles);
        }
    }

    @Override
    public Salesman getTopNofSalesMan() {
        int topSalemanId = 0;

        if (countOfSalesman == 0) return null;

        double max = salesmans[0].getSumOfAllSales();
        for (int i = 1; i < countOfSalesman; i++) {
            if (salesmans[i].getSumOfAllSales() > max) {
                max = salesmans[i].getSumOfAllSales();
                topSalemanId = i;
            }
        }

        return salesmans[topSalemanId];
    }

    @Override
    public Bill[] filter(Bill[] bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
        Bill[] billsFilt = new Bill[bills.length];
        int countFiltBills = 0;

        for (int i = 0; i < countOfBills; i++) {
            if (bills[i].getTime().compareTo(startTime) > 0 &&
                    bills[i].getTime().compareTo(endTime) < 0) {
                billsFilt[countFiltBills++] = bills[i];
            }
        }

        Bill[] billsRes = Arrays.copyOf(billsFilt, countFiltBills);
        for (Bill billsRe : billsRes) {
            billsRe.setProducts(Arrays.copyOf(billsRe.getProducts(),
                    billsRe.getProductsCount()));
        }

        Arrays.sort(billsRes, comparator);
        return billsRes;
    }

    public Bill[] getBills() {
        return bills;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public int getCountOfBills() {
        return countOfBills;
    }

    public void setCountOfBills(int countOfBills) {
        this.countOfBills = countOfBills;
    }

    public Salesman[] getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(Salesman[] salesmans) {
        this.salesmans = salesmans;
    }

    public int getCountOfSalesman() {
        return countOfSalesman;
    }

    public void setCountOfSalesman(int countOfSalesman) {
        this.countOfSalesman = countOfSalesman;
    }

    public Salesman getLoggedSalesman() {
        return loggedSalesman;
    }

    public void setLoggedSalesman(Salesman loggedSalesman) {
        this.loggedSalesman = loggedSalesman;
    }

    public boolean getIsLogged() {
        return logged;
    }

    private void setLogged(boolean logged) {
        this.logged = logged;
    }
}
