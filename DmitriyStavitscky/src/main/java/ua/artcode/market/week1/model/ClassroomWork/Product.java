package main.java.ua.artcode.market.week1.model.ClassroomWork;

public class Product {

    private String name;
    private double price;
    private int barcode;


    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.barcode = id;
    }

    public String toStringProduct() {

        return String.format("name: %s, price: %.2f, barcode: %d",
                name, price, barcode);
    }


    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }

}
