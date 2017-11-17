package week1;
/**
 * Created by ENIAC on 05.11.2017.
 */
public class Bill {

    private Product[] products;
    private double AmountPrice;
    private Salesman salesman;
    private Time time;

    private int billId = 0;

    private boolean isclosed = false;

    private static final int SIZE_OF_LIST = 10;

    private int productsCounter = 0;

    public Bill() {

    }

    public Bill(double amountPrice, Salesman salesman, Time time, int billId) {
        this.products = new Product[SIZE_OF_LIST];
        this.AmountPrice = amountPrice;
        this.salesman = salesman;
        this.time = time;
        this.billId = billId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public static int getSizeOfList() {
        return SIZE_OF_LIST;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public int getProductsCounter() {
        return productsCounter;
    }

    public void setProductsCounter(int productsCounter) {
        this.productsCounter = productsCounter;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public double getAmountPrice() {
        return AmountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        AmountPrice = amountPrice;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }


//    Methods


    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("product is null");
        } else {
            products[productsCounter] = product;
            productsCounter++;
        }
    }

    public double calculateAmountPrice(Bill bill) {
        if ((bill == null) || (productsCounter == 0)) {
            System.out.println("bill is null or there no one product to calc");
        } else
            for (int i = 0; i < productsCounter; i++) {
                AmountPrice += products[i].getPrice();
            }
        return AmountPrice;
    }

    public String printAllProducts() {
        String allproducts = "";
        if (productsCounter == 0) {
            System.out.println("no product to print");
        } else
            for (int i = 0; i < productsCounter; i++) {
                allproducts += products[i].printFullInfo() + "\n";
            }
        return allproducts;
    }

    public String printBillInfo() {
        return String.format("              BiLL  \n" +
                        "Title           Price    Barcode\n" +
                        "--------------------------------\n" +
                        "%s\nAmount Price = %.2f\nSeller: %s\n%s",
                printAllProducts(), AmountPrice, salesman.getFullname(), getTime().closeTime);
    }

}

