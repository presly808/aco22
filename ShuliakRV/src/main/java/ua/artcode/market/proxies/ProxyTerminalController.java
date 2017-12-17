package ua.artcode.market.proxies;

import ua.artcode.market.databases.AppDB;
import ua.artcode.market.exceptions.AppException;
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

    public ProxyTerminalController(ITerminal terminalController) {

        this.terminalController = terminalController;
    }

    @Override
    public AppDB getAppDB() {
        return terminalController.getAppDB();
    }

    @Override
    public Salesman logIn(String login, String password) throws AppException {

        Logger.getInstance(terminalController.getAppDB()).log(
                String.format("Time : %s  User is logging " +
                        "with login: %s and password: %s",
                LocalDateTime.now().toString(), login, password));

        loggedSalesman = terminalController.logIn(login, password);

        if (loggedSalesman != null) {

            Logger.getInstance(terminalController.getAppDB()).log(String.
                    format("Time : %s  User is logged " +
                                    "with login: %s and password: %s",
                            LocalDateTime.now().toString(),
                            loggedSalesman.getLogin(),
                            loggedSalesman.getPassword()));

            return loggedSalesman;

        } else {

            Logger.getInstance(terminalController.getAppDB()).log(String.
                    format("Time : %s  User isn't logged " +
                                    "with login: %s and password: %s",
                            LocalDateTime.now().toString(),
                            loggedSalesman.getLogin(),
                            loggedSalesman.getPassword()));
        }

        return null;
    }

    @Override
    public void logOut(Salesman salesman) {
        terminalController.logOut(salesman);
        loggedSalesman = null;
    }

    @Override
    public Bill createBill(Salesman salesmen) throws AppException {
        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to create a bill",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));

        return terminalController.createBill(salesmen);

    }

    @Override
    public Bill addProduct(int billId, Product product) throws AppException {
        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to add a product",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));

        return terminalController.addProduct(billId, product);
    }

    @Override
    public Bill closeAndSaveBill(int billId) throws AppException {

        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to close and save bill",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));

        return terminalController.closeAndSaveBill(billId);
    }

    @Override
    public List<Salesman> getTopNOfSalesMen(int n) throws AppException {
        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to get top N of salesmen",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));

        return terminalController.getTopNOfSalesMen(n);
    }

    @Override
    public Statistic doSomeStatisticStuff() {

        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to do some statistics",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));


        return terminalController.doSomeStatisticStuff();
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen, List<Product> products,
                             LocalDateTime startTime, LocalDateTime endTime,
                             Comparator<Bill> comparator) {

        Logger.getInstance(terminalController.getAppDB()).log(String.
                format("Time : %s  User %s is trying to filter some bills",
                        LocalDateTime.now().toString(),
                        loggedSalesman.getLogin()));


        return terminalController.filter(salesmen, products, startTime,
                endTime, comparator);
    }
}
