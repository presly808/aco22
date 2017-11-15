package ua.artcode.market;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import javax.swing.*;
import java.util.Date;

public class Bill {

    private int code;
    private int[][] products;
    private int quantityGoods;
    private double amountPrice;
    private SalesMan salesMan;
    public boolean closed;
    public Date closeTime;

    final Product[] productList;

    Bill(int code, int countProducts, String salesManName, Product[] productList){

        this.code = code;
        this.products = new int[countProducts][2];
        this.salesMan = new SalesMan(salesManName);

        this.productList = productList;

    }

    public void addProduct(int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < this.products.length; i++) {

            if ((this.products[i][0] == 0 || this.products[i][0] == productCode) && !productProcessed) {

                this.quantityGoods = (this.products[i][0] == 0 ? (this.quantityGoods + 1) : this.quantityGoods);

                this.products[i][0] = productCode;
                this.products[i][1] += quantity;

                this.amountPrice += Math.rint(Product.findByCode( this.productList, productCode).price*this.products[i][1]*100)/100;

                productProcessed = true;
            }
        }

        if (!productProcessed) {
            System.out.println("The limit of the number of products in the Check has been reached " +
                                this.products.length + " pieces");
        }
    }

    public void changeProduct(int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < this.products.length; i++) {

            if (this.products[i][0] == productCode && !productProcessed) {

                if (quantity == 0) {
                    this.products[i][0] = 0;
                    this.products[i][1] = 0;
                    this.quantityGoods = this.quantityGoods - 1;
                }
                else {
                    this.products[i][0] = productCode;
                    this.products[i][1] = quantity;
                }

                this.calculateAmountPrice();

                productProcessed = true;
            }
        }
    }

    public static void printBill(Bill currentBill) {

        System.out.println("Bill №" + currentBill.code +
                            "/ quontity of goods - " + currentBill.quantityGoods +
                            "/ Amount - " + currentBill.amountPrice +
                            "/  SalesMan - " + currentBill.salesMan.fullName +
                            "/ Closed - " + currentBill.closed +
                            "/ CloseTime - " + currentBill.closeTime);

        Bill.showProducts(currentBill);
    }

    public static void showProducts(Bill currentBill){

        System.out.println("Code\t"+"Goods\t"+"Price\t"+"Quantity");

        for (int i = 0; i < currentBill.products.length; i++) {

            if (currentBill.products[i][0] != 0) {

                Product currentProduct = Product.findByCode(currentBill.productList, currentBill.products[i][0]);
                System.out.println("" + currentProduct.code +
                                    "\t\t" + currentProduct.name +
                                    "\t\t" + currentProduct.price +
                                    "\t\t" + currentBill.products[i][1]);
            }
        }
    }

    public void calculateAmountPrice(){

        this.amountPrice = 0;

        for (int i = 0; i < this.products.length; i++) {


            if (this.products[i][0] != 0) {
                Product currentProduct = Product.findByCode( this.productList, this.products[i][0]);
                this.amountPrice = Math.rint(this.amountPrice + currentProduct.price*this.products[i][1]*100)/100;
            }
        }
    }

    public void closeBill(){

        String message = "Close check?";
        String title = "Waiting for confirmation to continue";
        int key = JOptionPane.showConfirmDialog( null, message,title, JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            this.closed = true;
            this.closeTime = new Date();
        }
    }

    public void choseProduct() {

        int key = JOptionPane.YES_OPTION;

        Product.printFullInfo(this.productList);

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Enter the product code", 0);
            int productCode = Integer.parseInt((stringProductCode == null ? ""+0: stringProductCode));

            Product currentProduct = Product.findByCode(this.productList, productCode);
            String message = "Enter the quantity of the product " + currentProduct.name;
            String stringProductQuontity = JOptionPane.showInputDialog(message, 0);
            int productQuontity = Integer.parseInt(stringProductQuontity);

            if (productQuontity != 0 && productCode != 0) {

                this.addProduct(productCode, productQuontity);
            }

            message = "Continue to select products?";
            String title = "Waiting to continue work";
            key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);
        }
    }

    public void allProductsSelected() {

        String message = "Are the products selected correctly?";
        String title = "Waiting to continue work";
        int key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);

        if (key != JOptionPane.YES_OPTION) {

            while (key == JOptionPane.NO_OPTION) {

                String stringProductCode = JOptionPane.showInputDialog("Enter the product code");
                int productCode = Integer.parseInt(stringProductCode);

                message = "Enter the quantity of the product " + Product.findByCode(this.productList, productCode).name;
                String stringProductQuontity = JOptionPane.showInputDialog(message);
                int productQuontity = Integer.parseInt(stringProductQuontity);

                if (productQuontity == 0 || productCode == 0) {

                    this.changeProduct(productCode, productQuontity);
                }

                Bill.printBill(this);

                message = "All positions are corrected?";
                title = "Waiting to continue work";
                key = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            }
        }
    }
}
