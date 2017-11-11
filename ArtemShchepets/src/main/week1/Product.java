package week1;

public class Product {

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

    public void setId(int id) {
        this.id = id;
    }

    public String showInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Product \"%s\", price: %.2f, barcode: %s", name, price, code);
    }


}
