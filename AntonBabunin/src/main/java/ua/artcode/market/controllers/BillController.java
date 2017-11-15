package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IBill;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillController implements IBill{

    private ArrayList<Bill> bills;
    private double amountPrice;

    public BillController() {
        this.bills = new ArrayList<Bill>();
    }


    public List<Bill> getBills() {
        return bills;
    }

//    public double getAmountPrice() {
//        return amountPrice;
//    }

    public boolean addProduct(Bill bill, Product product) {
        return bill != null && product != null && addProductIs(bill, product);
    }

    private boolean addProductIs(Bill bill, Product product) {
        if (bill.getProducts().containsKey(product)) {
            for (Map.Entry<Product, Integer> pair :
                    bill.getProducts().entrySet()) {
                if (pair.getKey().equals(product)) {
                    pair.setValue(pair.getValue() + 1);
                    return true;
                }
                bill.getProducts().put(product, 1);
                return true;
            }
        }
        bill.getProducts().put(product, 1);
        return true;
    }

    public double calculateAmountPrice (Bill bill) {
        double amountPrice = 0.0;
        if (bill != null && !bill.getProducts().isEmpty()) {
            for (Map.Entry<Product, Integer> pair :
                    bill.getProducts().entrySet()) {
                amountPrice += pair.getKey().getPrice() * pair.getValue();
            }
        }
        this.amountPrice = amountPrice;
        return amountPrice;
    }

    public boolean closeBill(Bill bill) {
        if  (bill != null){
            bill.setCloseTime(Utils.getCurrentTime());
            return true;
        }
        return false;
    }

    public String printBill(Bill bill) {
        String print = "BillController{" +
                "id=" + bill.getBillId() +
                ", openTime=" + bill.getOpenTime() +
                ", terminal id=" + bill.getTerminalId() +
                ", products=" + bill.getProducts() +
                ", salerman=" + bill.getSalesman() +
                ", amountPrice=" + bill.getAmountPrice() +
                ", closeTime=" + bill.getCloseTime() +
                '}';
        return print;
    }
}
