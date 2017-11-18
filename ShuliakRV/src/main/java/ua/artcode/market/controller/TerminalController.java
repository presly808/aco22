package ua.artcode.market.controller;

import com.sun.corba.se.spi.monitoring.StatisticsAccumulator;
import ua.artcode.market.models.*;

import java.util.Scanner;

public class TerminalController {

    private AppDB appDB;

    private int currentBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }


    public Salesman login(String login, String password) {

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

    public Bill findBillById(int id) {
        for (int i = 0; i < appDB.getCountBill(); i++) {
            if (appDB.getBills()[i] != null &&
                    appDB.getBills()[i].getId() == id)
                return appDB.getBills()[i];
        }

        return null;
    }

    public Salesman[] findSalesmanByLoginOrFullname(String loginOrFullname) {

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

    public int getIndexSales(Salesman s) {

        if (s != null) {

            for (int i = 0; i < appDB.getSales().length; i++) {
                if (appDB.getSales()[i] == s) return i;
            }
        }

        return -1;
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

    public Statistic doSomeStatisticStuff(Statistic s) {

        s = new Statistic();

        s.setMaxAmountofBill(getMax());
        s.setMinAmountofBill(getMin());
        s.setAverageAmountofBill(getAverage());
        s.setCountSoldProducts(countSoldProducts());

        return s;
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

}
