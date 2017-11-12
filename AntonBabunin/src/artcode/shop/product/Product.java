package artcode.shop.product;

import static artcode.shop.creator.methods.Methods.*;

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

    public boolean equals(Product product) {
        return product != null && this.getId() == product.getId() && this.getName().equals(product.getName()) && this.getPrice() == product.getPrice();

    }


}
