package week1.model;

public class Product implements Comparable {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public void printFullInfo() {
        System.out.println(" ID " + id + " Name " + name + "Price " + price);
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
    public String toString() {
        return "Name " + name + " Price " + price;
    }

    @Override
    public int compareTo(Object o) {
        Product prod = (Product) o;
        int res = this.name.compareTo(prod.name);
        if (res != 0) {
            return res / Math.abs(res);
        }

        if (this.price == prod.price) {
            return 0;
        } else if (this.price < prod.price) {
            return -1;
        } else return 1;


    }
}
