package ua.artcode.market.controller;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    private int currentBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }


    public Salesman signIn(String login, String password) {

        if (appDB.getSales() == null) return null;
        else {

            for (int i = 0; i < appDB.getSales().length; i++) {
                if ((appDB.getSales()[i] != null) &&
                        (appDB.getSales()[i].getLogin().equals(login)) &&
                        (appDB.getSales()[i].getPassword().equals(password))) {
                    appDB.getSales()[i].setLogged(true);
                    return appDB.getSales()[i];
                }
            }

            return null;
        }
    }

    public boolean createBill(Salesman s) {

        currentBill = appDB.getCountBill();

        if ((s != null) && (s.isLogged()) &&
                (currentBill < appDB.getBills().length)) {
            appDB.getBills()[currentBill] = new Bill(s);

            return true;
        }

        return false;
    }

    public boolean addProduct(Product p) {

        return appDB.getBills()[currentBill].addProduct(p);

    }

    public Bill closeAndSaveBill() {

        if (appDB.getBills()[currentBill].closeBill()) {
            appDB.incCountBill();
            return appDB.getBills()[currentBill];
        } else {
            appDB.getBills()[currentBill] = null;
        }

        return null;
    }


    public Salesman findSalesmanByLoginAndPassword(String login, String password) {

        Salesman[] s = new Salesman[appDB.getSales().length];
        int index = 0;

        if ((loginOrFullname == null || loginOrFullname.isEmpty()) ||
                (appDB.getSales().length == 0)) return null;

        for (int i = 0; i < appDB.getSales().length; i++) {
            if (appDB.getSales()[i].getLogin().equals(loginOrFullname) ||
                    appDB.getSales()[i].getFullname().equals(loginOrFullname))
                s[index++] = appDB.getSales()[i];
        }

        if (index > 0) return s;

        return null;
    }

    public Salesman[] getTopNofSalesMan(int n) {

        if ((n > 0) && (appDB.getSales() != null) &&
                (n <= appDB.getSales().length)) {

            Salesman[] s = new Salesman[n];
            int[] salesProducts = new int[appDB.getSales().length];

            for (int i = 0; i < appDB.getCountBill(); i++) {
                if (appDB.getBills()[i] != null) {
                    salesProducts[getIndexSales
                            (appDB.getBills()[i].getSalesMan())] +=
                            appDB.getBills()[i].getNumProd();
                }
            }

            for (int i = 0; i < n; i++) {

                int max = 0;
                int index = 0;

                for (int j = 0; j < salesProducts.length; j++) {
                    if (max < salesProducts[j]) {
                        max = salesProducts[j];
                        index = j;
                    }
                }
                salesProducts[index] = 0;
                s[i] = appDB.getSales()[index];
            }

            return s;
        }

        return null;
    }

    public Statistic doSomeStatisticStuff() {

        return new Statistic(getMax(),getMin(),getAverage(),countSoldProducts());
    }

    public double getMax() {

        if (appDB.getCountBill() > 0) {

            double max = 0;

            if (appDB.getBills()[0] != null) {
                max = appDB.getBills()[0].getAmountPrice();
            }

            for (int i = 1; i < appDB.getCountBill(); i++) {
                if (appDB.getBills()[i] != null &&
                        max < appDB.getBills()[i].getAmountPrice()) {
                    max = appDB.getBills()[i].getAmountPrice();
                }
            }

            return max;
        }
        return 0;
    }

    public double getMin() {

        if (appDB.getCountBill() > 0) {

            double min = Double.MAX_VALUE;

            if (appDB.getBills()[0] != null) {
                min = appDB.getBills()[0].getAmountPrice();
            }

            for (int i = 1; i < appDB.getCountBill(); i++) {
                if (appDB.getBills()[i] != null &&
                        min > appDB.getBills()[i].getAmountPrice()) {
                    min = appDB.getBills()[i].getAmountPrice();
                }
            }

            return min;
        }

        return 0;
    }

    public double getAverage() {

        if (appDB.getCountBill() > 0) {

            double result = 0;

            if (appDB.getBills()[0] != null) {
                result = appDB.getBills()[0].getAmountPrice();
            }

            for (int i = 1; i < appDB.getCountBill(); i++) {
                if (appDB.getBills()[i] != null)
                    result += appDB.getBills()[i].getAmountPrice();
            }
            result /= appDB.getCountBill();

            return result;
        }

        return 0;
    }

    public int countSoldProducts() {

        int result = 0;

        for (int i = 0; i < appDB.getCountBill(); i++) {
            if (appDB.getBills()[i] != null) {
                result += appDB.getBills()[i].getNumProd();
            }
        }

        return result;
    }

    public Bill calculateAmountPrice(Bill bill) {

        if (bill == null) return null;

        double amount = 0;

        for (Product product : bill.getProducts()) {
            amount += product.getPrice();
        }

        bill.setAmountPrice(amount);

        return bill;
    }

    @Override
    public Bill[] filter(Salesman[] sales, Product[] products, Date startTime,
                         Date endTime, Comparator<Bill> comparator) {

        Bill[] resBill = new Bill[appDB.getBills().length];

        int index = 0;

        for (int i = 0; i < appDB.getCountBill(); i++) {

            boolean addBill = false;

            if (sales != null) {

                for (int j = 0; j < sales.length; j++) {
                    if (appDB.getBills()[i].getSalesMan().equals(sales[j])) {
                        addBill = true;
                        break;
                    }
                }
            }

            if (addBill && products != null) {

                if (appDB.getBills()[i].hasProducts(products)) {
                    addBill = true;
                } else {
                    addBill = false;
                }
            }

            if (addBill && (startTime != null || endTime != null)) {

                if (startTime != null) {
                    if (appDB.getBills()[i].getCloseTime().
                            compareTo(startTime) >= 0) {
                        addBill = true;
                    } else {
                        addBill = false;
                    }
                }

                if (endTime != null) {
                    if (appDB.getBills()[i].getCloseTime().
                            compareTo(endTime) <= 0) {
                        addBill = true;
                    } else {
                        addBill = false;
                    }
                }

            }

            if (addBill) resBill[index++] = appDB.getBills()[i];

            Arrays.sort(resBill,comparator);

        }

        return resBill;
    }

}
