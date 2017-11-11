package main.java.market;

public class Product {

    public String name;
    public double price;
    public int id;

    public Product(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
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

    //method showInfo - String
    public static void showInfo(Product product){
        System.out.println("Product name: " + product.name +
                ";\nProduct price: " + product.price +
                ";\nProduct id: "  + product.id + ";");
    }

    public static void printInfo(Product product){
        //System.out.printf("%s \t \t \t \t %.2f \n", product.name, product.price);
        System.out.println(product.name + "\t \t \t \t " + product.price);
    }



}
