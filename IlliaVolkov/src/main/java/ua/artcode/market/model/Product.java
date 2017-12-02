package ua.artcode.market.model;

import java.util.List;

public class Product {

    public int code;
    public String name;
    public double price;

    public Product(int code, String name, double price){

        this.code = code;
        this.name = name;
        this.price = price;

    }

    public static void printFullInfo(List<Product> productsPrice){

        System.out.println("\n" + fulInfoOfProdukts(productsPrice));
    }

    public static String fulInfoOfProdukts(List<Product> productsPrice) {

        String textMessage = "";

        textMessage += "\nPRICE OF GOODS\n" + "Code\t\t"+"Goods\t\t"+"Price";

        for (Product itemProduct: productsPrice) {

            textMessage += "\n" + itemProduct.code +
                    "\t\t" + itemProduct.name +
                    "\t\t" + itemProduct.price;
        }

        return textMessage+"\n";
    }
}



