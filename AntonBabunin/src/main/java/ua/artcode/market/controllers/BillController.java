package ua.artcode.market.controllers;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class BillController {

    private Map<Product,Integer> products;
    private double amountPrice;

    public BillController() {
        this.products = new HashMap<Product,Integer>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }


    public double getAmountPrice() {
        return amountPrice;
    }

    public boolean addProduct(Product product) {
        if (product != null) {
            if (products == null) {
                products = new HashMap<Product,Integer>();
                products.put(product, 1);
                return true;
            }
            if (!products.containsKey(product)) {
                products.put(product, 1);
                return true;
            }
            return addProductIs(product);
        }
        return false;
    }

    private boolean addProductIs(Product product) {
        for (Map.Entry<Product,Integer> pair : products.entrySet()) {
            if (pair.getKey() == product) {
                pair.setValue(pair.getValue() + 1);
                return true;
            }
        }
        return false;
    }

    public double calculateAmountPrice (Bill bill) {
        double amountPrice = 0.0;
        if (bill != null && !bill.getProducts().isEmpty()) {
            for (Map.Entry<Product, Integer> pair : products.entrySet()) {
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
