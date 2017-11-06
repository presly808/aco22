package hw1;

public class Product {

    private int id;
    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        return name.equals(product.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ' ' + name + '}';
    }

    public void printFullInfo(){
        System.out.println(toString());
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }
}
