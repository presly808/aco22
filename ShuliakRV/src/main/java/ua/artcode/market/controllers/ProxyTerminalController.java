package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;
import ua.artcode.market.singletons.Logger;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class ProxyTerminalController implements ITerminal {

    private ITerminal terminalController;

    private Salesman loggedSalesman;

    private boolean isLoggedUser;

    public ProxyTerminalController(ITerminal terminalController) {

        this.terminalController = terminalController;
    }


    @Override
    public boolean logIn(String login, String password) {

        Logger.getInstance().log(String.format("Time : %t  User is logging " +
                        "with login: %s and password: %s",
                LocalDateTime.now(), login, password));

        loggedSalesman = terminalController.logIn(login, password);

        if (loggedSalesman != null) {

            isLoggedUser = true;

            Logger.getInstance().log(String.format("Time : %t  User is logged " +
                            "with login: %s and password: %s",
                    LocalDateTime.now(), login, password));
        } else {

            isLoggedUser = false;

            Logger.getInstance().log(String.format("Time : %t  User isn't logged " +
                            "with login: %s and password: %s",
                    LocalDateTime.now(), login, password));
        }

        return isLoggedUser;
    }

    @Override
    public Bill createBill(Salesman salesmen) {
        Logger.getInstance().log(String.
                format("Time : %t  User %s is trying to create a bill",
                        LocalDateTime.now(), salesmen.getLogin()));
        Bill bill = terminalController.createBill(salesmen);

        if (bill != null) {
            Logger.getInstance().log(String.
                    format("Time : %t  bill ID: %d is created",
                            LocalDateTime.now(), bill.getId()));
        } else {
            Logger.getInstance().log(String.
                    format("Time : %t  bill isn't created",
                            LocalDateTime.now()));
        }

        return bill;

    }

    @Override
    public Bill addProduct(int billId, Product product) {

        Logger.getInstance().log(String.
                format("Time : %t  User is trying to add a product",
                        LocalDateTime.now()));

        Bill bill = terminalController.addProduct(billId, product);

        if (bill != null) {
            Logger.getInstance().log(String.
                    format("Time : %t  bill ID: %d is created",
                            LocalDateTime.now(), bill.getId()));
        } else {
            Logger.getInstance().log(String.
                    format("Time : %t  bill isn't created",
                            LocalDateTime.now()));
        }

        return null;
    }

    @Override
    public Bill closeAndSaveBill(int billId) {
        return null;
    }

    @Override
    public List<Salesman> getTopNOfSalesMen(int n) {
        return null;
    }

    @Override
    public Statistic doSomeStatisticStuff() {
        return null;
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen, List<Product> products,
                             LocalDateTime startTime, LocalDateTime endTime,
                             Comparator<Bill> comparator) {
        return null;
    }
}
