package ua.artcode.market.interfaces;

import ua.artcode.market.model.Product;

import java.util.List;

public interface IStatistics {

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
