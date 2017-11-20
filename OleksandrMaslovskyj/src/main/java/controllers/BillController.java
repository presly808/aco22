package controllers;

import utils.TerminalUtils;
import interfaces.IBillLogic;
import models.Bill;
import models.Product;
import java.text.SimpleDateFormat;
import java.util.*;

public class BillController implements IBillLogic {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private Set<Bill> billSet;
    private double amountPrice;

    public BillController() {
        billSet = new HashSet<>();
    }

    public Product addProductToBill(Bill bill, String productName){
        Product product = new Product(TerminalUtils.longIdGenerator(),
                            productName, new Random().nextDouble());
        bill.setProducts(product);
        return product;
    }

    public void closeBill(Bill bill) {
        bill.setCloseTime(new SimpleDateFormat(
                DATE_FORMAT).toString());
    }

    public double calculateAmountPrice(Bill bill) {
        List<Product> products = bill.getProducts();
        if (products.isEmpty()) {
            throw new IllegalStateException("Empty product list");
        }
        double amountPrice = products.stream().mapToDouble(Product::getPrice).sum();
        this.amountPrice = amountPrice;
        return amountPrice;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public String printBill(Bill currentBill) {
        return String.format("{id:%d, products:%s, salesman:%s, " +
                        "amountPrice%d, closeTime%s}" , currentBill.getId(),
                currentBill.getProducts(), currentBill.getSalesman(),
                getAmountPrice(), currentBill.getCloseTime());
    }

    public Set<Bill> getBillSet() {
        return billSet;
    }
}