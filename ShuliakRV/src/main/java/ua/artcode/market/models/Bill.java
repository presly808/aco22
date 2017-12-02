package ua.artcode.market.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {

    private int id;
    private List<Product> products;
    private Salesman salesMan;
    private double amountPrice;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private boolean closed;

    public Bill(Salesman salesMan) {
        this.salesMan = salesMan;
        openTime = LocalDateTime.now();
        products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Salesman getSalesMan() {
        return salesMan;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSalesMan(Salesman salesMan) {
        this.salesMan = salesMan;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }


    public void calculateAmountPrice() {

        double amount = 0;

        for (Product product : getProducts()) {
            amount += product.getPrice();
        }

        amountPrice = amount;

    }

    public boolean hasProducts(List<Product> arrProduct) {

        if (arrProduct == null || arrProduct.isEmpty() ||
                products.isEmpty()) return false;

        for (Product inProduct : arrProduct) {

            boolean hasProd = false;

            for (Product product : products) {
                if (product.getId() == inProduct.getId()) {
                    hasProd = true;
                    break;
                }
            }

            if (!hasProd) return false;
        }

        return true;

    }

    @Override
    public String toString() {

        String str = "Чек№" + id + "\n";

        if (closed) {

            for (Product product : products) {
                str += product.toString();
            }

            str += String.format("Saler: %s; Time: %s; Sum: %.2f . \n",
                    salesMan.getFullname(), closeTime.toString(), amountPrice);

        } else str = "Чек не закрыт!";

        return str;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        return id == bill.id;
    }

}



