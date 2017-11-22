package ua.artcode.market.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bill implements Comparable<Bill> {

    private List<Product> products = new ArrayList<>();
    private int productsCount;
    private int id;
    private double amountPrice;

    private Salesman salesman;

    private Time time;

    public Bill(Salesman salesman, int idOfBill) {
        this.salesman = salesman;
        this.id = idOfBill;
    }

    public void calculateAmountPrice() {

        for (Product product : products) {
            amountPrice += product.getPrice();
        }
    }

    public void closeBill(int hours, int minutes, int seconds) {

        time = new Time(hours, minutes, seconds);
        salesman.setSumOfAllSales(salesman.getSumOfAllSales() + amountPrice);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "products=" + products +
                ", productsCount=" + productsCount +
                ", id=" + id +
                ", amountPrice=" + amountPrice +
                ", salesman=" + salesman +
                ", time=" + time +
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
                time.equals(other.time);

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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    ;

    public Salesman getSalesman() {
        return salesman;
    }

    @Override
    public int compareTo(Bill o) {
        double res = amountPrice - o.getAmountPrice();

        return res > 0 ? 1 :
                res < 0 ? -1 : 0;
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
        return o1.getSalesman().getFullName().compareTo
                (o2.getSalesman().getFullName());
    }
}

class BillTimeComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}




