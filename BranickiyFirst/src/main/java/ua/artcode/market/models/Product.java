package src.main.java.ua.artcode.market.models;

import java.util.Objects;

public class Product {

    private String name;
    private double price;
    private int id;

    public Product(String name, double price,int id ){

        this.name = name;
        this.id = id;
        this.price = price;

    }

    @Override
    public boolean equals(Object obj){

        if(obj == this) return true;

        if (obj==null || obj.getClass() != getClass()) return false;

        Product other = (Product) obj;

        return (name != null && name.equals(other.name)) && id == other.id
        && price == other.price ;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
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
}
