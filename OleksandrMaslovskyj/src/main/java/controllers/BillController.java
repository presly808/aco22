package controllers;

import utils.TerminalUtils;
import interfaces.IBill;
import models.Bill;
import models.Product;
import java.text.SimpleDateFormat;
import java.util.*;

public class BillController implements IBill{

    private Set<Bill> billSet;
    private int amountPrice;

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
                "yyyy-MM-dd HH:mm:ss.SSS").toString());
    }

    public double calculateAmountPrice(Bill bill) {
        int amountPrice = 0;

        List<Product> products = bill.getProducts();
        if (products.isEmpty()) {
            throw new IllegalStateException("Empty product list");
        }

        for (Product product : products) {
            amountPrice += product.getPrice();
        }
        this.amountPrice = amountPrice;
        return amountPrice;
    }

    public int getAmountPrice() {
        return amountPrice;
    }

    public void printBill(Bill currentBill) {
        System.out.println("BillController{" +
                "id=" + currentBill.getId() +
                ", products=" + currentBill.getProducts() +
                ", salesman=" + currentBill.getSalesman() +
                ", amountPrice=" + getAmountPrice() +
                ", closeTime='" + currentBill.getCloseTime() + '\'' +
                '}');
    }

    public Set<Bill> getBillSet() {
        return billSet;
    }
}


