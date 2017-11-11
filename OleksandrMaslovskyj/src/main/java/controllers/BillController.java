package controllers;

import Utils.*;
import interfaces.IBill;
import models.Product;
import models.Salesman;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BillController implements IBill {

    private long id;
    private List<Product> products;
    private Salesman salesman;
    private double amountPrice;

    private String closeTime;

    public BillController() {
        this.id = TerminalUtils.longIdGenerator();
        this.products = new ArrayList();
        this.salesman = new Salesman(StringGenerator.generateName(), StringGenerator.generateName());
    }

    public Product addProduct(String name){
        Product product = new Product(TerminalUtils.longIdGenerator(), name, new Random().nextDouble());
        products.add(product);
        return product;
    }

    public void closeBill() {
        this.closeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").toString();
        products = null;
    }

    public double calculateAmountPrice() {
        amountPrice = 0;

        if (products.isEmpty()) {
            throw new IllegalStateException("Empty product list");
        }

        for (Product product : products) {
            amountPrice += product.getPrice();
        }

        return amountPrice;
    }

    public void printBill() {
        System.out.println("BillController{" +
                "id=" + id +
                ", products=" + products +
                ", salesman=" + salesman.toString() +
                ", amountPrice=" + amountPrice +
                ", closeTime='" + closeTime + '\'' +
                '}');
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}


