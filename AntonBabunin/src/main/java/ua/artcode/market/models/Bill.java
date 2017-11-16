package ua.artcode.market.models;

import ua.artcode.market.utils.Utils;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Bill implements Comparator<Bill>{

    private int billId;
    private int terminalId;
    private Map<Product, Integer> products;
    private Salesman salesman;
    private double amountPrice;

    private Date openTime;
    private Date closeTime;

    public Bill() {
        this.billId = Utils.generateBillID();
        this.products = new HashMap<Product, Integer>();
        this.openTime = Utils.getCurrentTime();
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
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

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", products=" + products +
                ", salesman=" + salesman +
                ", amountPrice=" + amountPrice +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;

        if (object instanceof Bill) {
            Bill bill = (Bill) object;
            if (this.getBillId() == bill.getBillId() &&
                    this.getTerminalId() == bill.getTerminalId()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int compare(Bill o1, Bill o2) {
        return 0;
    }

    @Override
    public Comparator<Bill> reversed() {
        return null;
    }


}
