public class Product {
    int id;
    String name;
    double price;

    public String printFullInfo() {
        return String.format("Name: %s, Price: %.2f, ID: %d; %n",
                name, price, id);

    }
}

