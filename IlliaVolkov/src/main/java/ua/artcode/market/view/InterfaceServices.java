package ua.artcode.market.view;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class InterfaceServices {

    public static int questionLogOutSalesMan(){
        return JOptionPane.showConfirmDialog( null, "Do you want to log out?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }

    public static void allProductsSelected(Bill currentBill) throws IOException {

        AppDBImpl appDB = AppDBImpl.getEntity();

        String message = "Are the products selected correctly?";
        String title = "Waiting to continue work";
        int key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);

        if (key != JOptionPane.YES_OPTION) {

            while (key == JOptionPane.NO_OPTION) {

                String stringProductCode = JOptionPane.showInputDialog("Enter the product code");
                int productCode = Integer.parseInt(stringProductCode);

                if (productCode != 0) {

                    message = "Enter the quantity of the product " +
                    appDB.findProductByCode(productCode).name;
                    String stringProductQuontity = JOptionPane.showInputDialog(message);
                    int productQuontity = Integer.parseInt(stringProductQuontity);

                    if (productQuontity == 0) {

                        appDB.changeProductToBill(currentBill, productCode, productQuontity);
                    }

                   appDB.statistics.printBill(currentBill);
                }
                message = "All positions are corrected?";
                title = "Waiting to continue work";
                key = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            }
        }
    }

    public static void choseProduct(Bill currentBill) throws IOException {

        AppDBImpl appDB = AppDBImpl.getEntity();

        List<Product> productsPrice = appDB.getProductsPrice();

        int key = JOptionPane.YES_OPTION;
        String message;
        appDB.statistics.printPriceOfProducts(productsPrice);

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Enter the product code", 0);
            int productCode = Integer.parseInt((stringProductCode == null ? ""+0: stringProductCode));

            if (productCode != 0) {

                Product currentProduct = AppDBImpl.getEntity().findProductByCode(productCode);
                message = "Enter the quantity of the product " + currentProduct.name;
                String stringProductQuontity = JOptionPane.showInputDialog(message, 0);
                int productQuontity = Integer.parseInt(stringProductQuontity);

                if (productQuontity != 0) {

                   appDB.addProductToBill(currentBill, productCode, productQuontity);
                }
            }

            message = "Continue to select products?";
            String title = "Waiting to continue work";
            key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);
        }
    }

    public static void questionForClosingBill(Bill currentBill) throws IOException {

        String message = "Close check?";
        String title = "Waiting for confirmation to continue";
        int key = JOptionPane.showConfirmDialog( null, message,title, JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            AppDBImpl.getEntity().billController.closeBill(currentBill);
        }
    }

    public static int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Create a new check?",
                "Waiting to continue work", JOptionPane.YES_NO_OPTION);
    }

    public static SalesMan autorizationSalesMan() throws IOException {

        int key = JOptionPane.YES_OPTION;

        while (key == JOptionPane.YES_OPTION) {

            String message = "Enter the SalesMan <LOGIN> ";
            String loginSalesMan = JOptionPane.showInputDialog(message);

            message = "Enter the SalesMan <PASSWORD> ";
            String passSalesMan = JOptionPane.showInputDialog(message);

            SalesMan currentSalesMan = AppDBImpl.getEntity().findSalesMan(loginSalesMan, passSalesMan);

            if (currentSalesMan == null) {
                message = "Do you want to try again?";
                String title = "Autorization of the SalesMan";
                key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);

                if (key == JOptionPane.NO_OPTION) {
                    return null;
                }
            }
            else {
                return currentSalesMan;
            }
        }

        return null;
    }
}
