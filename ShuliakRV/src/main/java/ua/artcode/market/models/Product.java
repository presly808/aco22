package ua.artcode.market.models;

public class Product {
    private static int seqId;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        seqId++;
        id = seqId;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Продукт№ %s : Name: %s, Price: %.2f; %n",
                id, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

}

