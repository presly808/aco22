package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDB;
import ua.artcode.market.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppDBImpl implements IAppDB {

    private static AppDBImpl entity;

    private List<SalesMan> salesMansList;
    private List<Product> productsPriceList;
    private List<Terminal> terminalList;
    private List<Bill> billList;

    public final Logging logging;

    public final Generator generator;

    public final BillController billController;
    public final TerminalController terminalController;

    public final Statistics statistics;

    public AppDBImpl() throws IOException {

        this.salesMansList = new ArrayList<SalesMan>();
        this.productsPriceList = new ArrayList<Product>();
        this.terminalList = new ArrayList<Terminal>();
        this.billList = new ArrayList<Bill>();

        this.logging = Logging.getEntity();

        this.generator = new Generator();

        this.billController = new BillController();
        this.terminalController = new TerminalController();

        this.statistics = new Statistics();
    }

    public static AppDBImpl getEntity() throws IOException {

        if (entity == null) {

            synchronized (AppDBImpl.class) {
                if (entity == null) {
                    entity = new AppDBImpl();
                }
            }
        }
        return entity;
    }

    @Override
    public void remove(Object currentObject) throws IOException {

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
            logging.fixEvent("Object is not identified. Removing stopped!");
        }
    }

    public void clearList(Class currentClass) throws IOException {

        if (currentClass.equals(Terminal.class)) {
            terminalList.clear();
        }
        else if (currentClass.equals(Bill.class)) {
            billList.clear();
        }
        else if (currentClass.equals(Product.class)) {
            productsPriceList.clear();

        }
        else if (currentClass.equals(SalesMan.class)) {
            salesMansList.clear();
        }
        else{
            logging.fixEvent("Class is not identified. Operation stopped!");
        }
    }

    @Override
    public List<? extends Object> getAll(Class currentClass) throws IOException {

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
            logging.fixEvent("Class is not identified. Operation stopped!");

            return null;
        }
    }

    @Override
    public SalesMan findSalesMan(String login, String pass) throws IOException {

        for (SalesMan itemSalesMan: this.salesMansList) {

            if (itemSalesMan.getLogin().equals(login) && itemSalesMan.getPass().equals(pass) ) {
                return itemSalesMan;
            }
        }

        logging.fixEvent("SalesMan was not found!");

        return null;
    }

    @Override
    public Product findProductByCode(int productCode) throws IOException {

        for (Product itemProduct: this.productsPriceList) {

            if (itemProduct.code == productCode) {
                return itemProduct;
            }
        }

        logging.fixEvent("It is not possible to identify the product by code <<" + productCode + ">>");

        return null;
    }

    public ProductBill findProductBillByCode(Bill currentBill, int productCode) throws IOException {

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            if (itemProductBill.getProductCode() == productCode) {
                return itemProductBill;
            }
        }

        logging.fixEvent("It is not possible to identify the product by code <<" + productCode + ">>");

        return null;
    }

    public SalesMan createSalesMan(String fullName,String login,String pass){

        SalesMan salesMan = new SalesMan(fullName, login, pass);
        this.salesMansList.add(salesMan);

        return salesMan;
    }

    public List<SalesMan> getSalesManList() throws IOException { return this.salesMansList; }

    public Product createProduct(int code, String name, double price){

        Product product = new Product(code, name, price);
        this.productsPriceList.add(product);

        return product;
    }

    public Terminal createTerminal() throws IOException {

        Terminal terminal = new Terminal();
        this.terminalList.add(terminal);

        return terminal;
    }

    public Bill createBill(Terminal currentTerminal) throws IOException {
        return new Bill(currentTerminal,
                AppDBImpl.getEntity().getQuantityBillsTerminal(currentTerminal) + 1);
    }

    public static void addProductToBill(Bill currentBill, int productCode, int quantity) throws IOException {

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
                    new ProductBill(AppDBImpl.getEntity().findProductByCode(productCode).code, quantity);
            currentBill.addProductBill(newProductBill);

            //updateAmountPriceToBill(currentBill);
            currentBill.setQuantityGoods(currentBill.getQuantityGoods() + 1);
        }
    }

    public void changeProductToBill(Bill currentBill, int productCode, int quantity){

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

    public void updateAmountPriceToBill(Bill currentBill) throws IOException {

        currentBill.setAmountPrice(0);

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            Product currentProduct = findProductByCode(itemProductBill.getProductCode());

            currentBill.setAmountPrice(Math.rint(currentBill.getAmountPrice()
                    + currentProduct.price*itemProductBill.getProductQuontity()*100)/100);
        }
    }

    public int getQuantityBillsTerminal(Terminal terminal) throws IOException {

        return terminal.getBillsTerminal().size();
    }

    public void saveClosedBill(Bill currentBill) throws IOException {

        if (currentBill.closed) {

            updateAmountPriceToBill(currentBill);
            billList.add(currentBill);
        }
        else {
            logging.fixEvent("Bill is not closed, recording is not possible!");
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


    public static class Statistics {

        public void printPriceOfProducts(List<Product> productsPrice){

            System.out.println("\n" + getPriceOfProductsForPrint(productsPrice));
        }

        public String getPriceOfProductsForPrint(List<Product> productsPrice) {

            String textMessage = "";

            textMessage += "\nPRICE OF GOODS\n" + "Code\t\t"+"Goods\t\t"+"Price";

            for (Product itemProduct: productsPrice) {

                textMessage += "\n" + itemProduct.code +
                        "\t\t" + itemProduct.name +
                        "\t\t" + itemProduct.price;
            }

            return textMessage+"\n";
        }

        public String getBillHeadInfoForPrint(Bill currentBill){

            String message = "Bill №" + currentBill.getCode() +
                    "/ quontity of goods - " + currentBill.getQuantityGoods() +
                    "/ Amount - " + currentBill.getAmountPrice() +
                    "/ SalesMan - " + currentBill.getSalesMan().getFullName() +
                    "/ Closed - " + currentBill.closed +
                    "/ createTime - " + (currentBill.createTime == null ? "" : currentBill.createTime) +
                    "/ CloseTime - " + (currentBill.closeTime == null ? "" : currentBill.closeTime);

            return message;
        }

        public String getBillProductsForPrint(Bill currentBill) throws IOException {

            String message = "Code\t"+"Goods\t"+"Price\t"+"Quantity\n";

            for (ProductBill itemProductBill: currentBill.getProductsBill()) {

                Product currentProduct = AppDBImpl.getEntity().findProductByCode(itemProductBill.getProductCode());

                message += "" + currentProduct.code +
                        "\t\t" + currentProduct.name +
                        "\t\t" + currentProduct.price +
                        "\t\t" + itemProductBill.getProductQuontity()+"\n";
            }

            return message;
        }

        public void printBill(Bill currentBill) throws IOException {

            System.out.println(getBillHeadInfoForPrint(currentBill));
            System.out.println(getBillProductsForPrint(currentBill));
        }

        public void showInfo(Terminal currentTerminal) throws IOException {

            System.out.println("\n\n\n STATISTICS OF THE WORK OF THE STORE");

            AppDBImpl appDB = AppDBImpl.getEntity();

            for (Bill itemBill: appDB.getBillsTerminal(currentTerminal)) {
                appDB.statistics.printBill(itemBill);
            }
        }

    }
}
