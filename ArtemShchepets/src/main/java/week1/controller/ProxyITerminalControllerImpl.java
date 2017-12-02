package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.SalesStatistic;
import week1.model.Seller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class ProxyITerminalControllerImpl implements ITerminalController {

    private ITerminalController terminalController;

    public ProxyITerminalControllerImpl(ITerminalController terminalController) {
        this.terminalController = terminalController;
    }

    @Override
    public boolean login(String login, String password) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to sign in.");

        return terminalController.login(login, password);
    }

    @Override
    public Bill createBill() {

        System.out.println("[" + LocalTime.now() + "]: User is trying to create a bill.");

        return terminalController.createBill();
    }

    @Override
    public Bill addProduct(int billId, Product product) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to add a product.");

        return terminalController.addProduct(billId, product);
    }

    @Override
    public int getCurrentSellerId() {
        return terminalController.getCurrentSellerId();
    }

    @Override
    public void setCurrentSeller(int currentSellerId) {
        terminalController.setCurrentSeller(currentSellerId);
    }

    @Override
    public List<Bill> getAllBills() {
        return terminalController.getAllBills();
    }

    @Override
    public Bill closeBill(int billId) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to close a bill.");

        return terminalController.closeBill(billId);
    }

    @Override
    public Bill findBillById(int billId) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to find a bill by id.");

        return terminalController.findBillById(billId);
    }

    @Override
    public Seller findSellerByLoginOrFullName(String loginOrFullName) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to find seller by login or full name.");

        return terminalController.findSellerByLoginOrFullName(loginOrFullName);
    }

    @Override
    public Seller getTopOfSalesman() {

        System.out.println("[" + LocalTime.now() + "]: User is trying to get top seller.");

        return terminalController.getTopOfSalesman();
    }

    @Override
    public SalesStatistic doSomeStatisticStuff() {

        System.out.println("[" + LocalTime.now() + "]: User is trying to get some statistic.");

        return terminalController.doSomeStatisticStuff();
    }

    @Override
    public List<Bill> filter(LocalDateTime startTime, LocalDateTime endTime, Comparator<Bill> comparator) {

        System.out.println("[" + LocalTime.now() + "]: User is trying to filter some bills.");

        return terminalController.filter(startTime, endTime, comparator);
    }

    @Override
    public void turnOnTerminalLogger() {
        System.out.println("[" + LocalTime.now() + "]: Terminal logger is on.");

        terminalController.turnOnTerminalLogger();
    }

    @Override
    public void turnOnDatabaseLogger() {
        System.out.println("[" + LocalTime.now() + "]: Database logger is on.");

        terminalController.turnOnDatabaseLogger();
    }
}
