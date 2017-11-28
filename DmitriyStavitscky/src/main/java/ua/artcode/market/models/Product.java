package ua.artcode.market.models;

public class Product {

    private String name;
    private double price;
    private int id;

    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Product other = (Product) obj;

        return (name != null && name.equals(other.name)) &&
                id == other.id &&
                price == other.price;
    }

    @Override
    public String toString() {
        return String.format("name: %s, id: %d, price: %.2f", name, id, price);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

}


