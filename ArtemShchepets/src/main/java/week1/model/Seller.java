package week1.model;

public class Seller {

    private String name;

    private String login;
    private String password;

    private int age;

    private int soldProducts;

    public Seller() {
    }

    public Seller(String name, int age, String login, String password) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
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
        return "Seller: " + name + ", age: " + age + ", sold: " + soldProducts;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || obj.getClass() != Seller.class) return false;

        Seller other = (Seller) obj;

        if ((name != null && name.equals(other.getName())) &&
                (login != null && login.equals(other.getLogin())) &&
                (password != null && password.equals(other.getPassword())) &&
                age == other.getAge() &&
                soldProducts == other.getSoldProducts()) return true;

        return false;
    }
}
