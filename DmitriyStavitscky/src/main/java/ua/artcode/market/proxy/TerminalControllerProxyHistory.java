package ua.artcode.market.proxy;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;
import ua.artcode.market.models.Time;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class TerminalControllerProxyHistory implements ITerminal {

    private TerminalController terminal;

    private String action;

    public TerminalControllerProxyHistory(TerminalController terminal) {
        this.terminal = terminal;
    }

    @Override
    public Salesman addSalesman(String fullName, String login, int pass) {
        return terminal.addSalesman(fullName, login, pass);
    }

    @Override
    public void signIn(String loginOrName, int password) {
        terminal.signIn(loginOrName, password);
        action = "Saleman " + terminal.getLoggedSalesman().getFullName() + " logged.";
        rememberActionAndPrint();
    }

    @Override
    public void logOut() {
        terminal.logOut();
        action = "You are logged out at " + LocalDateTime.now();
        rememberActionAndPrint();
    }

    @Override
    public void createBill() {
        terminal.createBill();
        action = String.format("Salesman %s created a new bill at %s ", terminal.getLoggedSalesman().getFullName(),
                terminal.getCurrentBill().getOpenTime().toString());
        rememberActionAndPrint();
    }

    @Override
    public void closeAndSaveBill(int hours, int minutes, int seconds) {
        terminal.closeAndSaveBill(hours, minutes, seconds);
        action = "bill closed. " + terminal.getAllBills().
                get(terminal.getAllBills().size() - 1).
                toString();
        rememberActionAndPrint();
    }

    @Override
    public void addProductToBill(int id) {
        terminal.addProductToBill(id);
    }

    @Override
    public double calculateSalesmanSalary(Salesman chief) {
        return terminal.calculateSalesmanSalary(chief);
    }

    @Override
    public Salesman getTopNofSalesMan() {
        return terminal.getTopNofSalesMan();
    }

    @Override
    public Statistics makeStatistics() {
        return terminal.makeStatistics();
    }

    @Override
    public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
        return terminal.filterByTime(bills, startTime, endTime, comparator);
    }

    @Override
    public void addSubSalesman(Salesman chief, Salesman subordinate) {
        terminal.addSubSalesman(chief, subordinate);
    }

    @Override
    public double requiredAmountFromTheDepartment(Salesman chief) {
        return terminal.requiredAmountFromTheDepartment(chief);
    }

    @Override
    public List<Bill> getAllBills() {
        return terminal.getAllBills();
    }

    @Override
    public AppDB getAppDB() {
        return terminal.getAppDB();
    }

    private void rememberActionAndPrint() {
        terminal.getAppDB().addActionToHistory(action);
        System.out.println(action);
        action = null;
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

    public Bill getCurrentBill() {
        return terminal.getCurrentBill();
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
