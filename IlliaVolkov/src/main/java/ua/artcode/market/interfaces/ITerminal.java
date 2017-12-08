package ua.artcode.market.interfaces;

import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;

import java.io.IOException;
import java.util.List;

public interface ITerminal {

    int getQuantityBillsTerminal(Terminal terminal) throws IOException;

    void setAutorizedSalesMan(Terminal terminal, SalesMan autorizedSalesMan);

    SalesMan getSalesMan(Terminal terminal);

    List<Bill> getBillsTerminal(Terminal terminal) throws IOException;

}
