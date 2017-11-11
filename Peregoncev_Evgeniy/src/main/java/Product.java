

/**
 * Created by ENIAC on 05.11.2017.
 */
public class Product {

    private String name;
    private double price;
    private String barcode;

    public Product(String name, double price, String barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }


    public String printFullInfo() {

        return String.format("name %s price %f barcode %s barcode", name, price, barcode);
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
