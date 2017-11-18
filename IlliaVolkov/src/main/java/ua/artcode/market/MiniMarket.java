package ua.artcode.market;

import javax.swing.*;

public class MiniMarket {


    public static void main(String[] args) {

        Terminal myTerminal = new Terminal();

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
        int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

        Product[] productsList = Product.initProductsList(countProducts);

        String nameSaler = JOptionPane.showInputDialog("Enter SalesMan Name");

        int key = Terminal.questionCreateBill();

        while (key == JOptionPane.YES_OPTION) {

            Bill currentBill = myTerminal.createBill(nameSaler, productsList);

            while (!currentBill.closed) {

                currentBill.choseProduct();

                Bill.printBill(currentBill);

                currentBill.allProductsSelected();

                currentBill.questionForClosingBill();

                myTerminal.saveBill(currentBill);

            }

            key = Terminal.questionCreateBill();
        }
        Terminal.showInfo(myTerminal);

    }

}