package ua.artcode.market.controllers;

import ua.artcode.market.databases.AppDB;
import ua.artcode.market.comparators.SalesmenSoldProductsComparator;
import ua.artcode.market.exceptions.AppException;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static ua.artcode.market.utils.Utils.*;

public class TerminalController implements ITerminal {

    private AppDB appDB;

    public TerminalController(AppDB appDB) {
        this.appDB = appDB;
    }

    public AppDB getAppDB() {
        return appDB;
    }

    public Salesman logIn(String login, String password) throws AppException {

        if (appDB.getAllSalesman().isEmpty() || login == null ||
                login.isEmpty() || password == null || password.isEmpty())

            throw new AppException("Incorrect login or password");

        Salesman salesman =
                appDB.findSalesmanByLoginOAndPassword(login, password);

        if (salesman != null) {
            salesman.setLogged(true);
        } else {
            throw new AppException("Invalid login or password");
        }

        return salesman;
    }

    public void logOut(Salesman salesman) {
        salesman.setLogged(false);
    }


    public Bill createBill(Salesman salesman) throws AppException {

        if (salesman == null || !salesman.isLogged())
            throw new AppException("Salesman isn't logged or defined");

        Bill bill = new Bill(salesman);

        Bill billWihtId = appDB.saveBill(bill);

        return billWihtId;

    }

    public Bill addProduct(int billId, Product product) throws AppException {

        Bill bill = appDB.findByBillId(billId);

        if (bill == null)
            throw new AppException("Bill isn't found");

        bill.getProducts().add(product);

        appDB.updateBill(bill);

        return appDB.updateBill(bill);

    }

    public Bill closeAndSaveBill(int billId) throws AppException {

        Bill bill = appDB.findByBillId(billId);

        if (bill == null) throw new AppException("Bill isn't found");

        if (!bill.isClosed()) {

            bill.calculateAmountPrice();
            bill.setCloseTime(LocalDateTime.now());
            bill.setClosed(true);

            appDB.updateBill(bill);

        } else throw new AppException("Bill is closed");

        return bill;
    }

    public List<Salesman> getTopNOfSalesMen(int n) throws AppException {

        if (appDB.getAllSalesman().isEmpty() || n <= 0)
            throw new AppException("N is negative");

        List<Salesman> salesmen = appDB.getAllSalesman();

        List<Bill> bills = appDB.getAllBills();

        salesmen.stream().forEach(s -> s.setSoldProducts(0));
        salesmen.stream().forEach(s -> s.setAmountSales(0));

        bills.stream().filter(b -> b.isClosed()).
                peek(b -> b.getSalesMan().setSoldProducts(b.getSalesMan().
                        getSoldProducts() + b.getProducts().size())).
                forEach(b -> b.getSalesMan().
                        setAmountSales(b.getSalesMan().getAmountSales() +
                                b.getAmountPrice()));

        return salesmen.stream().sorted((s1, s2) ->
                (s1.getSoldProducts() - s2.getSoldProducts())).
                 skip(salesmen.size() - n).sorted((s1, s2) ->
                (s2.getSoldProducts() - s1.getSoldProducts())).
                        collect(Collectors.toList());

/*
        for (Salesman salesman : salesmen) {
            salesman.setSoldProducts(0);
            salesman.setAmountSales(0);
        }

        for (int i = 0; i < bills.size(); i++) {

            if (!bills.get(i).isClosed()) continue;

            Salesman salesman = bills.get(i).getSalesMan();

            salesman.setSoldProducts(
                    salesman.getSoldProducts() +
                            bills.get(i).getProducts().size());

            salesman.setAmountSales(salesman.getAmountSales() +
                    bills.get(i).getAmountPrice());


        }

        salesmen.sort(new SalesmenSoldProductsComparator());

        return salesmen.subList(0, n); */
    }

    public Statistic doSomeStatisticStuff() {

        return new Statistic(getMax(appDB), getMin(appDB),
                getAverage(appDB), countSoldProducts(appDB));
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen,
                             List<Product> products,
                             LocalDateTime startTime,
                             LocalDateTime endTime,
                             Comparator<Bill> comparator) {

        return staticFilter(appDB, salesmen, products,
                startTime, endTime, comparator);
    }

}
