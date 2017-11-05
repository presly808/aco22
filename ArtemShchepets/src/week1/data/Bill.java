package week1.data;

public class Bill {

    private Product[] billList;
    private int productListSize = 0;

    private double billCost = 0;
    private Time time;
    private Seller seller;

    private boolean isClosed = false;

    public Bill(Product[] billList, Seller seller, Time time) {
        this.billList = billList;
        this.time = time;
        this.seller = seller;
    }

    public void calculateBill() {


        if (billList != null && productListSize > 0) {

            for (int i = 0; i < billList.length; i++) {
                billCost += billList[i].getPrice();
            }


        }
    }

    public boolean isClosed() {
        return isClosed;
    }

    public Product[] getBillList() {
        return billList;
    }

    public double getBillCost() {
        return billCost;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setTime(Time time) {
        if (!isClosed) {
            this.time = time;
        } else System.out.println("Sorry, bill is closed!");
    }

    public void setSeller(Seller seller) {
        if (!isClosed) {
            this.seller = seller;
        } else System.out.println("Sorry, bill is closed!");
    }

    public void addProduct(Product product) {

        if (!isClosed) {
            if (product == null) {
                System.out.println("ENTER REAL PRODUCT!");
            } else {
                billList[productListSize] = product;
                productListSize++;
            }
        } else System.out.println("Sorry, bill is closed!");
    }

    public String showInfo() {

        String resultString = "***BILL***\n";

        for (int i = 0; i < billList.length; i++) {
            resultString += billList[i].showInfo() + "\n";
        }

        resultString += time.toString() + "\n" + seller.toString();

        return   (isClosed) ? resultString + "\n***BILL IS CLOSED***" : resultString + "\n***BILL IS OPENED***";
    }

    public void closeBill() {
        isClosed = true;
    }
}
