package week1.data;

import java.util.Arrays;
import java.util.Scanner;

public class Terminal {

    private Bill[] bills;
    private Seller[] sellers;

    private int actualSizeOfBills;
    private int actualSizeOfSellers;

    private int currentSellerIndex = -1;
    private int currentBillIndex = 1;

    boolean isSignIn = false;

    public Terminal() {
    }

    // TODO actual bills array
    public Terminal(Bill[] bills, Seller[] sellers) {
        this.bills = bills;

        // sellers should already be in terminal,
        // so I created a method to make an actual array of sellers(without nulls)
        // with an actual size of sellers
        this.sellers = makeAnActualArray(sellers);
        this.actualSizeOfSellers = this.sellers.length;
    }

    private Seller[] makeAnActualArray(Seller[] inputSellerArray) {

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
        this.bills = bills;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void setSellers(Seller[] sellers) {
        this.sellers = sellers;
    }

    public int getActualSizeOfBills() {
        return actualSizeOfBills;
    }

    public int getActualSizeOfSellers() {
        return actualSizeOfSellers;
    }

    public void signIn(String login, String password) {

        if ((login == null || password == null) || (login.equals("") || password.equals(""))) {
            System.out.println("Enter login\\pass correctly!");
        } else {

            if (sellers == null) {
                System.out.println("Sorry, there aren't any sellers in terminal!");
            } else {

                for (int i = 0; i < actualSizeOfSellers; i++) {
                    if (sellers[i].getLogin().equals(login) && sellers[i].getPassword().equals(password)) {
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

    public void createBill() {

        // check if salesman is signed in
        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
        } else {

            Bill newBill = new Bill(sellers[currentSellerIndex]);

            // set actual id for new bill
            newBill.setId(actualSizeOfBills + 1);

            Scanner scanner = new Scanner(System.in);

            boolean toContinue; // always false?

            // fill the list of products in new bill
            do {
                // TODO check valid input
                System.out.println("Enter product name: ");
                String newProductName = scanner.next();

                System.out.println("Enter product price: ");
                double newProductPrice = scanner.nextDouble();

                System.out.println("Enter product code: ");
                String newProductCode = "#" + scanner.next();

                newBill.getBillList()[newBill.getActualSizeOfList()] = new Product(newProductName, newProductPrice, newProductCode);
                newBill.setActualSizeOfList(newBill.getActualSizeOfList() + 1);

                System.out.println("Want to add another one product? Type \"y\" if you want and anything else - if not.");
                toContinue = ("y".equals(scanner.next()));

            } while (toContinue);
            // TODO check valid input
            // set close time for the bill
            System.out.println("Set close time. Like this --> 12:12:12");
            String parsingTime = scanner.next();

            String[] parsedTime = parsingTime.split(":");

            newBill.getTime().setHours(Integer.decode(parsedTime[0]));
            newBill.getTime().setMinutes(Integer.decode(parsedTime[1]));
            newBill.getTime().setSeconds(Integer.decode(parsedTime[2]));

            //add new bill to the terminal
            bills[actualSizeOfBills] = newBill;
            actualSizeOfBills++;
            currentBillIndex = actualSizeOfBills - 1;

            System.out.println("***BILL IS CREATED***");
        }
    }

    public void addProductToBill() {

        // TODO check null bills

        if (!isSignIn) {

            System.out.println("Firstly, you should sign in!");

        } else {

            if (currentBillIndex == -1) {

                System.out.println("There aren't any bills in terminal!");

            } else {

                if (bills[actualSizeOfBills - 1].isClosed()) {

                    System.out.println("You can't add products. Bill is closed!");

                } else {

                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Enter product name: ");
                    String newProductName = scanner.next();

                    System.out.println("Enter product price: ");
                    double newProductPrice = scanner.nextDouble();

                    System.out.println("Enter product code: ");
                    String newProductCode = "#" + scanner.next();

                    Product newProduct = new Product(newProductName, newProductPrice, newProductCode);

                    bills[actualSizeOfBills - 1].addProduct(newProduct);
                }
            }
        }
    }

    // why do we need such method in Terminal if we already had one in Bill?

    public void closeAndSaveBill() {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
        } else {

            if (bills == null) {
                System.out.println("Sorry, there aren't any bills in the terminal!");
            } else {
                bills[currentBillIndex].closeBill();
            }
        }

    }

    public Bill findBillById(int searchingId) {

        if (searchingId <= -1 || !isSignIn) return null;

        for (int i = 0; i < actualSizeOfBills; i++) {

            if (bills[i].getId() == searchingId) return bills[i];
        }

        return null;
    }

    public Seller findSalesmanByLoginOrFullname(String loginOrNameOfSalesMan) {

        if (loginOrNameOfSalesMan == null || "".equals(loginOrNameOfSalesMan)) return null;

        for (int i = 0; i < actualSizeOfSellers; i++) {
            if (sellers[i].getLogin().equals(loginOrNameOfSalesMan) || sellers[i].getName().equals(loginOrNameOfSalesMan))
                return sellers[i];
        }

        return null;
    }

    public Seller[] getTopNofSalesMan(int quantityOfTopSellers) {

        if (quantityOfTopSellers <= 0 || sellers == null || quantityOfTopSellers > sellers.length) return null;

        Seller[] topSellers = new Seller[quantityOfTopSellers];

        // calculate sold products
        for (int i = 0; i < sellers.length; i++) {
            for (int j = 0; j < bills.length; j++) {
                if (sellers[i].getName().equals(bills[j].getSeller().getName())) {
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
                if (sellers[i].getSoldProducts() < sellers[i+1].getSoldProducts()) {

                    tmp = sellers[i];
                    sellers[i] = sellers[i+1];
                    sellers[i+1] = tmp;

                    swapped = true;
                }


            }
            lastUnsortedIndex--;
        } while (swapped);

        //return an array with N top-sellers
        return Arrays.copyOfRange(sellers,0,quantityOfTopSellers);

    }

    public void doSomeStatisticStuff() {

        double maxPriceBill = 0;
        double minPriceBill = 0;

        int soldProducts = 0;

        Seller[] bestSalesMan = getTopNofSalesMan(1);

        // find maxPriceBill
        for (int i = 0; i <= bills.length - 1; i++) {
            if (bills[i] != null && bills[i].getBillCost() > bills[i+1].getBillCost() &&
                    bills[i].getBillCost() > maxPriceBill) {
                maxPriceBill = bills[i].getBillCost();
            }
        }

        //find minPriceBill
        for (int i = 0; i <= bills.length - 1; i++) {
            if (bills[i] != null && bills[i].getBillCost() < bills[i+1].getBillCost() &&
                    bills[i].getBillCost() < minPriceBill) {
                minPriceBill = bills[i].getBillCost();
            }
        }

        // calculate the sum of sold products
        for (int i = 0; i < bills.length; i++) {
            soldProducts += bills[i].getActualSizeOfList();
        }

        System.out.println(String.format("***Statistic***\n " +
                "The highest price of bill: %d\n " +
                "The lowest price of bill: %d\n " +
                "There are %d sold products now!\n " +
                "Best salesman: %s", maxPriceBill, minPriceBill, soldProducts, bestSalesMan ));

    }

}
