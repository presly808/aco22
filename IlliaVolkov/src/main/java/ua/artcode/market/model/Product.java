package ua.artcode.market.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    public int code;
    public String name;
    public double price;

    Product(int code, String name, double price){

        this.code = code;
        this.name = name;
        this.price = price;

    }

    public static Product createProduct(int code, String name, double price){

        Product product = new Product(code, name, price);

        return product;

    }

    public static List<Product> initProductsPrice(int countProducts){

        List<Product> productsPrice =new  ArrayList<Product>();

        for (int i = 0; i < countProducts; i++) {
            productsPrice.add(Product.createProduct(i+1,
                    "Goods"+(i+1),
                    Math.rint((Math.random()*10)*100)/100));
        }

        return productsPrice;
    }

    public static Product findByCode(List<Product> productsPrice, int productCode) {

        for (Product itemProduct: productsPrice) {

            if (itemProduct.code == productCode) {
                return itemProduct;
            }
        }

        System.out.println("It is not possible to identify the product by code <<" + productCode + ">>");


        return new Product(0,"", 0);
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



