package main.java.ua.artcode.market;

import javax.swing.*;

public class Bill {

   private int code;
   private int[][] products;
   private int quantityGoods;
   private double amountPrice;
   private Seller seller;
   public boolean closed;

   final Product[] productList;

    Bill(int code, int countProducts, String selerName, Product[] productList){

        this.code = code;
        this.products = new int[countProducts][2];
        this.seller = new Seller(selerName);

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
            System.out.println("Достигнут предел количества продуктов в Чеке " + this.products.length + " штук");
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

                this.countAmount();

                productProcessed = true;
            }
        }
    }

    public static void showInfo(Bill currentBill) {
                
        System.out.println("Bill №" + currentBill.code + "/ quontity of goods - " + currentBill.quantityGoods + "/ Amount - " + currentBill.amountPrice + "/  Seller - " + currentBill.seller.name + "/ Closed - " + currentBill.closed);

        Bill.showProducts(currentBill);
    }

    public static void showProducts(Bill currentBill){

        System.out.println("Code\t"+"Goods\t"+"Price\t"+"Quantity");

        for (int i = 0; i < currentBill.products.length; i++) {

            if (currentBill.products[i][0] != 0) {

                Product currentProduct = Product.findByCode(currentBill.productList, currentBill.products[i][0]);
                System.out.println("" + currentProduct.code + "\t\t" + currentProduct.name + "\t\t" + currentProduct.price + "\t\t" + currentBill.products[i][1]);

//                JTextArea myTextArea = new JTextArea();
//                myTextArea.setColumns(20);
//                myTextArea.setRows(5);
//                myTextArea.setText("wefqewwewqr");

            }
        }
    }

    public void countAmount(){

        this.amountPrice = 0;

        for (int i = 0; i < this.products.length; i++) {


            if (this.products[i][0] != 0) {
                this.amountPrice = Math.rint(this.amountPrice + Product.findByCode( this.productList, this.products[i][0]).price*this.products[i][1]*100)/100;
            }
        }
    }

    public void closeBill(){

        int key = JOptionPane.showConfirmDialog( null, "Закрыть чек?","Ожидание продолжения подтверждения", JOptionPane.YES_NO_OPTION);

        if (key == JOptionPane.YES_OPTION){

            this.closed = true;
        }
    }

    public void choseProduct() {

        int key = JOptionPane.YES_OPTION;

        Product.showProductList(this.productList);

        while (key == JOptionPane.YES_OPTION) {

            String stringProductCode = JOptionPane.showInputDialog("Введите код продукта", "Диалог выбора продукта");
            int productCode = Integer.parseInt(stringProductCode);

            String stringProductQuontity = JOptionPane.showInputDialog("Введите количество продукта " + Product.findByCode(this.productList, productCode).name, 0);
            int productQuontity = Integer.parseInt(stringProductQuontity);

            this.addProduct(productCode, productQuontity);

            key = JOptionPane.showConfirmDialog( null, "Продолжим выбор продуктов?","Ожидание продолжения работы", JOptionPane.YES_NO_OPTION);
        }
    }

    public void allProductsSelected() {

        int key = JOptionPane.showConfirmDialog( null, "Продукты выбраны верно?","Ожидание продолжения работы", JOptionPane.YES_NO_OPTION);

        if (key != JOptionPane.YES_OPTION) {

            while (key == JOptionPane.NO_OPTION) {

                String stringProductCode = JOptionPane.showInputDialog("Введите код продукта");
                int productCode = Integer.parseInt(stringProductCode);

                String stringProductQuontity = JOptionPane.showInputDialog("Введите количество продукта " + Product.findByCode(this.productList, productCode).name);
                int productQuontity = Integer.parseInt(stringProductQuontity);

                this.changeProduct(productCode, productQuontity);

                Bill.showInfo(this);

                key = JOptionPane.showConfirmDialog(null, "Все позиции откорректированы?", "Ожидание продолжения работы", JOptionPane.YES_NO_OPTION);
            }
        }
    }
}
