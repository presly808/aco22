package ua.artcode.market;

import java.util.Scanner;

public class Terminal {

    private static final int DEFAULT_AMOUNT_BILLS = 1000;

    private Bill[] bills;
    private Salesman[] sales;

    private int numBill;

    public Terminal(Salesman[] s) {
        sales = s;
        bills = new Bill[DEFAULT_AMOUNT_BILLS];
    }

    public Terminal(Salesman[] s, int amountBills) {
        sales = s;
        bills = new Bill[amountBills];
    }

    public Salesman login() {

        if (sales == null) return null;
        else {

            Scanner scan = new Scanner(System.in);

            System.out.print("Введите логин: ");
            String login = scan.nextLine();
            System.out.print("Введите пароль: ");
            String password = scan.nextLine();

            int i;

            for (i = 0; i < sales.length; i++) {
                if (sales[i] != null)
                    if ((sales[i].getLogin().equals(login)) && (sales[i].getPassword().equals(password))) {
                        sales[i].setLogged(true);
                        return sales[i];
                    }
            }

            return null;
        }
    }

    public boolean createBill(Salesman s) {

        if ((s != null) && (s.isLogged()) && (numBill < bills.length)) {
            bills[numBill] = new Bill(s);
            return true;
        }

        return false;
    }

    public boolean addProduct(Product p) {

        if (bills[numBill].addProduct(p)) return true;

        return false;

    }

    public Bill closeAndSaveBill() {

        if (bills[numBill].closeBill()) {
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

    public Salesman[] findSalesmanByLoginOrFullname(String loginOrFullname) {

        Salesman[] s = new Salesman[sales.length];
        int index = 0;

        if ((loginOrFullname == null || loginOrFullname.isEmpty()) || (sales.length == 0)) return null;

        for (int i = 0; i < sales.length; i++) {
            if (sales[i].getLogin().equals(loginOrFullname) || sales[i].getFullname().equals(loginOrFullname))
                s[index++] = sales[i];
        }

        if (index > 0) return s;

        return null;
    }

    public int getIndexSales(Salesman s) {

        if (s != null) {

            for (int i = 0; i < sales.length; i++) {
                if (sales[i] == s) return i;
            }
        }

        return -1;
    }

    public Salesman[] getTopNofSalesMan(int n) {

        if ((n > 0) && (sales != null) && (n <= sales.length)) {

            Salesman[] s = new Salesman[n];
            int[] salesProducts = new int[sales.length];

            for (int i = 0; i < numBill; i++) {
                salesProducts[getIndexSales(bills[i].getSalesMan())] += bills[i].getNumProd();
            }

            for (int i = 0; i < n; i++) {

                int max = 0;
                int index = 0;

                for (int j = 0; j < salesProducts.length; j++) {
                    if (max < salesProducts[j]) {
                        max = salesProducts[j];
                        index = j;
                    }
                }
                salesProducts[index] = 0;
                s[i] = sales[index];
            }

            return s;
        }

        return null;
    }

    public void doSomeStatisticStuff() {

        System.out.printf("Максимальная сумма чека: %.2f %n", getMax());
        System.out.printf("Минимальная сумма чека: %.2f %n", getMin());
        System.out.printf("Средняя сумма чека: %.2f %n", getAverage());
        System.out.printf("Количество проданных продуктов: %d %n", countSoldProducts());

    }

    public double getMax() {

        if (numBill > 0) {

            double max = bills[0].getAmountPrice();

            for (int i = 1; i < numBill; i++) {
                if (max < bills[i].getAmountPrice()) max = bills[i].getAmountPrice();
            }

            return max;
        }
        return 0;
    }

    public double getMin() {

        if (numBill > 0) {

            double min = bills[0].getAmountPrice();

            for (int i = 1; i < numBill; i++) {
                if (min > bills[i].getAmountPrice()) min = bills[i].getAmountPrice();
            }

            return min;
        }

        return 0;
    }

    public double getAverage() {

        if (numBill > 0) {

            double result = bills[0].getAmountPrice();

            for (int i = 1; i < numBill; i++) {
                result += bills[i].getAmountPrice();
            }
            result /= numBill;

            return result;
        }

        return 0;
    }

    public int countSoldProducts() {

        int result = 0;

        for (int i = 0; i < numBill; i++) {
            result += bills[i].getNumProd();
        }

        return result;
    }

}
