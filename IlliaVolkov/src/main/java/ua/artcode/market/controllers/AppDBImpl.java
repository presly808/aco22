package ua.artcode.market.controllers;

import ua.artcode.market.Util.Generator;
import ua.artcode.market.interfaces.IAppDB;
import ua.artcode.market.interfaces.IStatistics;
import ua.artcode.market.model.*;

import java.util.ArrayList;
import java.util.List;

public class AppDBImpl implements IAppDB, IStatistics{

    public Generator generator;

    private List<SalesMan> salesMansList;
    private List<Product> productsPriceList;
    private List<Terminal> terminalList;
    private List<Bill> billList;

    public final BillController billController;
    public final TerminalController terminalController;


    public AppDBImpl() {
        this.generator = new Generator(this);
        this.salesMansList = new ArrayList<SalesMan>();
        this.productsPriceList = new ArrayList<Product>();
        this.terminalList = new ArrayList<Terminal>();
        this.billList = new ArrayList<Bill>();
        this.billController = new BillController();
        this.terminalController = new TerminalController();
    }

    @Override
    public void remove(Object currentObject){

        if (currentObject.getClass() == Terminal.class) {
            terminalList.remove(currentObject);
        }
        else if (currentObject.getClass() == Bill.class) {
            billList.remove(currentObject);
        }
        else if (currentObject.getClass() == Product.class) {
            productsPriceList.remove(currentObject);
        }
        else if (currentObject.getClass() == SalesMan.class) {
            salesMansList.remove(currentObject);
        }
        else{
            System.out.println("Object is not identified. Removing stopped!");
        }
    }

    @Override
    public List<? extends Object> getAll(Class currentClass){

        if (currentClass.equals(Terminal.class)) {
            return terminalList;
        }
        else if (currentClass.equals(Bill.class)) {
            return billList;
        }
        else if (currentClass.equals(Product.class)) {
            return productsPriceList;

        }
        else if (currentClass.equals(SalesMan.class)) {
            return salesMansList;
        }
        else{
            System.out.println("Object is not identified. Operation stopped!");

            return null;
        }
    }

    @Override
    public SalesMan findSalesMan(String login, String pass) {

        for (SalesMan itemSalesMan: this.salesMansList) {

            if (itemSalesMan.getLogin().equals(login) && itemSalesMan.getPass().equals(pass) ) {
                return itemSalesMan;
            }
        }

        System.out.println("SalesMan was not found!");

        return null;
    }

    @Override
    public Product findProductByCode(int productCode) {

        for (Product itemProduct: this.productsPriceList) {

            if (itemProduct.code == productCode) {
                return itemProduct;
            }
        }

        System.out.println("It is not possible to identify the product by code <<" + productCode + ">>");

        return null;
    }

    public SalesMan createSalesMan(String fullName,String login,String pass){

        SalesMan salesMan = new SalesMan(fullName, login, pass);
        this.salesMansList.add(salesMan);

        return salesMan;
    }

    public Product createProduct(int code, String name, double price){

        Product product = new Product(code, name, price);
        this.productsPriceList.add(product);

        return product;
    }

    public Terminal createTerminal(){

        Terminal terminal = new Terminal(this);
        this.terminalList.add(terminal);

        return terminal;
    }

    public Bill createBill(Terminal currentTerminal) {
        return new Bill(currentTerminal,
                this.terminalController.getQuantityBillsTerminal(currentTerminal) + 1);
    }

    public static void addProductToBill(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            if (itemProductBill.getProductCode() == productCode && !productProcessed) {

                itemProductBill.setProductQuontity(itemProductBill.getProductQuontity() + quantity);

                //updateAmountPriceToBill(currentBill);

                productProcessed = true;
            }
        }

        if (!productProcessed) {
            ProductBill newProductBill =
                    new ProductBill(currentBill.terminal.currentAppDBImpl.findProductByCode(productCode).code, quantity);
            currentBill.addProductBill(newProductBill);

            //updateAmountPriceToBill(currentBill);
            currentBill.setQuantityGoods(currentBill.getQuantityGoods() + 1);
        }
    }

    public static void changeProductToBill(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        List<ProductBill> currentProductsBill = currentBill.getProductsBill();
        for (ProductBill itemProductBill: currentProductsBill) {

            if (itemProductBill.getProductCode() == productCode && !productProcessed) {

                if (quantity == 0) {

                    currentProductsBill.remove(itemProductBill);
                    currentBill.setQuantityGoods(currentBill.getQuantityGoods() - 1);
                    //updateAmountPriceToBill(currentBill);
                }
                else {
                    itemProductBill.setProductQuontity(quantity);
                    //updateAmountPriceToBill(currentBill);
                }
                productProcessed = true;
            }
        }
    }

    public void updateAmountPriceToBill(Bill currentBill){

        currentBill.setAmountPrice(0);

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            Product currentProduct = findProductByCode(itemProductBill.getProductCode());

            currentBill.setAmountPrice(Math.rint(currentBill.getAmountPrice()
                    + currentProduct.price*itemProductBill.getProductQuontity()*100)/100);
        }
    }

    public int getQuantityBillsTerminal(Terminal terminal) {

        return terminal.getBillsTerminal().size();
    }

    public void saveClosedBill(Bill currentBill) {

        if (currentBill.closed) {

            updateAmountPriceToBill(currentBill);
            billList.add(currentBill);
        }
        else {
            System.out.println("Bill is not closed, recording is not possible!");
        }
    }

    public List<Bill> getBillsTerminal(Terminal currentTerminal) {

        List<Bill> currentBillsList = new ArrayList<Bill>();

        for (Bill itemBill: billList) {
            if (itemBill.terminal.equals(currentTerminal)) {
                currentBillsList.add(itemBill);
            }
        }
        return currentBillsList;
    }

    public List<Product> getProductsPrice() { return this.productsPriceList; }


}
