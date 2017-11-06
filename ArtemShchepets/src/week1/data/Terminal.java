package week1.data;

import java.util.Scanner;

public class Terminal {

    private Bill[] bills;
    private Seller[] sellers;

    private int actualSizeOfBills;
    private int getActualSizeOfSellers;

    private int currentSellerIndex = -1;
    private int currentBillIndex = -1;

    boolean isSignIn = false;

    public Terminal() {
    }

    public Terminal(Bill[] bills, Seller[] sellers) {
        this.bills = bills;
        this.sellers = sellers;
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

    public int getGetActualSizeOfSellers() {
        return getActualSizeOfSellers;
    }

    public void signIn(String login, String password) {

        if ((login == null || password == null) || (login.equals("") || password.equals(""))) {
            System.out.println("Enter login\\pass correctly!");
        } else {

            for (int i = 0; i < getActualSizeOfSellers; i++) {
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

    public void createBill() {

        if (!isSignIn) {
            System.out.println("Firstly, you should sign in!");
        } else {

            Bill newBill = new Bill(sellers[currentSellerIndex]);

            newBill.setId(actualSizeOfBills + 1);

            Scanner scanner = new Scanner(System.in);

            boolean toContinue = false;
            do {
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

            System.out.println("Set close time. Like this --> 12:12:12");
            String parsingTime = scanner.next();

            String[] parsedTime = parsingTime.split(":");

            newBill.getTime().setHours(Integer.decode(parsedTime[0]));
            newBill.getTime().setHours(Integer.decode(parsedTime[1]));
            newBill.getTime().setHours(Integer.decode(parsedTime[2]));


            bills[actualSizeOfBills] = newBill;
            actualSizeOfBills++;

        }
    }

}
