package ua.artcode.market.Model;

import ua.artcode.market.Controller.BillController;
import ua.artcode.market.Interface.ITerminal;

import javax.swing.*;

public class Terminal implements ITerminal{

    private Bill[] bills;
    private BillController billController;
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
        return JOptionPane.showConfirmDialog( null, "Create a new check?","Waiting to continue work", JOptionPane.YES_NO_OPTION);
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

        BillController billController = new BillController();

        Terminal myTerminal = new Terminal(billController);

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
        int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

        Product[] productsList = Product.initProductsList(countProducts);

        String nameSaler = JOptionPane.showInputDialog("Enter SalesMan Name");

        int key = myTerminal.questionCreateBill();

        while (key == JOptionPane.YES_OPTION) {

            Bill currentBill = myTerminal.createBill(nameSaler, productsList);

            while (!currentBill.closed) {

                billController.choseProduct(currentBill);

                billController.printBill(currentBill);

                billController.allProductsSelected(currentBill);

                billController.questionForClosingBill(currentBill);

                myTerminal.saveBill(currentBill);

            }

            key = myTerminal.questionCreateBill();
        }
        Terminal.showInfo(myTerminal);
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

                myTerminal.billController.printBill(myTerminal.bills[i]);
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
