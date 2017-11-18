package week1.controller;

import week1.comparators.CreationDateComparator;
import week1.interfaces.IBill;
import week1.model.Bill;
import week1.model.Product;

import java.util.Arrays;

import static week1.utils.Utils.getCurrentDate;

public class BillController implements IBill {

    private static final int DEFAULT_SIZE_OF_BILL_ARRAY = 10;

    private int actualSizeOfBills;
    private int currentBillIndex = -1;
    private Bill[] bills;

    public BillController() {
    }

    public BillController(Bill[] bills) {
        this.bills = makeAnActualBillArray(bills);
        this.actualSizeOfBills = this.bills.length;
        currentBillIndex = actualSizeOfBills - 1;
    }

    public int getActualSizeOfBills() {
        return actualSizeOfBills;
    }

    public Bill[] getBills() {
        return bills;
    }

    public int getCurrentBillIndex() {
        return currentBillIndex;
    }

    public void setBills(Bill[] bills) {

        this.bills = makeAnActualBillArray(bills);
        if (bills != null) {
            this.actualSizeOfBills = bills.length;
        }
        this.currentBillIndex = actualSizeOfBills - 1;

    }

    public void setActualSizeOfBills(int actualSizeOfBills) {
        this.actualSizeOfBills = actualSizeOfBills;
    }

    public void setCurrentBillIndex(int currentBillIndex) {
        this.currentBillIndex = currentBillIndex;
    }

    private Bill[] makeAnActualBillArray(Bill[] inputBillArray) {

        if (inputBillArray == null) return null;

        Bill[] additionalArray = new Bill[inputBillArray.length];
        int additionalArrayCounter = 0;

        for (int i = 0; i < inputBillArray.length; i++) {
            if (inputBillArray[i] != null) {
                additionalArray[additionalArrayCounter] = inputBillArray[i];
                additionalArray[additionalArrayCounter].setId(additionalArrayCounter);
                additionalArray[additionalArrayCounter].calculateBill();
                additionalArrayCounter++;
            }
        }

        return Arrays.copyOf(additionalArray, additionalArrayCounter);
    }

    public boolean createBill(Bill newBill) {

        // check if salesman is signed in

        if (newBill == null) {
            System.out.println("Bill is null.");
            return false;
        } else {

            //add new bill to the terminal
            if (currentBillIndex == -1) {

                bills = new Bill[DEFAULT_SIZE_OF_BILL_ARRAY];

                bills[actualSizeOfBills] = newBill;
                actualSizeOfBills++;
                currentBillIndex = actualSizeOfBills - 1;

                return true;

            } else {

                if (actualSizeOfBills == bills.length) {

                    Bill[] newBillsList;
                    newBillsList = Arrays.copyOf(bills, bills.length * 3 / 2);
                    newBillsList[actualSizeOfBills] = newBill;
                    this.bills = newBillsList;

                    actualSizeOfBills++;
                    currentBillIndex = actualSizeOfBills - 1;

                    return true;
                } else {

                    bills[actualSizeOfBills] = newBill;

                    actualSizeOfBills++;
                    currentBillIndex = actualSizeOfBills - 1;

                    return true;
                }
            }
        }
    }

    public boolean closeAllPreviousBills() {

        if (bills != null) {
            for (int i = 0; i < actualSizeOfBills - 1; i++) {
                if (!bills[i].isClosed()) bills[i].setCloseTime(getCurrentDate());
            }

            return true;
        }

        return false;
    }

    public boolean addProductToBill(Product newProduct) {

        if (newProduct == null) {
            System.out.println("Product is null");
            return false;
        } else {

            if (currentBillIndex == -1) {
                System.out.println("There aren't any bills in terminal!");
                return false;

            } else {

                if (bills[actualSizeOfBills - 1].isClosed()) {
                    System.out.println("You can't add products. Bill is closed!");
                    return false;

                } else return bills[actualSizeOfBills-1].addProduct(newProduct);

            }
        }
    }

    public boolean closeAndSaveBill() {

        if (currentBillIndex == -1) {
            System.out.println("Sorry, there aren't any bills in the terminal!");
            return false;
        } else {

            if (bills[currentBillIndex].isClosed()) {
                System.out.println("Sorry, this bill is already closed!");
                return false;
            } else {
                bills[currentBillIndex].setCloseTime(getCurrentDate());
                return true;
            }
        }
    }

    public Bill findBillById(int searchingId) {

        if (searchingId <= -1 || currentBillIndex == -1) return null;

        for (int i = 0; i < actualSizeOfBills; i++) {

            if (bills[i].getId() == searchingId) return bills[i];
        }

        return null;
    }

    public Bill[] filter(String startDate, String endDate, CreationDateComparator comparator) {

        if (bills == null || startDate.compareTo(endDate) > 0) return null;

        Arrays.sort(bills, comparator);

        return filteredArray(startDate, endDate);
    }

    private Bill[] filteredArray(String startDate, String endDate) {

        int startCopyIndex = findStartIndexForFilter(startDate);
        int endCopyIndex = findEndIndexForFilter(endDate);

        if (startCopyIndex != -1 && endCopyIndex != -1)
            return Arrays.copyOfRange(bills, startCopyIndex, endCopyIndex);
        else return null;
    }

    private int findEndIndexForFilter(String endDate) {

        for (int i = 0; i < actualSizeOfBills; i++) {
            if (i != actualSizeOfBills - 1) {
                if (bills[i].getCreationDate().compareTo(endDate) > 0) return i - 1;
            } else {
                if (bills[i].getCreationDate().compareTo(endDate) <= 0)
                    return i;
            }
        }
        return -1;
    }

    private int findStartIndexForFilter(String startDate) {
        for (int i = 0; i < actualSizeOfBills; i++) {
            if (bills[i].getCreationDate().compareTo(startDate) >= 0) return i;
        }

        return -1;
    }
}
