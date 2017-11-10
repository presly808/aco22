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

    public String printFullInfo() {
        return String.format("Name: %s, Price: %.2f, ID: %d; %n",
                name, price, id);

    }
}

