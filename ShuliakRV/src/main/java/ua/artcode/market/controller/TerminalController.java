package ua.artcode.market.controller;

import ua.artcode.market.comparators.SalesmenSoldProductsComparator;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.*;

import java.time.LocalDateTime;
import java.util.*;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    private int currentBill;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }


    public Salesman logIn(String login, String password) {

        if (appDB.getAllSalesman().isEmpty() || login == null ||
                login.isEmpty() || password == null || password.isEmpty())
            return null;

        Salesman salesman =
                appDB.findSalesmanByLoginOAndPassword(login, password);

        if (salesman != null) salesman.setLogged(true);

        return salesman;
    }


    public Bill createBill(Salesman salesman) {

        if (salesman == null || salesman.isLogged() == false) return null;

        Bill bill = new Bill(salesman);

        Bill billWihtId = appDB.saveBill(bill);

        return billWihtId;

    }

    public Bill addProduct(int billId, Product product) {

        Bill bill = appDB.findByBillId(billId);

        if (bill == null) return null;

        bill.getProducts().add(product);

        appDB.updateBill(bill);

        return appDB.updateBill(bill);

    }

    public Bill closeAndSaveBill(int billId) {

        Bill bill = appDB.findByBillId(billId);

        if (bill == null) return null;

        bill.calculateAmountPrice();
        bill.setCloseTime(LocalDateTime.now());
        bill.setClosed(true);

        appDB.updateBill(bill);

        Salesman salesmen = bill.getSalesMan();

        salesmen.setSoldProducts(salesmen.getSoldProducts() +
                bill.getProducts().size());

        salesmen = appDB.updateSalesman(salesmen);

        return bill;
    }

    public List<Salesman> getTopNOfSalesMen(int n) {

        if (appDB.getAllSalesman().isEmpty() || n <= 0) return null;

        List<Salesman> salesmen = new ArrayList<>(n);

        appDB.getAllSalesman().sort(new SalesmenSoldProductsComparator());

        for (int i = 0; i < Math.min(n, appDB.getAllSalesman().size()); i++) {
            salesmen.add(appDB.getAllSalesman().get(i));
        }

        return salesmen;
    }

    public Statistic doSomeStatisticStuff() {

        return new Statistic(getMax(), getMin(),
                getAverage(), countSoldProducts());
    }

    public double getMax() {

        if (appDB.getAllBills().size() == 0) return 0;

        double max = appDB.getAllBills().get(0).getAmountPrice();

        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            if (max < appDB.getAllBills().get(i).getAmountPrice()) {
                max = appDB.getAllBills().get(i).getAmountPrice();
            }
        }

        return max;

    }

    public double getMin() {

        if (appDB.getAllBills().size() == 0) return 0;

        double min = appDB.getAllBills().get(0).getAmountPrice();


        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            if (min > appDB.getAllBills().get(i).getAmountPrice()) {
                min = appDB.getAllBills().get(i).getAmountPrice();
            }
        }

        return min;
    }

    public double getAverage() {

        if (appDB.getAllBills().size() == 0) return 0;

        double result = appDB.getAllBills().get(0).getAmountPrice();

        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            result += appDB.getAllBills().get(i).getAmountPrice();
        }

        result /= appDB.getAllBills().size();

        return result;
    }

    public int countSoldProducts() {

        int result = 0;

        for (int i = 0; i < appDB.getAllBills().size(); i++) {
            result += appDB.getAllBills().get(i).getProducts().size();

        }

        return result;
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen,
                             List<Product> products,
                             LocalDateTime startTime,
                             LocalDateTime endTime,
                             Comparator<Bill> comparator) {

        List<Bill> resBill = new ArrayList<>(appDB.getAllBills().size());

        int index = 0;

        for (int i = 0; i < appDB.getAllBills().size(); i++) {

            boolean addBill = false;

            if (salesmen != null) {

                for (int j = 0; j < salesmen.size(); j++) {
                    if (appDB.getAllBills().get(i).getSalesMan().
                            equals(salesmen.get(j))) {
                        addBill = true;
                        break;
                    }
                }
            }

            if (addBill && products != null) {

                if (appDB.getAllBills().get(i).hasProducts(products)) {
                    addBill = true;
                } else {
                    addBill = false;
                }
            }

            if (addBill && (startTime != null || endTime != null)) {

                if (startTime != null) {
                    if (appDB.getAllBills().get(i).getCloseTime().
                            compareTo(startTime) >= 0) {
                        addBill = true;
                    } else {
                        addBill = false;
                    }
                }

                if (endTime != null) {
                    if (appDB.getAllBills().get(i).getCloseTime().
                            compareTo(endTime) <= 0) {
                        addBill = true;
                    } else {
                        addBill = false;
                    }
                }

            }

            if (addBill) resBill.add(appDB.getAllBills().get(i));

            resBill.sort(comparator);

        }

        return resBill;
    }

}
