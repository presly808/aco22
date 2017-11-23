package ua.artcode.market.models;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Bill implements Comparable<Bill> {

    public static BillComparator billComparator = new BillComparator();

    private int id;
//    private int terminalId;
    private Map<Product, Integer> productsMap;
    private Salesman salesman;
    private double amountPrice;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    public Bill() {
        this.productsMap = new HashMap<>();
        this.openTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getTerminalId() {
//        return terminalId;
//    }
//
//    public void setTerminalId(int terminalId) {
//        this.terminalId = terminalId;
//    }

    public Map<Product, Integer> getProductsMap() {
        return productsMap;
    }

//    public void setProductsMap(Map<Product, Integer> productsMap) {
//        this.productsMap = productsMap;
//    }

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Bill bill = (Bill) object;

        return id == bill.id;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (productsMap != null ? productsMap.hashCode() : 0);
        result = 31 * result + (salesman != null ? salesman.hashCode() : 0);
        temp = Double.doubleToLongBits(amountPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (openTime != null ? openTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + id +
//                ", terminalId=" + terminalId +
                ", productsMap=" + productsMap +
                ", salesman=" + salesman +
                ", amountPrice=" + amountPrice +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }

    @Override
    public int compareTo(Bill o) {
        if (o == null) return 1;
        if (((o.getAmountPrice()-this.getAmountPrice()) * 100) > 0) return -1;
        else if (((o.getAmountPrice()-this.getAmountPrice()) * 100) == 0)
            return 0;
        else return 1;
    }
//
//    @Override
//    public int compare(Bill o1, Bill o2) {
//        return (int)((o1.getAmountPrice() - o2.getAmountPrice())*100);
//    }
//
//    @Override
//    public Comparator<Bill> thenComparing(Comparator<? super Bill> other) {
//        return new Comparator<Bill>() {
//            @Override
//            public int compare(Bill o1, Bill o2) {
//                return o1.getCloseTime().compareTo(o2.getCloseTime());
//            }
//        };
//    }
}
