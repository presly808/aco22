package ua.artcode.market.view;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.Terminal;

import javax.swing.*;

public class InterfaceServices {

    public static void runTerminal() {

        BillController billController = new BillController();

        Terminal myTerminal = new Terminal(billController);

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store",
                0);
        int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

        Product[] productsList = Product.initProductsList(countProducts);

        String nameSaler = JOptionPane.showInputDialog("Enter SalesMan Name");

        int key = myTerminal.questionCreateBill();

        while (key == JOptionPane.YES_OPTION) {

            Bill currentBill = myTerminal.createBill(nameSaler, productsList);

            while (!currentBill.closed) {

                billController.choseProduct(currentBill);

                InterfaceServices.printBill(currentBill);

                billController.allProductsSelected(currentBill);

                billController.questionForClosingBill(currentBill);

                myTerminal.saveBill(currentBill);

            }

            key = myTerminal.questionCreateBill();
        }
        Terminal.showInfo(myTerminal);
    }

    public static void allProductsSelected(Bill currentBill) {

        String message = "Are the products selected correctly?";
        String title = "Waiting to continue work";
        int key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);

        if (key != JOptionPane.YES_OPTION) {

            while (key == JOptionPane.NO_OPTION) {

                String stringProductCode = JOptionPane.showInputDialog("Enter the product code");
                int productCode = Integer.parseInt(stringProductCode);

                if (productCode != 0) {

                    message = "Enter the quantity of the product " + Product.findByCode(currentBill.getProductList(), productCode).name;
                    String stringProductQuontity = JOptionPane.showInputDialog(message);
                    int productQuontity = Integer.parseInt(stringProductQuontity);

                    if (productQuontity == 0 || productCode == 0) {

                        BillController.changeProduct(currentBill, productCode, productQuontity);
                    }

                    printBill(currentBill);
                }
                message = "All positions are corrected?";
                title = "Waiting to continue work";
                key = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            }
        }
    }

    public static void choseProduct(Bill currentBill) {

        int key = JOptionPane.YES_OPTION;
        String message;
        Product.printFullInfo(currentBill.getProductList());

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Enter the product code", 0);
            int productCode = Integer.parseInt((stringProductCode == null ? ""+0: stringProductCode));

            if (productCode != 0) {

                Product currentProduct = Product.findByCode(currentBill.getProductList(), productCode);
                message = "Enter the quantity of the product " + currentProduct.name;
                String stringProductQuontity = JOptionPane.showInputDialog(message, 0);
                int productQuontity = Integer.parseInt(stringProductQuontity);

                if (productQuontity != 0 && productCode != 0) {

                   BillController.addProduct(currentBill, productCode, productQuontity);
                }
            }

            message = "Continue to select products?";
            String title = "Waiting to continue work";
            key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);
        }
    }

    public static void printBill(Bill currentBill) {

        System.out.println(BillController.getBillInfoForPrint(currentBill));

        System.out.println(BillController.GetProductsForPrint(currentBill));
    }

    public static void questionForClosingBill(Bill currentBill) {

        String message = "Close check?";
        String title = "Waiting for confirmation to continue";
        int key = JOptionPane.showConfirmDialog( null, message,title, JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            BillController.closeBill(currentBill);
        }
    }
}
