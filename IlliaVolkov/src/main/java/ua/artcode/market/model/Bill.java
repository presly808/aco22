package ua.artcode.market.model;

import ua.artcode.market.controllers.AppDBImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {

    private int code;
    public final Terminal terminal;
    private List<ProductBill>  productsBill;
    private int quantityGoods;
    private double amountPrice;
    public boolean closed;
    public Date createTime;
    public Date closeTime;

    public Bill(Terminal currentTerminal, int code){

        this.terminal = currentTerminal;

        this.code = code;
        this.createTime = new Date();
        this.productsBill = new ArrayList<>();

    }

    public int getCode() {
        return code;
    }

    public List<ProductBill> getProductsBill() { return this.productsBill; }

    public void addProductBill(ProductBill productBill) { this.productsBill.add(productBill); }

    public int getQuantityProductsBill(){
        return this.productsBill.size();
    }

    public int getQuantityGoods(){
        return this.quantityGoods;
    }

    public void setQuantityGoods(int quantityGoods) {
        this.quantityGoods = quantityGoods;
    }

    public double getAmountPrice(){ return this.amountPrice; }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public SalesMan getSalesMan() {
        return this.terminal.getSalesMan();
    }
}
