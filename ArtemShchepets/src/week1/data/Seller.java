package week1.data;

public class Seller {

    private String name;
    private int age;

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

    @Override
    public String toString() {
        return "Seller: " + name +", age: " + age;
    }
}
