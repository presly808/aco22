package ua.artcode.market;

import javax.swing.*;
import java.lang.reflect.Array;

public class Terminal {

    private Bill[] bills;

    private SalesMan[] sales;

    Terminal(){

        bills = new Bill[100];
    }

    public static int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Create a new check?","Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }


    public Bill createBill( String nameSaler, Product[] productsList) {

        for (int i = 0; i < this.bills.length; i++) {
            if (this.bills[i] == null) {

                Bill currentBill = new Bill(i+1, 100, nameSaler, productsList);

                return currentBill;
            }
        }

        System.out.println("The terminal is full, it is necessary to remove I report!");

        return new Bill(0,0, "", productsList);
    }

    public void closeAndSaveBill(Bill currentBill) {

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

                Bill.printBill(myTerminal.bills[i]);
            }
        }
    }
}
