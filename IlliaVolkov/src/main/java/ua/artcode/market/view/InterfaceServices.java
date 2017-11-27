package ua.artcode.market.view;

import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;

import javax.swing.*;
import java.util.List;

public class InterfaceServices {

    public static void runTerminal() {

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");


        List<SalesMan> salesMans = SalesMan.initSalesMans(2);

        SalesMan autorizedSalesMan = InterfaceServices.autorizationSalesMan(salesMans);

        if (autorizedSalesMan != null) {

            BillController billController = new BillController();
            Terminal currentTerminal = new Terminal(billController);

            String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
            int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

            List<Product> productsPrice = Product.initProductsPrice(countProducts);

            int key = InterfaceServices.questionCreateBill();

            while (key == JOptionPane.YES_OPTION) {

                Bill currentBill = currentTerminal.createBill(autorizedSalesMan, productsPrice);

                while (!currentBill.closed) {

                    billController.choseProduct(currentBill);

                    InterfaceServices.printBill(currentBill);

                    billController.allProductsSelected(currentBill);

                    billController.questionForClosingBill(currentBill);

                    currentTerminal.saveBill(currentBill);

                }

                int keyBill = InterfaceServices.questionCreateBill();

                if (keyBill == JOptionPane.NO_OPTION) {
                    int keyLogOut = InterfaceServices.questionLogOutSalesMan();

                    if (keyLogOut == JOptionPane.YES_OPTION) {

                        autorizedSalesMan = null;

                        Terminal.showInfo(currentTerminal);

                        key = keyBill;
                    }
                } else {
                    key = keyBill;
                }
            }
        }
    }

    public static SalesMan autorizationSalesMan(List<SalesMan> salesMans) {

        int key = JOptionPane.YES_OPTION;

        while (key == JOptionPane.YES_OPTION) {

            String message = "Enter the SalesMan <LOGIN> ";
            String loginSalesMan = JOptionPane.showInputDialog(message);

            message = "Enter the SalesMan <PASSWORD> ";
            String passSalesMan = JOptionPane.showInputDialog(message);

            SalesMan currentSalesMan = SalesMan.findSalesMan(salesMans, loginSalesMan, passSalesMan);

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
                            Product.findByCode(currentBill.getProductsPrice(), productCode).name;
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
        Product.printFullInfo(currentBill.getProductsPrice());

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Enter the product code", 0);
            int productCode = Integer.parseInt((stringProductCode == null ? ""+0: stringProductCode));

            if (productCode != 0) {

                Product currentProduct = Product.findByCode(currentBill.getProductsPrice(), productCode);
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

        System.out.println(BillController.getBillHeadInfoForPrint(currentBill));
        System.out.println(BillController.GetBillProductsForPrint(currentBill));
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
