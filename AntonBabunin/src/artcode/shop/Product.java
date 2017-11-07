package artcode.shop;

public class Product {

    private int id;
    private String name;
    private double price;


    public void printFullInfo(){
        System.out.printf("Product: \n id: %d \n name: %s \n price: %.2f", this.getId(), this.getName(), this.getPrice());
    }




    public Product(int id) {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}
