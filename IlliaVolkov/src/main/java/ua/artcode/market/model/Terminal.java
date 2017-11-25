package ua.artcode.market.model;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.view.InterfaceServices;

import javax.swing.*;
import java.util.ArrayList;

public class Terminal implements ITerminal{

    private Bill[] bills;
    private ArrayList<Bill> billsTerminal;
    public final BillController billController;
    //private SalesMan[] sales;

    public Terminal(BillController billController){

        this.billController = billController;

        //this.bills = new Bill[100];
        this.billsTerminal = new ArrayList<>();
    }

    public Bill[] getBills() {

        return bills;
    }

    public ArrayList<Bill> getBillsTerminal() {

        return billsTerminal;
    }

    @Override
    public int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Create a new check?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }

    @Override
    public Bill createBill(String nameSaler, Product[] productsList) {

        /*for (int i = 0; i < this.bills.length; i++) {
            if (this.bills[i] == null) {
                Bill currentBill = new Bill(i+1, nameSaler, productsList);
                return currentBill;
            }
        }
        System.out.println("The terminal is full, it is necessary to remove I report!");
        return new Bill(0,"", productsList);
        */

        return new Bill(this.billsTerminal.size()+1, nameSaler, productsList);
    }

    public static void runTerminal(){
        InterfaceServices.runTerminal();
    }

    public void saveBill(Bill currentBill) {

        if (currentBill.closed) {
            //Matrix
           /* for (int i = 0; i < this.bills.length; i++) {
                if (this.bills[i] == null) {
                    this.bills[i] = currentBill;

                    break;
                }
            }*/

            //ArrayList
            this.billsTerminal.add(currentBill);
        }
    }



    public static void showInfo(Terminal myTerminal){

        System.out.println("\n\n\n STATISTICS OF THE WORK OF THE STORE");
        //Matrix
        /*for (int i = 0; i < myTerminal.bills.length; i++) {

            if (myTerminal.bills[i] != null) {

                InterfaceServices.printBill(myTerminal.bills[i]);
            }
        }*/

        for (int i = 0; i < myTerminal.billsTerminal.size(); i++) {
            InterfaceServices.printBill(myTerminal.billsTerminal.get(i));
        }
    }

    public int countQuontityBills(){

        int quontity = 0;

        //Matrix
        /*for (int i = 0; i < this.bills.length; i++) {
            if (this.bills[i] != null) {
                quontuty ++;
            }
        }*/

        //ArrayList
        quontity = this.billsTerminal.size();

        return quontity;
    }

}
