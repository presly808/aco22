package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;

import java.io.IOException;
import java.util.List;

public class TerminalController implements ITerminal{

    @Override
    public int getQuantityBillsTerminal(Terminal terminal) throws IOException {
        return AppDBImpl.getEntity().getQuantityBillsTerminal(terminal);
    }

    @Override
    public void setAutorizedSalesMan(Terminal terminal, SalesMan autorizedSalesMan) { terminal.setAutorizedSalesMan(autorizedSalesMan); }

    @Override
    public SalesMan getSalesMan(Terminal terminal) { return terminal.getSalesMan(); }

    @Override
    public List<Bill> getBillsTerminal(Terminal terminal) throws IOException { return AppDBImpl.getEntity().getBillsTerminal(terminal); }

}
