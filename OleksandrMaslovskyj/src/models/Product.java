package models;


public class Product {

    private long id;
    private String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void printFullInfo(){
        System.out.println("id: " + getId() + "; name: " + getName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
