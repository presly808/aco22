package artcode.shop;

import javax.swing.*;

public class Product {
    private String name;
    private double price;
    private int id;

    public Product(String name, double price, int id) {
        this.name = nameProductTester(name);
        this.price = priceProductTester(price);
        this.id = idProductTester(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static String nameProductTester (String name) {
        return  (name == null) ? "" : name;
    }

    private static double priceProductTester (double price) {
        return (price > 0.0) ? price : 0.0;
    }

    private static int idProductTester (int id) {
        return (id > 0) ? id : 0;
    }

    public static Product setProductNull(Product product) {
        product.setName("");
        product.setPrice(0.0);
        product.setId(0);
        return product;
    }

}
