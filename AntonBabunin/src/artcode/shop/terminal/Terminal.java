package artcode.shop.terminal;

import artcode.shop.Bill;
import artcode.shop.Product;
import artcode.shop.Salesman;

import java.util.Scanner;

public class Terminal {
    private Bill[] bills;
    private Salesman[] sales;

    private static int countClosedBill;
    private static int countCreatedBill;

    private static int countSalesman;



    public void login(Salesman salesman) {
        if (salesman != null)
            getSales()[countSalesman++] = salesman;
    }

    public Bill createBill(Salesman salesman) {
        if (getCountClosedBill() != getCountCreatedBill()) return null;

        if ((salesman == null)) return null;
        else {
            for (Salesman seller : sales) {
                if (seller.equals(salesman)) {
                    setCountCreatedBill(getCountCreatedBill() + 1);
                    return new Bill();
                }
            } return null;
        }
    }

    public void addProduct(Salesman salesman, Product product, Bill bill) {

    }

    public void closeAndSaveBill (Bill bill) {
        if (bill != null) {
            if (bills == null) {
                int size = 20;
                bills = new Bill[size];
            }
            if (!bill.isClosed()) {
                bill.closeBill();
                bills[countClosedBill++] = new Bill();
            }
        }
     }

    public Bill findBillById(int id) {
        return new Bill();
    }

    public Salesman findSalesmanByLoginOrFullname (String nameOrLogin) {
        return new Salesman("new");
    }

    public Salesman getTopNofSalesMan () {
        return new Salesman("new");
    }

    public void doSomeStatisticStuff () {

    }

    public static void setCountClosedBill(int countClosedBill) {
        Terminal.countClosedBill = countClosedBill;
    }

    public static int getCountCreatedBill() {
        return Terminal.countCreatedBill;
    }
    public static int getCountClosedBill() {
        return Terminal.countClosedBill;
    }

    public static void setCountCreatedBill(int countCreatedBill) {
        Terminal.countCreatedBill = countCreatedBill;
    }

    public Terminal() {
    }

    public Bill[] getBills() {
        return bills;
    }

    public Salesman[] getSales() {
        return sales;
    }

}
