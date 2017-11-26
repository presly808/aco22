package week1.view;

import week1.controller.TerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import java.util.Scanner;

import static week1.view.OutputMessages.*;

public class ConsoleView {

    public void runMenu(TerminalController terminal) {
        do {
            consoleWelcomeMessage();

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    menuSignIn(scanner, terminal);
                    break;
                case "2":
                    menuCreateBill(scanner, terminal);
                    break;
                case "3":

                    menuAddProduct(scanner, terminal);
                    break;
                case "4":
                    menuCloseAndSaveBill(scanner, terminal);
                    break;
                case "5":
                    menuFindBillById(scanner, terminal);
                    break;
                case "6":
                    menuFindSellerByLoginOrName(scanner, terminal);
                    break;
                case "7":
                    menuShowTopSellers(scanner, terminal);
                    break;
                case "8":
                    System.out.println(terminal.doSomeStatisticStuff());
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Wrong command! Try again!");
                    break;
            }
        } while (true);
    }

    private void menuShowTopSellers(Scanner scanner, TerminalController terminal) {

        System.out.println("Enter a number of sellers, " +
                "which you want to see as top sellers(not more than "
                + terminal.getActualSizeOfSellers() + " sellers.");

        Seller[] topSellers = terminal.getTopNofSalesMan(scanner.nextInt());

        if (topSellers == null) {
            System.out.println("No such info.");
        } else {
            System.out.println("Top sellers: ");
            for (int i = 0; i < topSellers.length; i++) {
                System.out.println(topSellers[i].toString());
            }
        }

        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuFindSellerByLoginOrName(Scanner scanner, TerminalController terminal) {

        System.out.println("Enter login or full name " +
                "and we will search for such salesman in our DB.");

        Seller searchingSeller = terminal.findSalesmanByLoginOrFullname(scanner.next());
        if (searchingSeller == null) {
            System.out.println("We can't find salesman with such login\\full name.");
        } else System.out.println(searchingSeller.toString());

        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuFindBillById(Scanner scanner, TerminalController terminal) {
        System.out.println("Enter bill id and we will search for it in our DB.");

        int id = scanner.nextInt();

        Bill searchingBill = terminal.findBillById(id);

        if (searchingBill == null) {
            System.out.println("We can't find such bill in our DB.");
        } else {
            System.out.println("Searching bill: " + searchingBill.showInfo());
        }
        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuCloseAndSaveBill(Scanner scanner, TerminalController terminal) {
        if (terminal.closeAndSaveBill()) {
            System.out.println("Bill was closed!");
        } else {
            System.out.println("Bill wasn't closed!");
        }
        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuAddProduct(Scanner scanner, TerminalController terminal) {
        checkIsClosedAllPreviousBills(terminal);

        System.out.println("Enter product name: ");
        String newProductName = scanner.next();

        System.out.println("Enter product price: ");
        double newProductPrice = scanner.nextDouble();

        System.out.println("Enter product code: ");
        String newProductCode = "#" + scanner.next();

        Product newProduct = new Product(newProductName, newProductPrice, newProductCode);

        if (terminal.addProductToBill(newProduct))
            System.out.println("Product was added to the last bill!");
        else {
            System.out.println("Product wasn't added.");
        }
        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuCreateBill(Scanner scanner, TerminalController terminal) {
        if (!terminal.isSignIn()) {
            System.out.println("Firstly, you should sign in!");
        } else {

            checkIsClosedAllPreviousBills(terminal);

            Bill newBill = new Bill(terminal.getSellers()[terminal.getCurrentSellerIndex()]);

            // set actual id for new bill
            newBill.setId(terminal.getBillController().getActualSizeOfBills() + 1);

            boolean toContinue;

            // fill the list of products in new bill
            do {
                toContinue = fillListOfProducts(scanner, newBill);
            } while (toContinue);

            newBill.setId(terminal.getBillController().getActualSizeOfBills());

            newBill.calculateBill();

            if (terminal.createBill(newBill))
                System.out.println("Bill is created and added to the terminal!");
            else {
                System.out.println("Bill wasn't created!");
            }
        }
        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuSignIn(Scanner scanner, TerminalController terminal) {
        System.out.println("Enter your login: ");
        String login = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

        System.out.println("\nTrying to sign in...\n");

        terminal.signIn(login, password);
        scanner.next(); //dk how to stop for a little my console app except this way

    }

    private void checkIsClosedAllPreviousBills(TerminalController terminal) {
        if (terminal.getBillController().getActualSizeOfBills() > 1) {

            terminal.getBillController().closeAllPreviousBills();
        }
    }

    private boolean fillListOfProducts(Scanner scanner, Bill newBill) {

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
        return ("y".equals(scanner.next()));
    }

}
