package week1.data;

public class Seller {

    private String name;

    private String login;
    private String password;

    private int age;

    private int soldProducts;

    public Seller() {
    }

    public Seller(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    @Override
    public String toString() {
        return "Seller: " + name +", age: " + age;
    }
}