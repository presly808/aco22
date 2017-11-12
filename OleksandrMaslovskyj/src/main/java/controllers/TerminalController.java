package controllers;

import interfaces.ITerminal;
import models.Bill;
import models.Product;
import models.Salesman;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalController implements ITerminal{

    private BillController billController;

    public TerminalController() {
        this.billController = new BillController();
    }

    public Bill createBill(Bill bill) {
        billController.getBillSet().add(bill);
        return bill;
    }

    public Product addProduct(Bill bill, String productName) {
        if (bill == null) {
            throw new IllegalStateException("BillController not created");
        }
        return billController.addProductToBill(bill, productName);
    }

    public void closeAndSaveBill(Bill bill) {
        billController.closeBill(bill);
    }

    //TODO add validation
    public Bill findBillById(long id) {
        return billController.getBillSet().stream().filter(bill -> bill.getId() == id).collect(Collectors.toSet()).iterator().next();
    }

    public Salesman findSalesmanByLoginOrFullName(String fullName, String login) {
        Set<Bill> billSet = billController.getBillSet();
        return billSet.stream().filter((Bill bill) -> {
            Salesman salesman = bill.getSalesman();
            if (salesman == null) {
                return false;
            }
            String fullname = salesman.getFullname();
            String login1 = salesman.getLogin();
            if (fullname == null && login1 == null ) {
                return false;
            }
            if (fullname.equals(fullName) ||
                    login1.equals(login)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList()).get(0).getSalesman();
    }

    //TODO Need to refactor
    public List<Bill> sortBillListByDateCreation() {
        List<Bill> list = new ArrayList<>();
        Set<Bill> set = billController.getBillSet();
        list.addAll(set);
        Collections.sort(list, (bill, bill1) -> {
            Date i1 = (bill.getCreationDate());
            Date i2 = (bill1.getCreationDate());
            return i1.compareTo(i2);
        });
        return list;
    }

    public Set<Bill> getBillSet() {
        return billController.getBillSet();
    }
}
