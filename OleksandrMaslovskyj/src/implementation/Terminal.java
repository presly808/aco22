package implementation;

import controllers.TerminalController;
import models.Product;
import models.Salesman;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Terminal implements TerminalController{

    private Bill currentBill;
    private Set<Bill> billSet;

    public Terminal() {
        this.billSet = new HashSet<>();
    }

    //TODO should be implemented
    public boolean login() {
        return false;
    }

    public Bill createBill() {
        this.currentBill = new Bill();
        billSet.add(currentBill);
        return currentBill;
    }

    public Product addProduct(String productName) {
        if (currentBill == null) {
            throw new IllegalStateException("Bill not created");
        }
        return currentBill.addProduct(productName);
    }

    public void closeAndSaveBill(Bill bill) {
        this.currentBill = null;
        bill.closeBill();
    }

    //TODO add validation
    public Bill findBillById(long id) {
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
