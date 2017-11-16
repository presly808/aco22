package ua.artcode.market.model;

public class Product {

    private String name;
    private int id;
    private double price;

    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if(obj == null || obj.getClass() != getClass()) {
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


