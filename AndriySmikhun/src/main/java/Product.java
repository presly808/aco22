public class Product {

    int id;
    String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }

    public void printFullInfo(){
        System.out.println(" ID " + id + " Name " + name);
    }

}
