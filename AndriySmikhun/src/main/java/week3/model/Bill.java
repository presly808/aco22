package week3.model;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int id;
    private List<Product> products;
    private double everageBill;
    private String openTime;
    private String closeTime;
    private Salesman salesman;

    public Bill(int id, double everageBill, String openTime, String closeTime, Salesman salesman) {
        this.id = id;
        this.everageBill = everageBill;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.salesman = salesman;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getEverageBill() {
        return everageBill;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        return id == bill.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
