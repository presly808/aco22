package week1.models;

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

    private boolean isClosed = false;


    public Bill() {
        this.productList = new ArrayList<>();
        this.time = new Time();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Time getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClosed(boolean closed) {
        this.isClosed = closed;
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
        return isClosed;
    }


    @Override
    public String toString() {
        return "Bill{" +
                "productList=" + productList +
                ", AmountPrice=" + AmountPrice +
                ", salesman=" + salesman +
                ", time=" + time +
                ", id=" + id +
                ", isClosed=" + isClosed +
                '}';
    }
}




