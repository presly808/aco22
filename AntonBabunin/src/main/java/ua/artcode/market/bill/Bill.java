package ua.artcode.market.bill;


//import ua.artcode.market.creator.BillCreator;

import ua.artcode.market.product.Product;
import ua.artcode.market.salesman.Salesman;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {

    private static final int DEFAULT_SIZE = 20;

    private int id;
    private Product[] products;
    private Salesman salesman;
    private double amountPrice;
    private String closeTime;

    private boolean isClosed;
    private int firstFree;
    private int lastFree;

    private int addedProducts;

/*     This constructor use in app
    public Bill(int id, Product[] products, Salesman salesman,
            double amountPrice, String closeTime, boolean isClosed,
            int firstFree,
            int lastFree, int addedProduct) {
        BillCreator.createBill(salesman);
    }
*/

    //    This constructor created for test, isn't used in app
    public Bill(int id, Salesman salesman) {
        this.id = id;
        this.salesman = salesman;
        this.products = new Product[DEFAULT_SIZE];
        this.amountPrice = 0.0;
        this.closeTime = "";
        this.isClosed = false;
        this.firstFree = 0;
        this.lastFree = 1;
    }

    public Bill(int id, Product[] products, Salesman salesman,
                double amountPrice, String closeTime,
                boolean isClosed, int firstFree,
                int lastFree) {

        this.id = id;
        this.products = products;
        this.salesman = salesman;
        this.amountPrice = amountPrice;
        this.closeTime = closeTime;
        this.isClosed = isClosed;
        this.firstFree = firstFree;
        this.lastFree = lastFree;

    }

    public static boolean equals(Product[] products1, Product[] products2) {
        for (Product product1 : products1) {
            for (Product product2 : products2) {
                if (product1 != null && !product1.equals(product2))
                    return false;
            }
        }
        return true;
    }

    public boolean equals(Bill bill) {
        return bill != null && this.id == bill.id &&
                this.salesman.equals(bill.salesman) &&
                equals(this.getProducts(), bill.getProducts()) &&
                this.amountPrice == bill.amountPrice &&
                this.closeTime.equals(bill.closeTime) &&
                this.isClosed == bill.isClosed &&
                this.firstFree ==
                        bill.firstFree &&
                this.lastFree ==
                        bill.lastFree;
    }


    public void setAddedProducts(int number) {
        this.addedProducts = number;
    }

    public boolean addProduct(Product product) {
        if (!this.isClosed()) {
            if (product != null && product.getName() != null) {
                addProductlogic(product);
            }
            return true;
        }
        return false;
    }

    private boolean addProductlogic(Product product) {
        if (this.getProducts() == null) {
            setProducts();
        }
        this.getProducts()[this.firstFree] = product;
        this.firstFree = this.lastFree;
        for (int i = firstFree; i < this.getProducts().length;
             i++) {
            if (this.getProducts()[i] == null) {
                this.lastFree = ++i;
                return true;
            }
        }
        return false;
    }
    private void setProducts() {
        this.products = new Product[DEFAULT_SIZE];
        this.firstFree = 0;
        this.lastFree = 1;
    }

    public double closeBill() { //It can be void, but for test it set as double;
        SimpleDateFormat dF =
                new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
        this.setCloseTime(dF.format(new Date()));
        this.setIsClosed();
        return this.calculateAmountPrice();
    }

    private void setIsClosed() {
        this.isClosed = true;
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    private double calculateAmountPrice() {
        double amountPrice = 0.0;
        if (this.getProducts() != null) {
            for (Product product : this.getProducts()) {
                if (product != null) {
                    amountPrice += product.getPrice();
                }
            }
        }
        return amountPrice;
    }

    public boolean printBill() {
        System.out.printf("Bill: \n ID: %d \n", this.getId());
        for (Product product : this.getProducts()) {
            if (product != null) {
                product.printFullInfo();
            }
        }
        System.out.printf("AmountPrice: %.2f \n", this.getAmountPrice());
        System.out.printf("CloseTime: %s \n", this.getCloseTime());
        System.out.printf("You were serviced by %s \n", salesman.getFullName());
        return true;
    }


    public int getId() {
        return id;
    }

    public Product[] getProducts() {
        return products;
    }

//    public void setProducts(Product[] products) {
//        this.products = products;
//    }

    public Salesman getSalesman() {
        return salesman;
    }

//    public void setSalesman(Salesman salesman) {
//        this.salesman = salesman;
//    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getAddedProducts() {
        return this.addedProducts;
    }

}
