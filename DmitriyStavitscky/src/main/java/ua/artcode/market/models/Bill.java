package ua.artcode.market.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bill implements Comparable<Bill> {

    private List<Product> products = new ArrayList<>();
    private int productsCount;
    private int id;
    private double amountPrice;

    private Salesman salesman;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    public Bill(Salesman salesman, int idOfBill) {
        this.salesman = salesman;
        this.id = idOfBill;
        this.openTime = LocalDateTime.now();
    }

    public void calculateAmountPrice() {

        for (Product product : products) {
            amountPrice += product.getPrice();
        }

    }

    public void closeBill() {

        closeTime = LocalDateTime.now();
        salesman.setSumOfAllSales(salesman.getSumOfAllSales() + amountPrice);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "products=" + products +
                ", productsCount=" + productsCount +
                ", id=" + id +
                ", amountPrice=" + amountPrice +
                ", salesman=" + salesman.getFullName() +
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
                salesman.equals(other.salesman) &&
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

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    @Override
    public int compareTo(Bill o) {
        double res = amountPrice - o.getAmountPrice();

        return res > 0 ? 1 :
                res < 0 ? -1 : 0;
    }

    public static class ProductsCountComparator implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getProducts().size() - o2.getProducts().size();
        }
    }

    public static class AmountPriceComparator implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {

            double res = o1.getAmountPrice() - o2.getAmountPrice();

            return res > 0 ? 1 :
                    res < 0 ? -1 : 0;
        }
    }

    public static class SalesmanComparator implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getSalesman().getFullName().compareTo
                    (o2.getSalesman().getFullName());
        }
    }
}




