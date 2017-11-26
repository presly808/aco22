package week1.models;

public class Product implements Comparable<Product>{

    private int id;

    private String name;
    private double price;
    private String code;

    public Product(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public String showInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Product \"%s\", price: %.2f, barcode: %s", name, price, code);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || obj.getClass() != Product.class) return false;

        Product other = (Product) obj;

        if (id == other.getId() &&
                (name !=null && name.equals(other.getName())) &&
                (code != null && code.equals(other.getCode()))) return true;

        return false;
    }

    @Override
    public int compareTo(Product o) {
        return this.id - o.getId();
    }
}
