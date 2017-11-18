package ua.artcode.market.Controller;

import ua.artcode.market.Interface.IBill;
import ua.artcode.market.Model.Bill;
import ua.artcode.market.Model.Product;
import ua.artcode.market.Model.SalesMan;

import javax.swing.*;
import java.util.Date;

public class BillController implements IBill{

    public void addProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if ((currentBill.getProducts()[i][0] == 0 || currentBill.getProducts()[i][0] == productCode) && !productProcessed) {

                currentBill.setQuantityGoods((currentBill.getProducts()[i][0] == 0 ? (currentBill.getQuantityGoods() + 1) : currentBill.getQuantityGoods()));

                currentBill.setProducts(i,0, productCode);
                currentBill.setProducts(i,1, quantity += currentBill.getProducts()[i][1]);

                currentBill.setAmountPrice(currentBill.getAmountPrice() + Math.rint(Product.findByCode( currentBill.getProductList(), productCode).price*currentBill.getProducts()[i][1]*100)/100);

                productProcessed = true;
            }
        }

        if (!productProcessed) {
            System.out.println("The limit of the number of products in the Check has been reached " +
                    currentBill.getProducts().length + " pieces");
        }
    }

    public void changeProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if (currentBill.getProducts()[i][0] == productCode && !productProcessed) {

                if (quantity == 0) {
                    currentBill.setProducts(i,0, 0);
                    currentBill.setProducts(i,1, 0);
                    currentBill.setQuantityGoods(currentBill.getQuantityGoods() - 1);

                    calculateAmountPrice(currentBill);

                    productProcessed = true;
                }
                else {
                    currentBill.setProducts(i,0, productCode);
                    currentBill.setProducts(i,1, quantity);

                    calculateAmountPrice(currentBill);

                    productProcessed = true;
                }
            }
        }
    }

    public static void printBill(Bill currentBill) {

        System.out.println(getBillInfoForPrint(currentBill));

        System.out.println(GetProductsForPrint(currentBill));
    }

    public static String getBillInfoForPrint(Bill currentBill){

        String message = "Bill №" + currentBill.getCode() +
                "/ quontity of goods - " + currentBill.getQuantityGoods() +
                "/ Amount - " + currentBill.getAmountPrice() +
                "/  SalesMan - " + currentBill.getSalesMan().getFullName() +
                "/ Closed - " + currentBill.closed +
                "/ CloseTime - " + currentBill.closeTime;

        return message;
    }

    public static String GetProductsForPrint(Bill currentBill){

        String message = "Code\t"+"Goods\t"+"Price\t"+"Quantity\n";

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if (currentBill.getProducts()[i][0] != 0) {

                Product currentProduct = Product.findByCode(currentBill.getProductList(), currentBill.getProducts()[i][0]);
                message += "" + currentProduct.code +
                        "\t\t" + currentProduct.name +
                        "\t\t" + currentProduct.price +
                        "\t\t" + currentBill.getProducts()[i][1]+"\n";
            }
        }

        return message;
    }

    public void calculateAmountPrice(Bill currentBill){

        currentBill.setAmountPrice(0);

        for (int i = 0; i < currentBill.getProducts().length; i++) {


            if (currentBill.getProducts()[i][0] != 0) {
                Product currentProduct = Product.findByCode(currentBill.getProductList(), currentBill.getProducts()[i][0]);
                currentBill.setAmountPrice(Math.rint(currentBill.getAmountPrice() + currentProduct.price*currentBill.getProducts()[i][1]*100)/100);
            }
        }
    }

    @Override
    public void questionForClosingBill(Bill currentBill) {

        String message = "Close check?";
        String title = "Waiting for confirmation to continue";
        int key = JOptionPane.showConfirmDialog( null, message,title, JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            closeBill(currentBill);
        }
    }

    public void closeBill(Bill currentBill){

        currentBill.closed = true;
        currentBill.closeTime = new Date();
    }

    @Override
    public void choseProduct(Bill currentBill) {

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

                    addProduct(currentBill, productCode, productQuontity);
                }
            }

            message = "Continue to select products?";
            String title = "Waiting to continue work";
            key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);
        }
    }

    @Override
    public void allProductsSelected(Bill currentBill) {

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

                        changeProduct(currentBill, productCode, productQuontity);
                    }

                    printBill(currentBill);
                }
                message = "All positions are corrected?";
                title = "Waiting to continue work";
                key = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            }
        }
    }
}
