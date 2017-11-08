package artcode.shop.product;

import static artcode.shop.otherMethods.Methods.*;

public class Product {

    private int id;
    private String name;
    private double price;


    public void printFullInfo(){
        System.out.printf("Product: \n id: %d \n name: %s \n price: %.2f", this.getId(), this.getName(), this.getPrice());
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = round(price, 2);
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

}
