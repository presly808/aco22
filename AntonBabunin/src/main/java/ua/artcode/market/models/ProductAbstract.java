package ua.artcode.market.models;

import ua.artcode.market.utils.Utils;

public abstract class ProductAbstract {

    private int id;
    private String name;
    private double price;

    public ProductAbstract() {
        this.id = Utils.generateID();
        this.name = "RandomProduct";
        this.price = 0.1;
    }

    public ProductAbstract(int id, String name, double price) {
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

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract String toString();
}
