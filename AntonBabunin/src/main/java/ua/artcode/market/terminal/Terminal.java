package ua.artcode.market.terminal;


import ua.artcode.market.bill.Bill;
import ua.artcode.market.creator.BillCreator;
import ua.artcode.market.product.Product;
import ua.artcode.market.salesman.Salesman;

import java.util.Arrays;


public class Terminal {
    private static final int DEFAULT_SIZE = 20;

    private int countSalesman = 0;


    private Bill[] bills;
    private Salesman[] sales;

    public Terminal() {
    }

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public Bill[] getBills() {
        return bills;
    }

    public Salesman[] getSales() {
        return sales;
    }

    public void setSales(Salesman[] sales) {
        this.sales = sales;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public boolean login(Salesman salesman) {
        if (salesman != null && this.getSales() == null) {

                this.sales = new Salesman[DEFAULT_SIZE];
                this.sales[countSalesman] = salesman;
                return true;
        }

        boolean isAtSales = false;
        for (Salesman seller : this.getSales()) {
            if (salesman.equals(seller)){
                isAtSales = true;
                break;
            }
        } if (!isAtSales) {
            this.sales[countSalesman] = salesman;
            this.countSalesman++;
            return true;
        }

        return true;
    }

    public boolean equals(Terminal terminal) {
        if (terminal == null) return false;
        if (this.getSales().length != terminal.getSales().length) return  false;
        if (this.getBills().length != terminal.getBills().length) return  false;
        for (int i = 0; i < terminal.getSales().length; i++) {
            if (terminal.getSales()[i] != null &&
                    !terminal.getSales()[i].equals(this.getSales()[i]))
                return false;
        }
        for (int i = 0; i < terminal.getBills().length; i++) {
            if (terminal.getBills()[i] != null &&
                    !terminal.getBills()[i].equals(this.getBills()[i]))
                return false;
        }

        return true;
    }
    public Bill createBill(Salesman salesman) {
        if (salesman != null) {

            if (findOpenedBill(salesman) == null) {
                Bill bill = BillCreator.createBill(salesman);
                bills[freeIndexBills()] = bill;
                return bill;
            }
            if (salesman.equals(findOpenedBill(salesman).getSalesman())) {
                return findOpenedBill(salesman);
            }
            System.out.println("You have to close the previous opened bill " +
                    "%s\n" + findOpenedBill(salesman).toString());
            return null;
        }
        return null;
    }

    private Bill findOpenedBill (Salesman salesman) {
        int[] bills = new int[2]; //[0] - opened, [1] - closed
        Bill billOpen = null;
        if (this.getBills() != null) {
            for (Bill bill : this.getBills()) {
                if (bill != null && bill.isClosed()) bills[1]++;
                else if (bill != null && !bill.isClosed()) {
                    bills[0]++;
                    if (salesman.equals(bill.getSalesman())) {
                        return bill;
                    } else {
                        billOpen = bill;
                    }
                }
                return billOpen;
            }
        }
        return null;
    }
    private int freeIndexBills() {
        if (this.getBills() != null){
            for (int i = 0; i < bills.length; i++) {
                if (bills[i] == null) return i;
            }
        }
        return 0;
    }

    public Bill addProduct() {
        if (this.getBills() == null) {
            bills = new Bill[DEFAULT_SIZE];
        } else {
            for (Bill bill : getBills()) {
                if (bill != null && !bill.isClosed()) {
                    return bill;
                }
            }
        }
        return null;
    }

    public boolean closeAndSaveBill (Bill bill) {
        if (bill != null) {
            if (this.bills != null && !bill.isClosed()) {
                double amountPrice = bill.closeBill();
                bill.setAmountPrice(amountPrice);
                bill.toString();
                return true;
            } return false;
        } return false;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "countSalesman=" + countSalesman +
                ", bills=" + Arrays.toString(bills) +
                ", sales=" + Arrays.toString(sales) +
                '}';
    }

// ________________________________________________________________





//_________________

    /*
    private Bill getBillBySalesman(Salesman salesman) {
        if (salesman != null) {
            for (Bill bill : this.getBills()) {
                if (bill.getSalesman().equals(salesman) && !bill.isClosed()) {
                    return bill;
                }
            }
        } return null;
    }


    private void printBill(Bill bill) {
        bill.printBill();
    }


    public Bill findBillById(int id) {
        if (id > 0) {
            if (this.getBills() != null) {
                for (Bill bill :this. getBills()) {
                    if (id == bill.getId()) {
                        return bill;
                    }
                }
            }
        }
        return null;
    }

    public Salesman findSalesmanByLoginOrFullname (String nameOrLogin) {
        if (nameOrLogin != null && this.getSales() != null) {
            for (Salesman salesman : this.getSales()) {
                if (nameOrLogin.equals(salesman.getFullName()) ||
                        nameOrLogin.equals(salesman.getLogin())) {
                    return salesman;
                }
            }
        }
        return null;
    }

    public Salesman getTopNofSalesMan () {
        int[] arrProductSales = new int[DEFAULT_SIZE];
        for (int i = 0; i < this.getSales().length; i++) {
            int countProduct = 0;
            for (Bill bill : this.getBills()) {
                if (this.getSales()[i] != null && bill != null &&
                        bill.getSalesman() == this.getSales()[i]) {
                    countProduct++;
                }
                arrProductSales[i] = countProduct;
            }
        }
        return this.getSales()[maxIndex(arrProductSales)];
    }

    private int maxIndex(int[] arr) {
        int ind = 0;
        if (arr != null) {
            int max = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                    ind = i;
                }
            }
        }
        return ind;
    }

   public Salesman getTopNofSalesMan () {
        Salesman bestSeller = null;
        if (this.getSales() != null) {

            int[] countSales = new int[getSales().length];
            for (int i = 0; i < this.getSales().length; i++) {
                int count = 0;
                for (Bill bill : this.getBills()) {
                    if (bill != null && this.getSales()[i] != null &&
                    this.getSales()[i].equals(bill.getSalesman())) {
                        count++;
                    }
                }
                countSales[i] = count;
            }
             bestSeller = this.getSales()[maxIndex(countSales)];
        }
        return bestSeller;

    public void doSomeStatisticStuff () {

    }

    private double getMaxAmountPrice () {
        double max = 0.0;
        if (getBills() != null && this.getBills().length > 0) {
            for (Bill bill : this.getBills()) {
                if (bill.getAmountPrice() > max)
                    max = bill.getAmountPrice();
            }
        }
        return max;
    }

    private double getMinAmountPrice () {
        double min = 0.0;
        if (getBills() != null && this.getBills().length > 0) {
            min = this.getBills()[0].getAmountPrice();
            for (Bill bill : this.getBills()) {
                if (bill.getAmountPrice() < min)
                    min = bill.getAmountPrice();
            }
        }
        return min;
    }

    private double getAverageAmountPrice () {
        double average = 0.0;
        if (getBills() != null && this.getBills().length > 0) {
            for (Bill bill : this.getBills()) {
                average += bill.getAmountPrice();
            }
        }
        return average/getBills().length;
    }

    private int getMaxProduct() {
        int max = 0;
        if (this.getBills() != null && this.getBills().length > 0) {
            max = ( this.getBills()[0].getProducts() != null) ?
            this.getBills()[0].getProducts().length : max;
            for (Bill bill : this.getBills()) {
                if (bill.getProducts() != null &&
                bill.getProducts().length > max)
                    max = bill.getProducts().length;
            }
        }
        return max;
    }

    private int getMinProduct() {
        return 0;
    }

    private int getAverageProduct() {
        return 0;
    }

    private int getMaxProductSales() {
        return 0;
    }

    private int getMinProductSales() {
        return 0;
    }

    private int getAverageProductSales() {
        return 0;
    }

    private int getAverageAmountPriceSales() {
        return 0;
    }

    private int getMaxAmountPriceSales() {
        return 0;
    }

    public void setCountClosedBill(int countClosedBill) {
    this.countClosedBill = countClosedBill;}*/
}
