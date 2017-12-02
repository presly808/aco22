package controllers;

import exceptions.BillNotFoundException;
import exceptions.UnableToAddProductToBillException;
import exceptions.UnableToCalculatePriceException;
import exceptions.UnableToCloseBillException;
import interfaces.IBillLogic;
import models.Bill;
import models.Product;
import utils.TerminalUtils;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BillController implements IBillLogic {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private Set<Bill> billSet;
    private double amountPrice;

    public BillController() {
        billSet = new HashSet<>();
    }

    public Product addProductToBill(Bill bill, String productName)
                                throws UnableToAddProductToBillException {

        if (bill == null || productName.isEmpty()) {
            throw new UnableToAddProductToBillException("Product can not be created");
        }
        Product product = new Product(TerminalUtils.longIdGenerator(),
                            productName, new Random().nextDouble());
        bill.setProducts(product);
        return product;
    }

    public void closeBill(Bill bill) throws UnableToCloseBillException {

        if (bill == null) {
            throw new UnableToCloseBillException("Bill is null");
        }

        bill.setCloseTime(new SimpleDateFormat(
                DATE_FORMAT).toString());
    }

    public double calculateAmountPrice(Bill bill) throws UnableToCalculatePriceException {
        List<Product> products = bill.getProducts();
        if (products.isEmpty() || products == null) {
            throw new UnableToCalculatePriceException("Empty product list");
        }
        double amountPrice = products.stream().mapToDouble(Product::getPrice).sum();
        this.amountPrice = amountPrice;
        return amountPrice;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public String printBill(Bill currentBill) throws BillNotFoundException {
        if (currentBill == null) {
            throw new BillNotFoundException("Incorrect Bill");
        }
        return String.format("{id:%d, products:%s, salesman:%s, " +
                        "amountPrice%d, closeTime%s}" , currentBill.getId(),
                currentBill.getProducts(), currentBill.getSalesman(),
                getAmountPrice(), currentBill.getCloseTime());
    }

    public Set<Bill> getBillSet() {
        return billSet;
    }
}