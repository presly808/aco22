package hw1.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Bill extends DBItem implements Comparable {

    private List<Product> products;
    private Salesman salesman;
    private Double amountPrice;
    private Date closeTime;

    public Bill(int id, Salesman salesman) {
        super.id = id;
        this.salesman = salesman;
        this.products = new ArrayList<>(10);
        this.amountPrice = 0.0;
    }

    public Bill(int id) {
        super.id = id;
        this.products = new ArrayList<>(10);
    }

    public Bill closeBill(){
        setCloseTime(new Date(System.currentTimeMillis()));
        calculateAmountPrice();
        return this;
    }

    public Bill closeBill(Date d){
        setCloseTime(d);
        calculateAmountPrice();
        return this;
    }

    public boolean addProduct(Product p){
        this.amountPrice = p.getPrice();
        return this.products.add(p);
    }

    private void calculateAmountPrice(){
        amountPrice = 0.0;
        for (Product p : this.products){
            amountPrice += p.getPrice();
        }
    }

    public String printBill(){
        return this.toString();
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
    
    public List<Product> getProducts() {
        return (List<Product>) products;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", products=" + products +
                ", salesman=" + salesman +
                ", amountPrice=" + amountPrice +
                ", closeTime=" + closeTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;

        Bill bill = (Bill) o;

        if (id != bill.id) return false;
        if (!products.equals(bill.products)) return false;
        if (salesman != null ? !salesman.equals(bill.salesman) : bill.salesman != null) return false;
        if (amountPrice != null ? !amountPrice.equals(bill.amountPrice) : bill.amountPrice != null) return false;
        return closeTime != null ? closeTime.equals(bill.closeTime) : bill.closeTime == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + products.hashCode();
        result = 31 * result + (salesman != null ? salesman.hashCode() : 0);
        result = 31 * result + (amountPrice != null ? amountPrice.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() == Bill.class) {
            return this.id - ((Bill) o).getId();
        }
        return 0;
    }

    public static class SortByDateComparator implements Comparator<Bill>{
        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getCloseTime().compareTo(o2.getCloseTime());
        }
    }

}
