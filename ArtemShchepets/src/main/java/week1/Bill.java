package week1;

public class Bill {

    private static final int DEFAULT_SIZE_OF_LIST = 20;

    private Product[] billList;
    private int actualSizeOfList = 0;

    private int id;

    private double billCost = 0;
    private Time time;
    private Seller seller;

    private boolean isClosed = false;

    public Bill() {
    }

    public Bill(Seller seller, Time time) {
        this.billList = new Product[DEFAULT_SIZE_OF_LIST];
        this.time = time;
        this.seller = seller;
        isClosed = true;
    }

    public Bill(Seller seller) {
        this.billList = new Product[DEFAULT_SIZE_OF_LIST];
        this.time = new Time(00,00,00);
        this.seller = seller;
    }


    public void calculateBill() {


        if (billList != null && actualSizeOfList > 0) {

            for (int i = 0; i < actualSizeOfList; i++) {
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

    public int getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }

    public static int getDefaultSizeOfList() {
        return DEFAULT_SIZE_OF_LIST;
    }

    public int getActualSizeOfList() {
        return actualSizeOfList;
    }

    public void setActualSizeOfList(int actualSizeOfList) {
        this.actualSizeOfList = actualSizeOfList;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setTime(Time time) {
        if (!isClosed) {
            this.time = time;
            this.isClosed = true;
        } else System.out.println("Sorry, bill is closed!");
    }

    public void setId(int id) {
        this.id = id;
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

                billList[actualSizeOfList] = product;
                actualSizeOfList++;
            }
        } else System.out.println("Sorry, bill is closed!");
    }

    public String showInfo() {

        String resultString = "***BILL***\n";

        for (int i = 0; i < actualSizeOfList; i++) {
            resultString += billList[i].showInfo() + "\n";
        }

        resultString += time.toString() + "\n" + seller.toString();

        return   isClosed ? resultString + "\n***BILL IS CLOSED***" : resultString + "\n***BILL IS OPENED***";
    }

    public void closeBill() {
        isClosed = true;
    }
}
