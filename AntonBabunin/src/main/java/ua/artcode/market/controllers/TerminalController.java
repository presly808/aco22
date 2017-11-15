package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;


public class TerminalController implements ITerminal{
    private BillController billController;

    public TerminalController() {
        this.billController = new BillController();
    }

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

}
