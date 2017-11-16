package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ISalesman;
import ua.artcode.market.interfaces.SomeStatistics;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SalesmanController implements ISalesman, SomeStatistics{

    private TerminalController terminalController;

    public SalesmanController(){
        this.terminalController = new TerminalController();
    }


    @Override
    public Bill createBill(Terminal terminal, Salesman salesman) {
        if (terminal != null && salesman != null) {
            Bill bill = terminalController.createBill(terminal,salesman);

            return bill;
        }
        return null;
    }

    @Override
    public boolean addProduct(Terminal terminal, Bill bill, Product product) {
        if (terminal == null || bill == null || product == null) return false;

        return this.terminalController.addProduct(bill, product);
    }

    @Override
    public boolean closeAndSafeBill(Terminal terminal, Bill bill) {
        return terminal != null && bill != null &&
                terminalController.closeAndSafeBill(bill);

    }

    @Override
    public Salesman login(Terminal terminal, String login, String password) {
        return terminalController.login(terminal, login, password);
    }

    @Override
    public Salesman create(String fullName, String login, String password) {
        return terminalController.create(fullName, login, password);
    }

    @Override
    public List<Bill> getSalesmanBills(Salesman salesman) {
        if (salesman != null) {
            return terminalController.getSalesmanBills(salesman);
        }
        return null;
    }

    @Override
    public Bill findBillById(int id) {
        return terminalController.findBillById(id);
    }

    @Override
    public List<Bill> filterMethodAll(Salesman salesman, Product product,
                                      Date startDate, Date endDate,
                                      Comparator<Bill> billComparator) {
        return terminalController.filterMethodAll(salesman, product, startDate,
                endDate,billComparator);
    }

//    @Override
//    public List<Bill> filterMethod (Salesman salesman) {
//        return new ArrayList<>();
//    }


}

