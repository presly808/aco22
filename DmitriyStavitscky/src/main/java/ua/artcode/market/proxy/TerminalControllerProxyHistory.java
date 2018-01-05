package ua.artcode.market.proxy;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalException;
import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public class TerminalControllerProxyHistory implements ITerminal {

    private TerminalController terminal;

    private String action;

    public TerminalControllerProxyHistory(TerminalController terminal) {
        this.terminal = terminal;
    }

    @Override
    public Salesman addSalesman(String fullName, String login, int pass) throws AppDBException, TerminalException {
        return terminal.addSalesman(fullName, login, pass);
    }

    @Override
    public void signIn(String loginOrName, int password) throws AppDBException {
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
    public void createBill() throws TerminalException {
        terminal.createBill();
        action = String.format("Salesman %s created a new bill at %s ", terminal.getLoggedSalesman().getFullName(),
                terminal.getCurrentBill().getOpenTime().toString());
        rememberActionAndPrint();
    }

    @Override
    public void closeAndSaveBill() throws SaveBillException {
        terminal.closeAndSaveBill();
        action = "bill closed. " + terminal.getAllBills().
                get(terminal.getAllBills().size() - 1).
                toString();
        rememberActionAndPrint();
    }

    @Override
    public void addProductToBill(int id) throws AppDBException {
        terminal.addProductToBill(id);
    }

    @Override
    public double calculateSalesmanSalary(Salesman chief) {
        return terminal.calculateSalesmanSalary(chief);
    }

    @Override
    public Salesman getTopNofSalesMan() throws TerminalException {
        return terminal.getTopNofSalesMan();
    }

    @Override
    public Statistics makeStatistics() throws TerminalException {
        return terminal.makeStatistics();
    }

    @Override
    public List<Bill> filterByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return terminal.filterByTime(startTime, endTime);
    }

    @Override
    public void addSubSalesman(Salesman chief, Salesman subordinate) throws WrongSubordinateException, AppDBException {
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