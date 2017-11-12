package models;

import Utils.TerminalUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Comparable<Bill>{

    private long id;
    private List<Product> products;
    private Salesman salesman;
    private double amountPrice;
    private String closeTime;
    private long creationDate;

    public Bill() {
        creationDate = System.currentTimeMillis();
        id = TerminalUtils.longIdGenerator();
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", products=" + products +
                ", salesman=" + salesman +
                ", amountPrice=" + amountPrice +
                ", closeTime='" + closeTime + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        Bill bill = (Bill)obj;
        return this.getId() == bill.getId();
    }

    public long getCreationDate() {
        return creationDate;
    }

    public int compareTo(Bill o) {
        return (int) (this.creationDate - o.creationDate);
    }
}
