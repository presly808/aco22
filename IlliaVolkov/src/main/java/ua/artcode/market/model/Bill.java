package ua.artcode.market.model;

import java.util.ArrayList;
import java.util.Date;

public class Bill {

    private int code;
    //private int[][] products;
    private ArrayList<ProductBill>  productsBill;
    private int quantityGoods;
    private double amountPrice;
    private SalesMan salesMan;
    public boolean closed;
    public Date closeTime;
    final Product[] productList;


    //public Bill(int code, int countProducts, String salesManName, Product[] productList){
    public Bill(int code, String salesManName, Product[] productList){

        this.code = code;
        //this.products = new int[countProducts][2];
        this.productsBill = new ArrayList<>();
        this.salesMan = new SalesMan(salesManName);

        this.productList = productList;
    }

    public int getCode() {
        return code;
    }

    /*public int[][] getProducts() {
        return products;
    }*/

    public ArrayList<ProductBill> getProductsBill() {
        return productsBill;
    }

    /*public void setProducts(int s, int c, int num) {
        this.products[s][c] = num;
    }*/

    public void setProductsBill(ProductBill productsBill) {
        this.productsBill.add(productsBill);
    }

    public void setQuantityGoods(int quantityGoods) {
        this.quantityGoods = quantityGoods;
    }

    public int getQuantityGoods(int quantityGoods) {
        return quantityGoods;
    }

    public double getAmountPrice(double amountPrice) {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public Product[] getProductList() {
        return productList;
    }

    public SalesMan getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(SalesMan salesMan) {
        this.salesMan = salesMan;
    }

    public int getQuantityGoods(){
        return this.quantityGoods;
    }

    public double getAmountPrice(){
        return this.amountPrice;
    }
}
