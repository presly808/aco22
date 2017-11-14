package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;


public class TerminalController implements ITerminal{
    private BillController billController;

    public TerminalController() {
        this.billController = new BillController();
    }

    public boolean createBill(Bill bill) {
        return bill != null && this.billController.getBills().add(bill);
    }

    public boolean addProduct(Bill bill, Product product) {
        return bill != null && product != null &&
                !this.billController.getBills().contains(bill) &&
                this.billController.addProduct(bill, product);

    }

    public boolean closeAndSafeBill(Bill bill) {
        if (bill != null) {
            if (billController.closeBill(bill)) {
                billController.calculateAmountPrice(bill);
                billController.printBill(bill);
                return true;
            }
        }
        return false;
    }

}
