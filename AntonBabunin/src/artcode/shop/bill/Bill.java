package artcode.shop.bill;

import artcode.shop.salesman.Salesman;
import artcode.shop.product.Product;
import artcode.shop.creator.BillCreator;

public class Bill {

    private static final int DEFAULT_SIZE = 20;

    private int id;
    private Product[] products;
    private Salesman salesman;
    private double amountPrice;
    private String closeTime;

    private boolean isClosed;
    private int firstFreePositionAtProducts;
    private int nextFreePositionAtProducts;

    public Bill(Salesman salesman) {
        BillCreator.createBill(salesman);
    }


//    This constructor created for test, isn't used in app
    public Bill(int id, Salesman salesman) {
        this.id = id;
        this.salesman = salesman;
        this.products = new Product[20];
        this.amountPrice = 0.0;
        this.closeTime = "";
        this.isClosed = false;
        this.firstFreePositionAtProducts = 0;
        this.nextFreePositionAtProducts = 1;
    }

    public Bill(int id, Product[] products, Salesman salesman, double amountPrice, String closeTime,
                boolean isClosed, int firstFreePositionAtProducts, int nextFreePositionAtProducts) {

        this.id = id;
        this.products = products;
        this.salesman = salesman;
        this.amountPrice = amountPrice;
        this.closeTime = closeTime;
        this.isClosed = isClosed;
        this.firstFreePositionAtProducts = firstFreePositionAtProducts;
        this.nextFreePositionAtProducts = nextFreePositionAtProducts;

    }
    public static boolean equals (Product[] products1, Product[] products2) {
        for (Product product1 : products1) {
            for (Product product2 : products2) {
                if (product1 != null && !product1.equals(product2)) return false;
            }
        }
        return true;
    }


    public boolean equals (Bill bill) {
        return this.id == bill.id && this.salesman.equals(bill.salesman) && equals(this.getProducts(), bill.getProducts()) &&
                this.amountPrice == bill.amountPrice && this.closeTime.equals(bill.closeTime) &&
                this.isClosed == bill.isClosed && this.firstFreePositionAtProducts == bill.firstFreePositionAtProducts &&
                this.nextFreePositionAtProducts == bill.nextFreePositionAtProducts;
    }












    public void addProduct (Product product) {
        if (!this.isClosed()) {
                if (product != null && product.getName() != null) {

                    if (this.getProducts() == null || this.getProducts().equals(null)) {
                        setProducts();
                    }
                    this.getProducts()[this.firstFreePositionAtProducts] = product;
                    this.firstFreePositionAtProducts = this.nextFreePositionAtProducts;

                    for (int i = firstFreePositionAtProducts + 1; i < this.getProducts().length; i++) {
                        if (this.getProducts()[i] == null) {
                            this.nextFreePositionAtProducts = i;
                            break;
                        }
                    }
                }
        }
    }

    private void setProducts() {
        this.products = new Product[DEFAULT_SIZE];
        this.firstFreePositionAtProducts = 0;
        this.nextFreePositionAtProducts = 1;
    }

    public double closeBill () { //It can be void, but for test it set as double;
        this.setIsClosed();
        return this.calculateAmountPrice();
    }

    private void setIsClosed() {
        this.isClosed = true;
    }
    public boolean isClosed() {
        return this.isClosed;
    }

    private double calculateAmountPrice(){
        double amountPrice = 0.0;
        if (products != null) {
            for (Product product : products) {
                if (product != null) {
                    amountPrice += product.getPrice();
                }
            }
        }
        return amountPrice;
    }

    private boolean printBill(){
        return true;
    }



    public int getId() {
        return id;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
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
