/**
 * Created by ENIAC on 05.11.2017.
 */
public class Bill {

    private static final int SIZE_OF_LIST = 10;

    private Product[] products;
    private double AmountPrice;
    private Salesman salesman;
    private String closeTimel;

    private int billId;

    private boolean isclosed = false;

    private int productsCounter = 0;

    public Bill() {

    }

    public Bill(double amountPrice, Salesman salesman, String closeTimel, int billId) {
        this.products = new Product[SIZE_OF_LIST];
        this.AmountPrice = amountPrice;
        this.salesman = salesman;
        this.closeTimel = closeTimel;
        this.billId = billId;
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

    public boolean isclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        isclosed = isclosed;
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

    public String getCloseTimel() {
        return closeTimel;
    }

    public void setCloseTimel(String closeTimel) {
        this.closeTimel = closeTimel;
    }


//    Methods


    public void addProduct(Product product) {
        if (product == null) {
        } else {
            products[productsCounter] = product;
            productsCounter++;
        }
    }

    public double calculateAmountPrice(Bill bill) {
        if ((bill == null) || (productsCounter == 0)) {
        } else
            for (int i = 0; i < productsCounter; i++) {
                AmountPrice += products[i].getPrice();
            }
        return AmountPrice;
    }

    public String printAllProducts() {
        String allproducts = "";
        if (productsCounter == 0) {
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
                        "%s\nAmount Price = %.2f\nSeller: %s\nTime: %s",
                printAllProducts(), AmountPrice, salesman.getFullname(), closeTimel);
    }
}

