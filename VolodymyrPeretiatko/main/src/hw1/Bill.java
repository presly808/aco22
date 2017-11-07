package hw1;

import java.util.ArrayList;
import java.util.Date;


public class Bill {

    private int id;
    private ArrayList<Product> products;
    private Salesman salesman;
    private double amountPrice;
    private Date closeTime;

    public static Bill openBill(int id, Salesman salesman){
        Bill bill = new Bill(id, salesman);
        bill.products = new ArrayList<>();
        return bill;
    }

    public static Bill closeBill(Bill bill){
        bill.setCloseTime(new Date(System.currentTimeMillis()));
        bill.calculateAmountPrice();
        return bill;
    }


    public boolean addProduct(Product p){
        this.amountPrice += p.getPrice();
        return this.products.add(p);
    }

    private void calculateAmountPrice(){
        for (Product p : this.products){
            amountPrice += p.getPrice();
        }
    }

    public String printBill(){
        return this.toString();
    }

    public Bill(int id, Salesman salesman) {
        this.id = id;
        this.salesman = salesman;
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

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProducts() {
        return products;
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

}
