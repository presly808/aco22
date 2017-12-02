package main.java.ua.artcode.market.models;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bill {

    private List<Product> products = new ArrayList<>();
    private int productsCount;
    private int id;
    private double amountPrice;

    private SalesMan salesMan;

    private LocalDateTime openTime;

    private Time closeTime;

    public Bill(SalesMan salesMan, int idOfBill) {
        this.salesMan = salesMan;
        this.id = idOfBill;
        this.openTime = LocalDateTime.now();
    }

    public void calculateAmountPrice() {

        for (Product product : products) {
            amountPrice += product.getPrice();
        }
    }

    public void closeBill(int hours, int minutes, int seconds) {

        closeTime = new LocalDateTime.now();
        salesMan.setAmountOfAllSales(salesMan.getAmountOfAllSales() + amountPrice);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "products=" + products +
                ", productsCount=" + productsCount +
                ", id=" + id +
                ", amountPrice=" + amountPrice +
                ", salesMan=" + salesMan +
                ", closeTime=" + closeTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Bill other = (Bill) obj;

        return (products != null && products.equals(other.products)) &&
                (productsCount == other.productsCount) &&
                id == other.id &&
                amountPrice == other.amountPrice &&
                salesMan.equals(other.salesMan) &&
                closeTime.equals(other.closeTime);

    }

    // if this < object -> -
    // if this > object -> +

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount += productsCount;
    }

    public int getId() {
        return id;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public SalesMan getSalesMan() {
        return salesMan;
    }

    @Override
    public int compareTo(Bill o) {
        double res = amountPrice - o.getAmountPrice();

        return res > 0 ? 1 :
                res < 0 ? -1 : 0;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }
}

class BillIdComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {

        return o1.getId() - o2.getId();
    }
}

class BillProductsCountComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getProducts().size() - o2.getProducts().size();
    }
}

class BillAmountPriceComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {

        double res = o1.getAmountPrice() - o2.getAmountPrice();

        return res > 0 ? 1 :
                res < 0 ? -1 : 0;
    }
}

class BillSalesmanComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getSalesMan().getFullName().compareTo
                (o2.getSalesMan().getFullName());
    }
}

class BillTimeComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getCloseTime().compareTo(o2.getCloseTime());
    }


}
