package ua.artcode.market.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {

    private int code;
    //private int[][] products;
    private List<ProductBill>  productsBill;
    private int quantityGoods;
    private double amountPrice;
    private SalesMan salesMan;
    public boolean closed;
    public Date createTime;
    public Date closeTime;
    final List<Product> productsPrice;


    //public Bill(int code, int countProducts, String salesManName, Product[] productList){
    public Bill(int code, SalesMan salesMan, List<Product> productsPrice){

        this.code = code;
        this.createTime = new Date();
        this.productsBill = new ArrayList<>();
        this.salesMan = salesMan;
        this.productsPrice = productsPrice;
    }

    public int getCode() {
        return code;
    }

    public List<ProductBill> getProductsBill() { return productsBill; }

    public void setProductsBill(ProductBill productsBill) { this.productsBill.add(productsBill); }

    public List<Product> getProductsPrice() { return productsPrice; }

    public int getSizeProductsBill(){
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
        return salesMan;
    }

    public void setSalesMan(SalesMan salesMan) {
        this.salesMan = salesMan;
    }
}
