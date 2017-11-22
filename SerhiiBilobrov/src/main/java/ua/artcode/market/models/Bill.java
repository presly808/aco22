package ua.artcode.market.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 19.11.17.
 */
public class Bill {

    private int id;
    private List<Product> productList;
    private double amountPrice;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private boolean closed;

    public Bill() {
        this.productList = new ArrayList<>();
        this.openTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", productList=" + productList +
                ", amountPrice=" + amountPrice +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
