package main.java.controllers;

import main.java.interfaces.ITerminal;
import main.java.models.Product;
import main.java.models.Salesman;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TerminalController implements ITerminal {

    private BillController currentBill;
    private Set<BillController> billSet;

    public TerminalController() {
        this.billSet = new HashSet<>();
    }

    //TODO should be implemented
    public boolean login() {
        return false;
    }

    public BillController createBill() {
        this.currentBill = new BillController();
        billSet.add(currentBill);
        return currentBill;
    }

    public Product addProduct(String productName) {
        if (currentBill == null) {
            throw new IllegalStateException("BillController not created");
        }
        return currentBill.addProduct(productName);
    }

    public void closeAndSaveBill(BillController bill) {
        this.currentBill = null;
        bill.closeBill();
    }

    //TODO add validation
    public BillController findBillById(long id) {
        return billSet.stream().filter(bill -> bill.getId() == id).collect(Collectors.toSet()).iterator().next();
    }

    public Salesman findSalesmanByLoginOrFullName(String fullName) {
        return billSet.stream().filter(bill -> bill.getSalesman().getFullname().equals(fullName)
                                                    || bill.getSalesman().getLogin().equals(fullName)).
                                                        collect(Collectors.toSet()).iterator().next().getSalesman();
    }

    //TODO should be implemented
    public Salesman getTopOnSalesMan() {
        return null;
    }

    //TODO UI should be implemented
}
