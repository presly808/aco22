import java.util.Date;

public class Bill {

    private int id;
    private Product[] arr;
    private Salesman salesman;
    private double amountPrice;
    private Date closeTime;
    private boolean isopen = true;
    private int numprod;

    public Bill(int id, Salesman salesman, int numprod) {
        this.id = id;
        this.salesman = salesman;
        this.numprod = numprod;
        arr = new Product[numprod];
        numprod = 0;
    }

    public void addProduct(Product p) {
        if ((isopen)&&(p!=null))
            arr[numprod++] = p;
    }

    public String showInfo() {

        String str = "";

        for (int i = 0; i < numprod; i++) {
            str += arr[i].printFullInfo();
        }

        str += "Saler: " + salesman.getFullname() + "; " + "Time: " + closeTime.toString() + "; " + "Sum: " + amountPrice + ".";
        return str;
    }

    public void closeBill() {

        for (int i = 0; i < numprod; i++) {
            amountPrice += arr[i].price;
        }

        isopen = false;

    }
}
