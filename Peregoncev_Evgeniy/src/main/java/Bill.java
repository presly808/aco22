package main.java;

/**
 * Created by ENIAC on 05.11.2017.
 */
public class Bill {

    private Product[] products;
    private Double AmountPrice;
    private Salesman salesman;
    private String closeTime;

    public Bill(Product[] products, Double amountPrice, String saller, String time) {
        this.products = products;
        AmountPrice = amountPrice;
//        salesman = salesman.getFullname();
        closeTime = closeTime;
    }


    public int counter = 0;

    public void add(Product pr) {
        products[counter] = pr;
        counter++;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Double getAmountPrice() {
        return AmountPrice;
    }

    public void setAmountPrice(Double amountPrice) {
        AmountPrice = amountPrice;
    }

    public String getTime() {
        return closeTime;
    }

    public void setTime(String time) {
        closeTime = time;
    }

//    public String makeBill() {
//
//        return String.format("продукты %s, AmountPrice %d, Saller %s, Time %s",
//                products, AmountPrice, Saller, Time);
//    }

}
