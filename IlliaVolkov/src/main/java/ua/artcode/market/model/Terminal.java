package ua.artcode.market.model;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.view.InterfaceServices;

import javax.swing.*;

public class Terminal implements ITerminal{

    private Bill[] bills;
    public final BillController billController;
    //private SalesMan[] sales;

    public Terminal(BillController billController){

        this.billController = billController;

        bills = new Bill[100];
    }

    public Bill[] getBills() {

        return bills;
    }

    @Override
    public int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Create a new check?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }

    @Override
    public Bill createBill(String nameSaler, Product[] productsList) {

        for (int i = 0; i < this.bills.length; i++) {
            if (this.bills[i] == null) {

                Bill currentBill = new Bill(i+1, 100, nameSaler, productsList);

                return currentBill;
            }
        }

        System.out.println("The terminal is full, it is necessary to remove I report!");

        return new Bill(0,0, "", productsList);
    }

    public static void runTerminal(){
        InterfaceServices.runTerminal();
    }

    public void saveBill(Bill currentBill) {

        if (currentBill.closed) {

            for (int i = 0; i < this.bills.length; i++) {
                if (this.bills[i] == null) {
                    this.bills[i] = currentBill;

                    break;
                }
            }
        }
    }



    public static void showInfo(Terminal myTerminal){

        System.out.println("\n\n\n STATISTICS OF THE WORK OF THE STORE");

        for (int i = 0; i < myTerminal.bills.length; i++) {

            if (myTerminal.bills[i] != null) {

                InterfaceServices.printBill(myTerminal.bills[i]);
            }
        }
    }

    public int countQuontityBills(){

        int quontuty = 0;

        for (int i = 0; i < this.bills.length; i++) {
            if (this.bills[i] != null) {
                quontuty ++;
            }
        }

        return quontuty;
    }

}
