package ua.artcode.market.interfaces;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;

import java.util.List;

public interface ITerminal {

    public void showInfo(Terminal currentTerminal);

    public int getQuantityBillsTerminal(Terminal terminal);

    public void saveClosedBill(Terminal  currentTerminal, Bill currentBill);

    public void setAutorizedSalesMan(Terminal terminal, SalesMan autorizedSalesMan);

    public SalesMan getSalesMan(Terminal terminal);

    public List<Bill> getBillsTerminal(Terminal terminal);


}
