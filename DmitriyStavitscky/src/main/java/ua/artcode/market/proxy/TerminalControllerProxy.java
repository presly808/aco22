package ua.artcode.market.proxy;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.interf.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class TerminalControllerProxy implements ITerminal {

    private TerminalController terminal;

    private String action;

    public TerminalControllerProxy(TerminalController terminal) {
        this.terminal = terminal;
    }

    @Override
    public void addSalesman(String fullName, String login, int pass) {
        if (fullName.isEmpty() || login.isEmpty() || pass <= 0) {
            System.out.println("wrong data");

        } else {
            terminal.addSalesman(fullName, login, pass);
        }
    }

    @Override
    public void signIn(boolean isLogin, String loginOrName, int password) {

        if (terminal.getIsLogged()) {
            System.out.println("You already logged");

        } else if (terminal.getAppDB().findSalesman(loginOrName, isLogin) == null ||
                terminal.getAppDB().findSalesman(loginOrName, isLogin).getPass() != password) {
            System.out.println("wrong data");

        } else {
            terminal.signIn(isLogin, loginOrName, password);
            action = "Saleman " + terminal.getLoggedSalesman().getFullName() + " logged.";
            rememberActionAndPrint();
        }
    }

    @Override
    public void logOut() {
        terminal.logOut();
        action = "You are logged out at " + LocalDateTime.now();
        rememberActionAndPrint();
    }

    @Override
    public void createBill() {
        if (!terminal.getIsLogged()) {
            System.out.println("please log in");

        } else {
            terminal.createBill();
            action = String.format("Salesman %s created a new bill at %s ", terminal.getLoggedSalesman().getFullName(),
                    terminal.getNewBill().getOpenTime().toString());
            rememberActionAndPrint();
        }
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {
        if (terminal.getNewBill().getProducts().size() == 0) {
            System.out.println("you did not make a single sale, the bill wil be deleted");

        } else {
            terminal.closeAndSaveBill(hours, minutes, seconds);
            action = "bill closed. " + terminal.getAllBills().
                    get(terminal.getAllBills().size() - 1).
                    toString();
            rememberActionAndPrint();
        }
    }

    @Override
    public void addProductToBill(int id) {
        if (terminal.getIsLogged() && terminal.getAppDB().findProductById(id) != null) {
            terminal.getNewBill().getProducts().add(terminal.getAppDB().findProductById(id));

            System.out.println("Added product: " + terminal.getAppDB().findProductById(id).toString());

        } else {
            System.out.println("please log in");
        }
    }

    @Override
    public Salesman getTopNofSalesMan() {
        if (terminal.getAppDB().getBills().size() == 0) {
            System.out.println("count of bills = 0");
            return null;

        } else {
            return terminal.getTopNofSalesMan();
        }
    }

    @Override
    public Statistics makeStatistics() {
        if (terminal.getAppDB().getBills().size() == 0) {
            System.out.println("count of bills = 0");
            return null;

        } else {
            return terminal.makeStatistics();
        }
    }

    @Override
    public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
        return terminal.filterByTime(bills, startTime, endTime, comparator);
    }

    public void rememberActionAndPrint() {
        terminal.getAppDB().addActionToHistory(action);
        System.out.println(action);
        action = null;
    }

    @Override
    public List<Bill> getAllBills() {
        return terminal.getAllBills();
    }

    public int getCountOfBills() {
        return terminal.getAppDB().getBills().size();
    }

    public int getCountOfSalesman() {
        return terminal.getAppDB().getSalesmans().size();
    }

    public Salesman getLoggedSalesman() {
        return terminal.getLoggedSalesman();
    }

    public boolean getIsLogged() {
        return terminal.getIsLogged();
    }

    public AppDB getAppDB() {
        return terminal.getAppDB();
    }

    public Bill getNewBill() {
        return terminal.getNewBill();
    }

    public List<Salesman> getSalesmans() {
        return terminal.getAppDB().getSalesmans();
    }
}
/*
package ua.artcode.week3.design_patterns.social_network.common;

public class SocialNetworkProxy implements SocialNetworkApi {

    private SocialNetworkApi target;

    public SocialNetworkProxy(SocialNetworkApi targe) {
        this.target = target;
    }

    @Override
    public String login(String email, String pass) {

        System.out.printf("email %s, pass %s\n", email, pass);
        return target.login(email, pass);
    }

    @Override
    public int createGroup(String name) {
        return 0;
    }
}
*/
