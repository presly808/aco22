package week1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {

    private List<Product> productList;

    private int id;

    private double amountPrice;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    private boolean isClosed;

    private Seller seller;

    private int nextProductId;

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
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public int getNextProductId() {
        return nextProductId;
    }

    public void setNextProductId(int nextProductId) {
        this.nextProductId = nextProductId;
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
