package controllers;

import interfaces.ITerminal;
import models.Bill;
import models.Product;
import models.Salesman;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalController implements ITerminal{

    private Set<BillController> billSet;
    private BillController billController;

    public TerminalController() {
        this.billSet = new HashSet<>();
        this.billController = new BillController();

    }

    public Bill createBill(Bill bill) {
        return new Bill();
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

    public Salesman findSalesmanByLoginOrFullName(String fullName) {
        return billController.getBillSet().stream().filter(bill -> bill.getSalesman().getFullname().equals(fullName)
                || bill.getSalesman().getLogin().equals(fullName)).
                collect(Collectors.toSet()).iterator().next().getSalesman();
    }

    //TODO Need to refactor
    public List<Bill> sortBillListByDateCreation() {
        List<Bill> list = new ArrayList<>();
        Set<Bill> set = billController.getBillSet();
        list.addAll(set);
        Collections.sort(list, (bill, bill1) -> {
            long i1 = (bill.getCreationDate());
            long i2 = (bill1.getCreationDate());
            return i1 > i2 ? -1 : (i1 == i2 ? 0 : 1);
        });
        return list;
    }
}
