package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.interfaces.SomeStatistics;
import ua.artcode.market.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TerminalController implements ITerminal, SomeStatistics{
    private static Map<Salesman, String> salesmanLogin;
    private static Map<String, String> loginPassword;
    private BillController billController;

    public TerminalController() {
        this.billController = new BillController();
    }

//    public static Map<Salesman, String[]> getSalesmanList() {
//        return salesmanList;
//    }
//
//    public static void setSalesmanList(Map<Salesman, String[]> list) {
//        salesmanList = list;
//    }

    @Override
    public Bill createBill(Terminal terminal, Salesman salesman) {
        Bill bill = new Bill();
        bill.setSalesman(salesman);
        terminal.getSales().add(salesman);
        terminal.getBills().add(bill);

        return this.billController.getBills().add(bill) ? bill : null;
    }

    @Override
    public boolean addProduct(Bill bill, Product product) {
        return bill != null && product != null &&
                this.billController.getBills().contains(bill) &&
                this.billController.addProduct(bill, product);

    }

    @Override
    public boolean closeAndSafeBill(Bill bill) {
        if (bill != null && billController.closeBill(bill)) {
            bill.setAmountPrice(billController.getAmountPrice());
            billController.printBill(bill);
            return true;
        }
        return false;
    }

    @Override
    public Salesman login(Terminal terminal, String login, String pass) {
        if (terminal != null || login == null || pass == null) return null;

        for (Map.Entry<String, String> pair : loginPassword.entrySet()) {
            if (login.equals(pair.getKey()) && pass.equals(pair.getValue())){
                for (Map.Entry<Salesman, String> pair1 :
                        salesmanLogin.entrySet()) {
                    if (login.equals(pair1.getValue())) {
                        Salesman salesman = pair1.getKey();
                        terminal.getSales().add(salesman);
                        return salesman;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public List<Bill> getSalesmanBills (Salesman salesman) {
        ArrayList<Bill> bills = null;
        if (salesman != null) {
            for (Bill bill : billController.getBills()) {
                if (salesman.equals(bill.getSalesman())) {
                    if (bills == null) {
                        bills = new ArrayList<Bill>();
                    }
                    bills.add(bill);
                }
            }
        }
        return bills;
    }

    @Override
    public Salesman create (String fullName, String login, String password) {

        Salesman seller =
                new Salesman(fullName, login, password);

        if (salesmanLogin == null) {
            salesmanLogin = new HashMap<Salesman, String>();
            loginPassword = new HashMap<String, String>();
        }

        if (salesmanLogin.isEmpty() || !salesmanLogin.containsValue(login)) {
            salesmanLogin.put(seller, login);
            loginPassword.put(login, password);
            return seller;
        }
        if (salesmanLogin.containsValue(login)) {
            return seller;
        }
        return null;
    }



}
