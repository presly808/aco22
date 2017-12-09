package week1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    public double getAmountPrice() {
        return calculateAmountPrice();
    }

    private double calculateAmountPrice() {

        double amountPrice = 0;

        for (int i = 0; i < productList.size(); i++) {
            amountPrice += productList.get(i).getPrice();
        }

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

    public static class AmountPriceComparator implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return (int) (o1.getAmountPrice() - o2.getAmountPrice());
        }
    }

    public static class CreationDateComparator implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getOpenTime().compareTo(o2.getOpenTime());
        }
    }

}
