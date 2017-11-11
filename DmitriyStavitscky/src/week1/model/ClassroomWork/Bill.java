package week1.model.ClassroomWork;

public class Bill {

    private Product[] products;
    private double amountPrice;
    private String saler;
    private String productNames = "";

    private SellDate sellDate;

    public Bill(Product[] allProducts, String saler) {
        this.products = allProducts;
        this.saler = saler;
    }

    public void addProduct(int code) {
        for (Product product : products) {
            if (code == product.getBarcode()) {
                amountPrice += product.getPrice();
                productNames += product.getName() + ", ";
            }
        }
    }

    public String convertBill() {
        return String.format("products: %s, amount price: %.2f, saler: %s, date: ",
                productNames, amountPrice, saler);
    }

}
