package ua.artcode.market.model;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.controllers.BillController;

import java.io.IOException;
import java.util.List;

public class Terminal {

    public final BillController billController;
    private SalesMan autorizedSalesMan;

    public Terminal() throws IOException {
        this.billController = AppDBImpl.getEntity().billController;
    }

    public void setAutorizedSalesMan(SalesMan autorizedSalesMan) { this.autorizedSalesMan = autorizedSalesMan; }

    public SalesMan getSalesMan() { return this.autorizedSalesMan; }

    public List<Bill> getBillsTerminal() throws IOException { return AppDBImpl.getEntity().getBillsTerminal(this); }



}
