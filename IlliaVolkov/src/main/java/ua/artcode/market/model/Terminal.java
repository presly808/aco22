package ua.artcode.market.model;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.interfaces.ITerminal;

import java.util.List;

public class Terminal {

    public final AppDBImpl currentAppDBImpl;
    public final BillController billController;
    private SalesMan autorizedSalesMan;

    public Terminal(AppDBImpl currentAppDBImpl){

        this.currentAppDBImpl = currentAppDBImpl;
        this.billController = currentAppDBImpl.billController;
    }

    public void setAutorizedSalesMan(SalesMan autorizedSalesMan) { this.autorizedSalesMan = autorizedSalesMan; }

    public SalesMan getSalesMan() { return this.autorizedSalesMan; }

    public List<Bill> getBillsTerminal() { return this.currentAppDBImpl.getBillsTerminal(this); }



}
