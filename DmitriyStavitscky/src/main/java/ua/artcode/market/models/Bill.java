package ua.artcode.market.models;

import java.util.Arrays;
import java.util.Comparator;

public class Bill implements Comparable<Bill> {

    private static final int MAX_COUNT_OF_PRODUCTS_IN_BILL = 10;

    private Product[] products = new Product[MAX_COUNT_OF_PRODUCTS_IN_BILL];
    private int productsCount;
    private int id;
    private double amountPrice;

    private boolean isClosed;

    private Salesman salesman;

    private Time time;

    public Bill(Salesman salesman, int idOfBill) {
        this.salesman = salesman;
        this.id = idOfBill;
    }

    public void addProduct(String name, int id, double price) {
        if (productsCount == MAX_COUNT_OF_PRODUCTS_IN_BILL) {
            System.out.println("sorry, max count of products in bill");

        } else if (name == null || id == 0 || price == 0.0) {
            System.out.println("wrong data");

        } else if (isClosed) {
            System.out.println("sorry, bill is closed");

        } else {
            products[productsCount++] = new Product(name, id, price);
            calculateAmountPrice();
        }
    }

    private void calculateAmountPrice() {
        for (int i = 0; i < productsCount; i++) {
            amountPrice += products[i].getPrice();
        }
    }

    public void closeBill(int hours, int minutes, int seconds) {
        isClosed = true;
        time = new Time(hours, minutes, seconds);
        salesman.setSumOfAllSales(amountPrice);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "products=" + Arrays.toString(products) +
                ", productsCount=" + productsCount +
                ", id=" + id +
                ", amountPrice=" + amountPrice +
                ", isClosed=" + isClosed +
                ", salesman=" + salesman.toString() +
                ", time=" + time.toString() +
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

        return (products != null && Arrays.equals(products, other.products)) &&
                (productsCount == other.productsCount) &&
                id == other.id &&
                amountPrice == other.amountPrice &&
                isClosed == other.isClosed &&
                salesman.equals(other.salesman) &&
                time.equals(other.time);

    }

    // if this < object -> -
    // if this > object -> +

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public int getId() {
        return id;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Time getTime() {
        return time;
    }

    public boolean isClosed() {
        return isClosed;
    }

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
        return o1.getProductsCount() - o2.getProductsCount();
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




