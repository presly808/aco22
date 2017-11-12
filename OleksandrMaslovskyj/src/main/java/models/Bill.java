package models;

import Utils.DateUtils;
import Utils.TerminalUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill implements Comparable<Bill>{

    private long id;
    private List<Product> products;
    private Salesman salesman;
    private String closeTime;
    private Date creationDate;

    public Bill() {
        this.creationDate = DateUtils.getCurrentDate();
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
                ", closeTime='" + closeTime + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        Bill bill = (Bill)obj;
        return this.getId() == bill.getId();
    }

    public int compareTo(Bill o) {
        return this.creationDate.compareTo(o.creationDate);
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
