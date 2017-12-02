package ua.artcode.market.view;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;

import javax.swing.*;
import java.util.List;

public class InterfaceServices {

    public static int questionLogOutSalesMan(){
        return JOptionPane.showConfirmDialog( null, "Do you want to log out?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
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

                    message = "Enter the quantity of the product " +
                            currentBill.terminal.currentAppDBImpl.findProductByCode(productCode).name;
                    String stringProductQuontity = JOptionPane.showInputDialog(message);
                    int productQuontity = Integer.parseInt(stringProductQuontity);

                    if (productQuontity == 0 || productCode == 0) {

                        BillController.changeProductToBill(currentBill, productCode, productQuontity);
                    }

                   BillController.printBill(currentBill);
                }
                message = "All positions are corrected?";
                title = "Waiting to continue work";
                key = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            }
        }
    }

    public static void choseProduct(Bill currentBill) {

        List<Product> productsPrice = currentBill.terminal.currentAppDBImpl.getProductsPrice();

        int key = JOptionPane.YES_OPTION;
        String message;
        Product.printFullInfo(productsPrice);

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Enter the product code", 0);
            int productCode = Integer.parseInt((stringProductCode == null ? ""+0: stringProductCode));

            if (productCode != 0) {

                Product currentProduct = currentBill.terminal.currentAppDBImpl.findProductByCode(productCode);
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

    public static void questionForClosingBill(Bill currentBill) {

        String message = "Close check?";
        String title = "Waiting for confirmation to continue";
        int key = JOptionPane.showConfirmDialog( null, message,title, JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            BillController.closeBill(currentBill);
        }
    }

    public static int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Create a new check?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }

}
