import java.util.Date;

public class Bill {

    private static int id;
    private Product[] arr;
    private Salesman salesMan;
    private double amountPrice;
    private Date closeTime;
    private boolean isOpen = true;
    private int numProd;

    public Bill(Salesman salesMan, int countProd) {
        this.id++;
        this.salesMan = salesMan;
        arr = new Product[countProd];
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

        str += String.format("ID: %s; Saler: %s; Time: %s; Sum: %s .",id,salesMan.getFullname(), closeTime.toString(), amountPrice);
        return str;
    }

    public void calculateAmountPrice() {
        amountPrice = 0;
        for (int i = 0; i < numProd; i++) {
            amountPrice += arr[i].price;
        }
    }

    public void closeBill() {

        calculateAmountPrice();
        closeTime = new Date();
        isOpen = false;

    }
}
