package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

public class Terminal implements ITerminal {

    private Bill[] bills = new Bill[10];
    private Salesman[] sales = new Salesman[10];

    public Terminal() {
        this.bills = new Bill[20];
        this.sales = new Salesman[10];
    }


    @Override
    public boolean login(String login, String password) {
        for (int i = 0; i < sales.length; i++) {
            if (sales[i] == null) break;
            if (sales[i].getLogin().equals(login) || sales[i].getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addBill(Bill bill) {
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) bills[i] = bill;
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addSalesman(Salesman salesman) {
        for (int i = 0; i < sales.length; i++) {
            if (sales[i] == null) sales[i] = salesman;
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Bill createBill(int id, Salesman salesman) {
        if (salesman.isStatus()) {
            Bill bill = new Bill(id, salesman);
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
            for (int j = 0; j < bills.length; j++) {
                if (bills[0].getSalesman().equals(sales[5])) {
                    count[i] = count[i] + 1;
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

    public Bill[] filterByParameter(Salesman[] salesman, Product[] product, String startTime, String endTime) {
        Bill[] billTime = filterByDate(startTime, endTime);
        Bill[] bySales = filterBySales(billTime, salesman);
        Bill[] byProduct = filterByProduct(bySales, product);
        return byProduct;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////FilterByDate
    public Bill[] filterByDate(String startTime, String endTime) {
        Bill[] forRerurn = new Bill[bills.length];
        int count = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) break;
            if (((bills[i].getDataTime().compareTo(startTime)) >= 0 &&
                    (bills[i].getDataTime().compareTo((endTime))) <= 0)) {
                forRerurn[count] = bills[i];
                count++;
            }

        }
        return forRerurn;
    }

    /////////////////////////////////////////////////////////////////////////////////////////FilterBySaleman
    public Bill[] filterBySales(Bill[] withBill, Salesman[] withSales) {
        Bill[] forRerurn = new Bill[withBill.length];
        int count = 0;
        for (int i = 0; i < withSales.length; i++) {
            for (int y = 0; y < withBill.length; y++) {
                if (withBill[y] == null) break;
                if (withSales[i].equals(withBill[y].getSalesman())) {
                    forRerurn[count] = withBill[y];
                }
            }
        }
        return forRerurn;
    }

    /////////////////////////////////////////////////////////////////////FilterByProduct
    public Bill[] filterByProduct(Bill[] withBill, Product[] withProduct) {
        Bill[] forRerurn = new Bill[withBill.length];
        int count = 0;
        for (int i = 0; i < withProduct.length; i++) {
            for (int y = 0; y < withBill.length; y++) {
                if (withBill[y] == null) break;
                if (searchInBillProduct(withBill[y], withProduct[i])) {
                    forRerurn[count] = withBill[y];
                    count++;
                }
            }
        }
        return forRerurn;
    }

    /////////////////////////////~~~/////////////////////////
    public boolean searchInBillProduct(Bill bill, Product product) {
        for (int i = 0; i < bill.getProducts().length; i++) {
            if (bill.getProducts()[i] == null) break;
            if (bill.getProducts()[i].equals(product)) return true;
        }
        return false;
    }


}