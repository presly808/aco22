package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;
import week1.model.MyDataTime;

import java.util.Comparator;

public class Terminal implements ITerminal {

    private Bill[] bills = new Bill[20];
    private Salesman[] sales = new Salesman[10];

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public Terminal() {
    }

    @Override
    public boolean login(String login, String password) {
        for (int i = 0; i < sales.length; i++) {
            if (sales[i] == null) break;
            if (sales[i].getLogin().equals(login)) {
                if (sales[i].getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Bill createBill(int id, Salesman salesman) {
        if (salesman.isStatus()) {
            Bill bill = new Bill();
            bill.setId(id);
            bill.setSalesman(salesman);
            return bill;
        }
        return null;
    }

    @Override
    public void addProduct(Bill bill, Product product) {
        if (bill.getDataTime() == null) {
            for (int i = 0; i < bill.getProducts().length; i++) {
                if (bill.getProducts()[i] == null) {
                    bill.getProducts()[i] = product;
                }
            }
        }

    }

    @Override
    public Bill getBillById(int id) {
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) break;
            if (bills[i].getId() == id) {
                return bills[i];
            }
        }
        return null;
    }                        //search bill

    @Override
    public Salesman getSalemanByName(String salesman) {
        for (int i = 0; i < salesman.length(); i++) {
            if (sales[i] == null) break;
            if (sales[i].getLogin().equals(salesman) || sales[i].getFullname().equals(salesman)) {
                return sales[i];
            }
        }
        return null;
    }

    @Override
    public Salesman getTopNofSalesMan() {
        int id = 0;
        int[] count = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            if (sales[i] == null) break;
            count[i] = 0;
            for (int y = 0; y < bills.length; i++) {
                if (bills[y].getSalesman().equals(sales[i])) {
                    count[i]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
                id = i;
                max = count[i];
            }
        }
        return sales[id];
    }                   //

    public void doSomeStatisticStuff() {
        System.out.println("Max Bill " + maxBill());
        System.out.println("Min Bill " + minBill());
        System.out.println("Average bill " + averageBill());

    }

    public int maxBill() {
        int id = 0;
        double max = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) break;
            if (max < bills[i].getAmountPrice()) {
                id = i;
                max = bills[i].getAmountPrice();
            }
        }
        return id;
    }

    public int minBill() {
        int id = 0;
        double min = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) break;
            if (min > bills[i].getAmountPrice()) {
                id = i;
                min = bills[i].getAmountPrice();
            }
        }
        return id;

    }

    public double averageBill() {
        double average = 0.0d;
        int count = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) break;
            average += bills[i].getAmountPrice();
            count++;
        }
        average = average / count;
        return average;
    }

    public Bill[] filterByParameter(Salesman[] salesman, Product[] product, MyDataTime startTime, MyDataTime endTime ) {

        Bill[] tempbill = new Bill[bills.length];
        int count = 0;

        for (int i = 0; i < bills.length; i++) {
            if ( bills[i] == null)break;
            for (int y = 0; y < salesman.length; i++){
                if (bills[i].getSalesman().equals(salesman[y])){
                    if (((startTime.compareTo(bills[i].getDataTime())) >= 0 &&
                            (startTime.compareTo(bills[i].getDataTime())) <= 0 )){
                        tempbill[count] = bills[i];
                    }
                }
            }
        }

        Bill [] forReturn = new Bill[tempbill.length];
        int countForReturn = 0;
        for (int i = 0; i < forReturn.length; i++){
            if (bills[i].getProducts().equals(product))
                forReturn[count] = bills[i];
            countForReturn++;
        }
        return forReturn;
    }
}
