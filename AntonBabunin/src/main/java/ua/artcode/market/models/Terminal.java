package ua.artcode.market.models;

import ua.artcode.market.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private int id;
    private List<Bill> bills;
    private List<Salesman> sales;

    public Terminal() {
        this.id = Utils.generateTerminalID();
        this.bills = new ArrayList<Bill>();
        this.sales = new ArrayList<Salesman>();
    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public List<Bill> getBills() {
        return bills;
    }

//    public void setBills(List<Bill> bills) {
//        this.bills = bills;
//    }

    public List<Salesman> getSales() {
        return sales;
    }

//    public void setSales(List<Salesman> sales) {
//        this.sales = sales;
//    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Terminal terminal = (Terminal) object;

        return id == terminal.id && bills.equals(terminal.bills) &&
                sales.equals(terminal.sales);
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                ", bills=" + bills +
                ", sales=" + sales +
                '}';
    }
}
