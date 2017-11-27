package ua.artcode.market.model;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.view.InterfaceServices;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Terminal implements ITerminal{

    private List<Bill> billsTerminal;
    public final BillController billController;
    private List<SalesMan> salesMans;
    private SalesMan autorizedSalesMan;

    public Terminal(BillController billController){

        this.billController = billController;
        this.billsTerminal = new ArrayList<>();
    }

    public List<Bill> getBillsTerminal() { return billsTerminal; }

    public void setSalesMans(List<SalesMan> salesMans) { this.salesMans = salesMans; }

    public void setAutorizedSalesMan(SalesMan autorizedSalesMan) { this.autorizedSalesMan = autorizedSalesMan; }

    public List<SalesMan> getSalesMans() { return salesMans; }

    @Override
    public Bill createBill(SalesMan salerMan,List<Product> productsPrice) {

        return new Bill(this.billsTerminal.size()+1, salerMan, productsPrice);
    }

    @Override
    public void saveBill(Bill currentBill) {

        if (currentBill.closed) {

            this.billsTerminal.add(currentBill);
        }
    }

    public static void showInfo(Terminal myTerminal){

        System.out.println("\n\n\n STATISTICS OF THE WORK OF THE STORE");

        for (Bill itemBill: myTerminal.billsTerminal) {
            InterfaceServices.printBill(itemBill);
        }
    }

    @Override
    public int countQuontityBills(){

        return this.billsTerminal.size();
    }

}
