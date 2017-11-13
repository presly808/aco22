package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Time;

import java.util.Arrays;

public class Terminal {

    private static final int DEFAULT_SIZE_OF_BILL_ARRAY = 10;

    private Bill[] bills;
    private Seller[] sellers;

    private int actualSizeOfBills;
    private int actualSizeOfSellers;

    private int currentSellerIndex = -1;
    private int currentBillIndex = -1;

   private boolean isSignIn = false;

    public Terminal() {
    }

    public Terminal(Seller[] sellers) {
        this.sellers = makeAnActualSellersArray(sellers);
        this.actualSizeOfSellers = this.sellers.length;
    }

    public Terminal(Bill[] bills, Seller[] sellers) {

        // make an actual array of bills (without nulls)
        this.bills = makeAnActualBillArray(bills);
        this.actualSizeOfBills = this.bills.length;
        currentBillIndex = actualSizeOfBills - 1;

        // sellers should already be in terminal,
        // so I created a method to make an actual array of sellers(without nulls)
        // with an actual size of sellers
        this.sellers = makeAnActualSellersArray(sellers);
        this.actualSizeOfSellers = this.sellers.length;
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

    private Seller[] makeAnActualSellersArray(Seller[] inputSellerArray) {

        if (inputSellerArray == null) return null;

        Seller[] additionalArray = new Seller[inputSellerArray.length];
        int additionalArrayCounter = 0;

        for (int i = 0; i < inputSellerArray.length; i++) {
            if (inputSellerArray[i] != null) {
                additionalArray[additionalArrayCounter] = inputSellerArray[i];
                additionalArrayCounter++;
            }
        }

        return Arrays.copyOf(additionalArray, additionalArrayCounter);
    }

    public Bill[] getBills() {
        return bills;
    }

    public void setBills(Bill[] bills) {

        this.bills = makeAnActualBillArray(bills);
        if (bills != null) {
            this.actualSizeOfBills = bills.length;
        }
        this.currentBillIndex = actualSizeOfBills - 1;

    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void setSellers(Seller[] sellers) {

        this.sellers = makeAnActualSellersArray(sellers);
        if (sellers != null) {
            this.actualSizeOfSellers = sellers.length;
        }
    }

    public int getActualSizeOfBills() {
        return actualSizeOfBills;
    }

    public void setActualSizeOfBills(int actualSizeOfBills) {
        this.actualSizeOfBills = actualSizeOfBills;
    }

    public int getActualSizeOfSellers() {
        return actualSizeOfSellers;
    }

    public void setActualSizeOfSellers(int actualSizeOfSellers) {
        this.actualSizeOfSellers = actualSizeOfSellers;
    }

    public int getCurrentSellerIndex() {
        return currentSellerIndex;
    }

    public int getCurrentBillIndex() {
        return currentBillIndex;
    }

    public void setCurrentSellerIndex(int currentSellerIndex) {
        this.currentSellerIndex = currentSellerIndex;
    }

    public void setCurrentBillIndex(int currentBillIndex) {
        this.currentBillIndex = currentBillIndex;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public void signIn(String login, String password) {

        isSignIn = false;
        currentSellerIndex = -1;

        if ((login == null || password == null) || (login.equals("") || password.equals(""))) {
            System.out.println("Enter login\\pass correctly!");
        } else {

            if (sellers == null) {
                System.out.println("Sorry, there aren't any sellers in terminal!");
            } else {

                for (int i = 0; i < actualSizeOfSellers; i++) {
                    if (sellers[i].getLogin().equals(login) && sellers[i].getPassword().equals(password)
                            && sellers[i].getName() != null) {
                        isSignIn = true;
                        currentSellerIndex = i;
                    }
                }

                if (!isSignIn) {
                    System.out.println("We can't find user with such login\\pass. Try again!");
                } else {
                    System.out.println("Greeting, " + sellers[currentSellerIndex].getName());
                }

            }

        }
    }

    public boolean createBill(Bill newBill) {

        // check if salesman is signed in
        if (currentSellerIndex == -1) {
            System.out.println("Firstly, you should sign in!");
            return false;
        }

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

    public boolean closeAllPreviousBills(Time closeTime) {

        if (bills != null) {
            for (int i = 0; i < actualSizeOfBills - 1; i++) {
                if (!bills[i].isClosed()) bills[i].closeBill(closeTime);
            }

            return true;
        }

        return false;
    }

    public boolean addProductToBill(Product newProduct) {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
            return false;
        } else {

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

                    } else return bills[actualSizeOfBills - 1].addProduct(newProduct);

                }
            }
        }
    }

    public boolean closeAndSaveBill(Time time) {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
            return false;
        } else {

            if (currentBillIndex == -1) {
                System.out.println("Sorry, there aren't any bills in the terminal!");
                return false;
            } else {

                if (bills[currentBillIndex].isClosed()) {
                    System.out.println("Sorry, this bill is already closed!");
                    return false;
                } else {

                    return bills[currentBillIndex].closeBill(time);
                }
            }
        }
    }

    public Bill findBillById(int searchingId) {

        if (searchingId <= -1 || !isSignIn || currentBillIndex == -1) return null;

        for (int i = 0; i < actualSizeOfBills; i++) {

            if (bills[i].getId() == searchingId) return bills[i];
        }

        return null;
    }

    public Seller findSalesmanByLoginOrFullname(String loginOrNameOfSalesMan) {

        if (loginOrNameOfSalesMan == null || "".equals(loginOrNameOfSalesMan) || sellers == null) return null;

        for (int i = 0; i < actualSizeOfSellers; i++) {
            if ((sellers[i].getName() != null && sellers[i].getLogin() != null)
                    && (sellers[i].getLogin().equals(loginOrNameOfSalesMan)
                    || sellers[i].getName().equals(loginOrNameOfSalesMan)))
                return sellers[i];
        }

        return null;
    }

    public Seller[] getTopNofSalesMan(int quantityOfTopSellers) {

        if (quantityOfTopSellers <= 0 || sellers == null || quantityOfTopSellers > sellers.length) return null;

        // calculate sold products
        for (int i = 0; i < actualSizeOfSellers; i++) {
            for (int j = 0; j < actualSizeOfBills; j++) {
                if (sellers[i].getName() != null && sellers[i].getSoldProducts() == 0
                        && sellers[i].getName().equals(bills[j].getSeller().getName())) {
                    sellers[i].setSoldProducts(sellers[i].getSoldProducts() + bills[j].getActualSizeOfList());
                }
            }
        }

        // sort sellers by sold products
        int lastUnsortedIndex = sellers.length - 1;

        Seller tmp;
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (sellers[i].getSoldProducts() < sellers[i + 1].getSoldProducts()) {

                    tmp = sellers[i];
                    sellers[i] = sellers[i + 1];
                    sellers[i + 1] = tmp;

                    swapped = true;
                }


            }
            lastUnsortedIndex--;
        } while (swapped);

        //return an array with N top-sellers
        return Arrays.copyOfRange(sellers, 0, quantityOfTopSellers);

    }

    public String doSomeStatisticStuff() {

        if (bills == null || sellers == null) System.out.println("Sry, we haven't any bills or sellers!");

        double maxPriceBill = findMaxPriceBill();
        double minPriceBill = findMinPriceBill();

        int soldProducts = calculateSumOfSoldProducts();

        Seller[] bestSalesMan = getTopNofSalesMan(1);

        return (String.format("***Statistic***\n " +
                "The highest price of bill: %f\n " +
                "The lowest price of bill: %f\n " +
                "There are %d sold products now!\n " +
                "Best salesman: %s", maxPriceBill, minPriceBill, soldProducts, bestSalesMan[0].toString()));

    }

    private int calculateSumOfSoldProducts() {
        int soldProducts = 0;

        if (currentBillIndex > -1) {
            for (int i = 0; i <= currentBillIndex; i++) {
                soldProducts += bills[i].getActualSizeOfList();
            }
        }
        return soldProducts;
    }

    private double findMaxPriceBill() {
        double maxPriceBill = bills[0].getBillCost();

        // find maxPriceBill
        if (currentBillIndex > -1) {
            for (int i = 0; i <= currentBillIndex; i++) {
                if (bills[i] != null && bills[i].getBillCost() > maxPriceBill) {
                    maxPriceBill = bills[i].getBillCost();
                }
            }
        }

        return  maxPriceBill;
    }

    private double findMinPriceBill() {
        double minPriceBill = bills[0].getBillCost();

        //find minPriceBill
        if (currentBillIndex > -1) {
            for (int i = 0; i <= currentBillIndex; i++) {
                if (bills[i] != null && bills[i].getBillCost() < minPriceBill) {
                    minPriceBill = bills[i].getBillCost();
                }
            }
        }

        return minPriceBill;
    }
}
