package ua.artcode.market;

import java.util.Scanner;

public class Terminal {

    private static final int DEFAULT_AMOUNT_BILLS = 1000;

    private Bill[] bills;
    private Salesman[] salesMen;

    private int numBill;

    public Terminal(Salesman[] s) {
        salesMen = s;
        bills = new Bill[DEFAULT_AMOUNT_BILLS];
    }

    public Terminal(Salesman[] s, int amountBills) {
        salesMen = s;
        bills = new Bill[amountBills];
    }

    public Salesman login() {

        if (salesMen == null) return null;
        else {

            Scanner scan = new Scanner(System.in);

            System.out.println("Введите логин: ");
            String login = scan.nextLine();
            System.out.println("Введите пароль: ");
            String password = scan.nextLine();

            int i;

            for (i = 0; i < salesMen.length; i++) {
                if ((salesMen[i].getLogin().equals(login)) && (salesMen[i].getPassword().equals(password)))
                    return salesMen[i];
            }

            return null;
        }
    }

    public void createBill(Salesman s) {

        if (s != null) bills[numBill] = new Bill(s);

    }

    public void addProduct(Product p) {

        if (p != null) bills[numBill].addProduct(p);

    }

    public Bill closeAndSaveBill() {

        bills[numBill].closeBill();
        if (!bills[numBill].isOpen()) {
            return bills[numBill++];
        } else {
            bills[numBill] = null;
        }

        return null;
    }

    public Bill findBillById(int id) {
        for (int i = 0; i < numBill; i++) {
            if (bills[i].getId() == id) return bills[i];
        }

        return null;
    }

    public Salesman findSalesmanByLoginOrFullname(String login, String fullname) {

        if ((login == null && fullname == null) || (salesMen.length == 0)) return null;

        if ((login != null) && (fullname == null)) {

            for (int i = 0; i < salesMen.length; i++) {
                if (salesMen[i].getLogin().equals(login)) {
                    return salesMen[i];
                }
            }
        } else if ((login == null) && (fullname != null)) {

            for (int i = 0; i < salesMen.length; i++) {
                if (salesMen[i].getFullname().equals(fullname)) {
                    return salesMen[i];
                }
            }
        } else if ((login != null) && (fullname != null)) {

            for (int i = 0; i < salesMen.length; i++) {
                if ((salesMen[i].getLogin().equals(login)) && (salesMen[i].getFullname().equals(fullname))) {
                    return salesMen[i];
                }
            }
        }

        return null;
    }

    public Salesman[] getTopNofSalesMan(int n) {
        Salesman[] s = new Salesman[n];
        int[] sales = new int[salesMen.length];

        for (int i = 0; i < numBill; i++) {
            sales[bills[i].getSalesMan().getId()] += bills[i].getNumProd();
        }

        for (int i = 0; i < Math.min(s.length, sales.length); i++) {
            int max = 0;
            int index = 0;
            for (int j = 0; j < sales.length; j++) {
                if (max < sales[j]) {
                    max = sales[j];
                    index = j;
                }
            }
            sales[index] = 0;
            s[i] = salesMen[index];
        }

        return s;
    }

    public double getMax() {

        double max = 0;

        for (int i = 0; i < numBill; i++) {
            if (max < bills[i].getAmountPrice()) max = bills[i].getAmountPrice();
        }

        return max;
    }

    public double getMin() {

        double min = 0;

            for (int i = 0; i < numBill; i++) {
                if (min > bills[i].getAmountPrice()) min = bills[i].getAmountPrice();
            }

        return min;
    }

    public double getAverage() {

        double result = 0;

        if (numBill > 0) {

            for (int i = 0; i < numBill; i++) {
                result += bills[i].getAmountPrice();
            }
            result /= numBill;
        }
        return result;
    }

    public int countSoldProducts() {

        int result = 0;


            for (int i = 0; i < numBill; i++) {
                result += bills[i].getNumProd();
            }

        return result;
    }

}
