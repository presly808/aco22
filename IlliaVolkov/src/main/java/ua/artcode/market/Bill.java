package ua.artcode.market;

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
            System.out.println("The limit of the number of products in the Check has been reached " + this.products.length + " pieces");
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

        System.out.println("Bill №" + currentBill.code + "/ quontity of goods - " + currentBill.quantityGoods + "/ Amount - " + currentBill.amountPrice + "/  SalesMan - " + currentBill.salesMan.fullName + "/ Closed - " + currentBill.closed + "/ CloseTime - " + currentBill.closeTime);

        Bill.showProducts(currentBill);
    }

    public static void showProducts(Bill currentBill){

        System.out.println("Code\t"+"Goods\t"+"Price\t"+"Quantity");

        for (int i = 0; i < currentBill.products.length; i++) {

            if (currentBill.products[i][0] != 0) {

                Product currentProduct = Product.findByCode(currentBill.productList, currentBill.products[i][0]);
                System.out.println("" + currentProduct.code + "\t\t" + currentProduct.name + "\t\t" + currentProduct.price + "\t\t" + currentBill.products[i][1]);
            }
        }
    }

    public void calculateAmountPrice(){

        this.amountPrice = 0;

        for (int i = 0; i < this.products.length; i++) {


            if (this.products[i][0] != 0) {
                this.amountPrice = Math.rint(this.amountPrice + Product.findByCode( this.productList, this.products[i][0]).price*this.products[i][1]*100)/100;
            }
        }
    }

    public void closeBill(){

        int key = JOptionPane.showConfirmDialog( null, "Close check?","Waiting for confirmation to continue", JOptionPane.YES_NO_OPTION);

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

            String stringProductQuontity = JOptionPane.showInputDialog("Enter the quantity of the product " + Product.findByCode(this.productList, productCode).name, 0);
            int productQuontity = Integer.parseInt(stringProductQuontity);

            if (productQuontity == 0 || productCode == 0) {

                this.addProduct(productCode, productQuontity);
            }

            key = JOptionPane.showConfirmDialog( null, "Continue to select products?","Waiting to continue work", JOptionPane.YES_NO_OPTION);
        }
    }

    public void allProductsSelected() {

        int key = JOptionPane.showConfirmDialog( null, "Are the products selected correctly?","Waiting to continue work", JOptionPane.YES_NO_OPTION);

        if (key != JOptionPane.YES_OPTION) {

            while (key == JOptionPane.NO_OPTION) {

                String stringProductCode = JOptionPane.showInputDialog("Enter the product code");
                int productCode = Integer.parseInt(stringProductCode);

                String stringProductQuontity = JOptionPane.showInputDialog("Enter the quantity of the product " + Product.findByCode(this.productList, productCode).name);
                int productQuontity = Integer.parseInt(stringProductQuontity);

                if (productQuontity == 0 || productCode == 0) {

                    this.changeProduct(productCode, productQuontity);
                }

                Bill.printBill(this);

                key = JOptionPane.showConfirmDialog(null, "All positions are corrected?", "Waiting to continue work", JOptionPane.YES_NO_OPTION);
            }
        }
    }
}
