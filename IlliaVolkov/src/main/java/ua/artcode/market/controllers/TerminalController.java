package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;

import java.util.List;

public class TerminalController implements ITerminal{

    @Override
    public void showInfo(Terminal currentTerminal){

        System.out.println("\n\n\n STATISTICS OF THE WORK OF THE STORE");

        for (Bill itemBill: currentTerminal.currentAppDBImpl.getBillsTerminal(currentTerminal)) {
            BillController.printBill(itemBill);
        }
    }

    @Override
    public int getQuantityBillsTerminal(Terminal terminal) {
        return terminal.currentAppDBImpl.getQuantityBillsTerminal(terminal);
    }

    @Override
    public void saveClosedBill(Terminal  currentTerminal, Bill currentBill) {
        currentTerminal.currentAppDBImpl.saveClosedBill(currentBill);
    }

    @Override
    public void setAutorizedSalesMan(Terminal terminal, SalesMan autorizedSalesMan) { terminal.setAutorizedSalesMan(autorizedSalesMan); }

    @Override
    public SalesMan getSalesMan(Terminal terminal) { return terminal.getSalesMan(); }

    @Override
    public List<Bill> getBillsTerminal(Terminal terminal) { return terminal.currentAppDBImpl.getBillsTerminal(terminal); }

}
