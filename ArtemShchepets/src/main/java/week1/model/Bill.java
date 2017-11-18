package week1.model;

import java.util.Arrays;

import static week1.utils.Utils.getCurrentDate;

public class Bill{

    private static final int DEFAULT_SIZE_OF_LIST = 20;

    private Product[] billList;
    private int actualSizeOfList = 0;

    private int id;

    private double billCost = 0;
    private String closeTime;
    private Seller seller;

    private String creationDate;

    private boolean isClosed = false;

    public Bill() {
    }

    public Bill(Seller seller) {
        this.billList = new Product[DEFAULT_SIZE_OF_LIST];
        this.creationDate = getCurrentDate();
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

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
        setClosed(true);
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBillList(Product[] billList) {
        this.billList = billList;
        actualSizeOfList = billList.length;
    }

    public boolean addProduct(Product product) {

        if (!isClosed) {
            if (product == null) {
                System.out.println("ENTER REAL PRODUCT!");
                return false;
            } else {

                billList[actualSizeOfList] = product;
                actualSizeOfList++;
                return true;
            }
        } else  {
            System.out.println("Sorry, bill is closed!");
            return false;
        }
    }

    public String showInfo() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || obj.getClass() != Bill.class) return false;

        Bill other = (Bill) obj;

        if ((billList != null && Arrays.equals(billList,other.getBillList())) &&
                billCost == other.getBillCost() &&
                (closeTime != null && closeTime.equals(other.getCloseTime())) &&
                (creationDate != null && creationDate.equals(other.getCreationDate()))) return true;

        return false;
    }

    @Override
    public String toString() {
        String resultString = "***BILL***\n";

        for (int i = 0; i < actualSizeOfList; i++) {
            resultString += billList[i].showInfo() + "\n";
        }

        resultString +=  creationDate + "\n" + seller.toString();

        return isClosed ? resultString + "\n***BILL IS CLOSED***" : resultString + "\n***BILL IS OPENED***";
    }
}
