package artcode.shop.terminal;

import artcode.shop.Bill;
import artcode.shop.Product;
import artcode.shop.Salesman;

public class Terminal {
    private Bill[] bills;
    private Salesman[] sales;

    public void login(Salesman salesman) {

    }

    public Bill createBill() {
        return new Bill();
    }

    public void addProduct(Salesman salesman, Product product, Bill bill) {

    }

    public void closeAndSaveBill (Bill bill) {

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
