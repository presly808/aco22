package week1.controller;

import week1.comparators.CreationDateComparator;
import week1.comparators.SellersSoldProductsComparator;
import week1.interfaces.ITerminal;
import week1.model.*;

import java.util.Arrays;

public class TerminalController implements ITerminal {

    private BillController billController;
    private Seller[] sellers;

    private int actualSizeOfSellers;

    private int currentSellerIndex = -1;

    private boolean isSignIn = false;

    public TerminalController() {
        billController = new BillController();
    }

    public TerminalController(BillController billController, Seller[] sellers) {
        this.billController = billController;
        this.sellers = makeAnActualSellersArray(sellers);
        this.actualSizeOfSellers = this.sellers.length;
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


    public Seller[] getSellers() {
        return sellers;
    }

    public void setSellers(Seller[] sellers) {

        this.sellers = makeAnActualSellersArray(sellers);
        if (sellers != null) {
            this.actualSizeOfSellers = sellers.length;
        }
    }

    public BillController getBillController() {
        return billController;
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

    public void setCurrentSellerIndex(int currentSellerIndex) {
        this.currentSellerIndex = currentSellerIndex;
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

        if (currentSellerIndex == -1) {
            System.out.println("Firstly, you should sign in!");
            return false;
        }

        return billController.createBill(newBill);
    }

    public boolean addProductToBill(Product newProduct) {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
            return false;
        }

        return billController.addProductToBill(newProduct);
    }

    public boolean closeAndSaveBill() {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
            return false;
        }

        return billController.closeAndSaveBill();
    }

    public Bill findBillById(int searchingId) {

        if (!isSignIn) return null;

        return billController.findBillById(searchingId);
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
            for (int j = 0; j < billController.getActualSizeOfBills(); j++) {
                if (sellers[i].getName() != null && sellers[i].getSoldProducts() == 0
                        && sellers[i].getName().equals(billController.getBills()[j].getSeller().getName())) {
                    sellers[i].setSoldProducts(sellers[i].getSoldProducts() + billController.getBills()[j].getActualSizeOfList());
                }
            }
        }

        // sort sellers by sold products
        Arrays.sort(sellers, new SellersSoldProductsComparator());

        //return an array with N top-sellers
        return Arrays.copyOfRange(sellers, actualSizeOfSellers - quantityOfTopSellers, actualSizeOfSellers);

    }

    public String doSomeStatisticStuff() {

        if (billController.getBills() == null || sellers == null)
            System.out.println("Sry, we haven't any bills or sellers!");

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

        if (billController.getCurrentBillIndex() > -1) {
            for (int i = 0; i <= billController.getCurrentBillIndex(); i++) {
                soldProducts += billController.getBills()[i].getActualSizeOfList();
            }
        }
        return soldProducts;
    }

    private double findMaxPriceBill() {
        double maxPriceBill = billController.getBills()[0].getBillCost();

        // find maxPriceBill
        if (billController.getCurrentBillIndex() > -1) {
            for (int i = 0; i <= billController.getCurrentBillIndex(); i++) {
                if (billController.getBills()[i] != null && billController.getBills()[i].getBillCost() > maxPriceBill) {
                    maxPriceBill = billController.getBills()[i].getBillCost();
                }
            }
        }

        return maxPriceBill;
    }

    private double findMinPriceBill() {
        double minPriceBill = billController.getBills()[0].getBillCost();

        //find minPriceBill
        if (billController.getCurrentBillIndex() > -1) {
            for (int i = 0; i <= billController.getCurrentBillIndex(); i++) {
                if (billController.getBills()[i] != null && billController.getBills()[i].getBillCost() < minPriceBill) {
                    minPriceBill = billController.getBills()[i].getBillCost();
                }
            }
        }

        return minPriceBill;
    }

    @Override
    public Bill[] filter(String startDate, String endDate, CreationDateComparator someComparator) {
        return billController.filter(startDate,endDate,someComparator);
    }

    @Override
    public boolean closeAllPreviousBills() {
        return billController.closeAllPreviousBills();
    }
}
