package week1.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ENIAC on 05.11.2017.
 */
public class Bill {

    private List<Product> productList;

    private double AmountPrice;
    private Salesman salesman;
    private Time time;

    private int id = 0;

    private boolean isclosed = false;


    private int productsCounter = 0;

    public Bill() {

    }

    public Bill(double amountPrice, Salesman salesman, Time time, int id) {

        this.productList = new ArrayList<>();
        this.AmountPrice = amountPrice;
        this.salesman = salesman;
        this.time = time;
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public int getProductsCounter() {
        return productsCounter;
    }

    public void setProductsCounter(int productsCounter) {
        this.productsCounter = productsCounter;
    }

    public double getAmountPrice() {
        return AmountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        AmountPrice = amountPrice;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public boolean isClosed() {
        return isclosed;
    }


//    Methods

//    public double calculateAmountPrice(Bill bill) {
//        if ((bill == null) || (productsCounter == 0)) {
//            System.out.println("bill is null or there no one product to calc");
//        } else
//            for (int i = 0; i < productsCounter; i++) {
//                AmountPrice += products[i].getPrice();
//            }
//        return AmountPrice;
//    }

//    public String printAllProducts() {
//        String allproducts = "";
//        if (productsCounter == 0) {
//            System.out.println("no product to print");
//        } else
//            for (int i = 0; i < productsCounter; i++) {
//                allproducts += products[i].toString() + "\n";
//            }
//        return allproducts;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("              BiLL  \n" +
//                        "Title           Price    Barcode\n" +
//                        "--------------------------------\n" +
//                        "%s\nAmount Price = " + getAmountPrice() + "\nSeller: %s\n%s",
//                printAllProducts(), salesman.getFullname(), getTime().getCloseTime());
//    }


}

