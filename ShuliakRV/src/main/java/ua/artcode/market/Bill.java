package ua.artcode.market;

import java.util.Date;

public class Bill {

    private static final int DEFAULT_AMOUNT_PRODUCTS = 100;

    private static int seqId;
    private int id;
    private Product[] products;
    private Salesman salesMan;
    private double amountPrice;
    private Date closeTime;
    private boolean isOpen = true;
    private int numProd;

    public Bill(Salesman salesMan) {
        seqId++;
        id = seqId;
        this.salesMan = salesMan;
        products = new Product[DEFAULT_AMOUNT_PRODUCTS];
    }

    public Bill(Salesman salesMan, int amountProd) {
        seqId++;
        id = seqId;
        this.salesMan = salesMan;
        products = new Product[amountProd];
    }

    public int getId() {
        return id;
    }

    public int getNumProd() {
        return numProd;
    }

    public Salesman getSalesMan() {
        return salesMan;
    }

    public double getAmountPrice() {
        return amountPrice;
    }


    public boolean addProduct(Product p) {

        if ((isOpen) && (p != null) && (numProd < products.length)) {

            products[numProd++] = p;

            return true;
        }
        return false;
    }

    public void printBill() {

        if (!isOpen) {

            String str = "Чек№" + id + "\n";

            for (int i = 0; i < numProd; i++) {
                str += products[i].printFullInfo();
            }

            str += String.format("Saler: %s; Time: %s; Sum: %.2f .",
                    salesMan.getFullname(), closeTime.toString(), amountPrice);

            System.out.println(str);
        } else System.out.println("Чек не закрыт!");
    }

    public void calculateAmountPrice() {

        amountPrice = 0;

        for (int i = 0; i < numProd; i++) {
            amountPrice += products[i].getPrice();
        }
    }

    public boolean closeBill() {

        if (numProd > 0) {

            calculateAmountPrice();
            closeTime = new Date();
            isOpen = false;

            return true;

        }

        return false;
    }
}


