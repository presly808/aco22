package ua.artcode.market.creator;


import ua.artcode.market.product.Product;

public class ProductGeneration {
    private static final int DEFAULT_PRODUCT_NAME = 30;

    public static Product generateProduct(){
        int id = generateId();
        String name = generateName();
        double price = generatePrice();

        return new Product(id, name, price);
    }

    private static double generatePrice() {
        return Math.random()*1000;
    }

    private static String generateName() {
        char[] nameChar = new char[DEFAULT_PRODUCT_NAME];
        for (int i = 0; i < DEFAULT_PRODUCT_NAME; i++) {
            nameChar[i] = (char) ((int)(65+(Math.random()*26)));
        }
        return String.valueOf(nameChar);

    }

    private static int generateId() {
        return (int) (Math.random()*100000);
    }
}
