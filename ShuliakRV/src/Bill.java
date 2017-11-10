import java.util.Date;

public class Bill {

    private static final int DEFAULT_AMOUNT_PRODUCTS = 100;

    private static int seqId;
    private int id;
    private Product[] arr;
    private Salesman salesMan;
    private double amountPrice;
    private Date closeTime;
    private boolean isOpen = true;
    private int numProd;

    public Bill(Salesman salesMan) {
        seqId++;
        id = seqId;
        this.salesMan = salesMan;
        arr = new Product[DEFAULT_AMOUNT_PRODUCTS];
    }

    public Bill(Salesman salesMan, int amountProd) {
        this.id++;
        this.salesMan = salesMan;
        arr = new Product[amountProd];
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getId() {
        return id;
    }

    public int getNumProd() {
        return numProd;
    }

    public Salesman getSalesMan() {
        return salesMan;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void addProduct(Product p) {
        if ((isOpen) && (p != null))
            arr[numProd++] = p;
    }


    public String printBill() {

        String str = "";

        for (int i = 0; i < numProd; i++) {
            str += arr[i].printFullInfo();
        }

        str += String.format("ID: %s; Saler: %s; Time: %s; Sum: %s .", id, salesMan.getFullname(), closeTime.toString(), amountPrice);
        return str;
    }

    public void calculateAmountPrice() {
        amountPrice = 0;
        for (int i = 0; i < numProd; i++) {
            amountPrice += arr[i].price;
        }
    }

    public void closeBill() {
        if (numProd > 0) {
            calculateAmountPrice();
            closeTime = new Date();
            isOpen = false;
        }
    }
}


