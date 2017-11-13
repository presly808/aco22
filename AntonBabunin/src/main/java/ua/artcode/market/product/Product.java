package ua.artcode.market.product;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class Product {

    private int id;
    private String name;
    private double price;


    public void printFullInfo(){
        System.out.printf("Product: id: %d name: %s price: %.2f \n",
                this.getId(), this.getName(), this.getPrice());
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Product product) {
        return product != null && this.getId() == product.getId() &&
                this.getName().equals(product.getName()) &&
                this.getPrice() == product.getPrice();

    }

    @Override
    public String toString() {
        return  String.format("%d %s %.2f \n", getId(), getName(),getPrice());
    }
}
