package implementation;

import controllers.TerminalController;
import models.Product;
import models.Salesman;

public class Terminal implements TerminalController{

    public boolean login() {
        return false;
    }

    public Bill createBill() {
        return null;
    }

    public Product addProduct() {
        return null;
    }

    public Bill closeAndSaveBill(Bill bill) {
        return null;
    }

    public Bill findBillById(long id) {
        return null;
    }

    public Salesman findSalesmanByLoginOrFullname(String fullname) {
        return null;
    }

    public Salesman getTopNofSalesMan() {
        return null;
    }
}
