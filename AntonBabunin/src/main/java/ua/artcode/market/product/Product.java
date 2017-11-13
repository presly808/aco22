package ua.artcode.market.product;


public class Product {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
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
    public boolean equals(Object object) {
        return object != null && object instanceof Product &&
                this.getId() == ((Product) object).getId() &&
                this.getName().equals(((Product) object).getName()) &&
                this.getPrice() == ((Product) object).getPrice();
    }

    @Override
    public String toString() {
        return  String.format("Product: id: %d name: %s price: %.2f \n",
                this.getId(), this.getName(), this.getPrice());
    }
}
