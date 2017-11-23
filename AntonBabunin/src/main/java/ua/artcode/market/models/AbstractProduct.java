package ua.artcode.market.models;

public abstract class AbstractProduct {
    private int id;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
