package artcode.shop.terminal;

import artcode.shop.Bill;
import artcode.shop.Product;
import artcode.shop.Salesman;

public class Terminal {
    private Bill[] bills;
    private Salesman[] sales;

    private int countBill;

    public void login(Salesman salesman) {

    }

    public Bill createBill() {
        return new Bill();
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
                bills[countBill++] = new Bill();
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





    public Terminal() {
    }

    public Bill[] getBills() {
        return bills;
    }

    public Salesman[] getSales() {
        return sales;
    }

}
