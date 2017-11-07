package implementation;

import Utils.*;
import controllers.BillController;
import models.Product;
import models.Salesman;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bill implements BillController{

    private long id;
    private List<Product> products;
    private Salesman salesman;
    private double amountPrice;

    private String closeTime;

    public Bill() {
        this.id = Utils.longIdGenerator();
        this.products = new ArrayList();
        this.salesman = new Salesman(StringGenerator.generateName(), StringGenerator.generateName());
    }

    public Product addProduct(String name){
        Product product = new Product(Utils.longIdGenerator(), name, new Random().nextDouble());
        products.add(product);
        return product;
    }

    public void closeBill() {
        this.closeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").toString();
        products = null;
    }

    public double calculateAmountPrice() {
        amountPrice = 0;

        for (Product product : products) {
            amountPrice += product.getPrice();
        }

        return amountPrice;
    }

    public void printBill() {
        System.out.println("Bill{" +
                "id=" + id +
                ", products=" + products +
                ", salesman=" + salesman.toString() +
                ", amountPrice=" + amountPrice +
                ", closeTime='" + closeTime + '\'' +
                '}');
    }
}


