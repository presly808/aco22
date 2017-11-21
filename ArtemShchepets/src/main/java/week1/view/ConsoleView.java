package week1.view;

import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import java.util.Scanner;

public class ConsoleView {

    private boolean loggedIn;

    public void runMenu(ITerminalController terminal) {

        Scanner scanner = new Scanner(System.in);
        menuSignIn(scanner, terminal);
        System.out.println("\n***WELCOME TO THE TERMINAL!***");

        do {
            consoleWelcomeMessage();

            String choice = scanner.next();

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
                    scanner.next();
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Wrong command! Try again!");
                    scanner.next();
                    break;
            }
        } while (true);
    }

    public static void consoleWelcomeMessage() {
        System.out.println("1: Login as another user.\n" +
                "2: Create bill.\n" +
                "3: Add product. \n" +
                "4: Close and Save bill. \n" +
                "5: Find bill by id. \n" +
                "6: Find salesman by login or full name.\n" +
                "7: Get top of Salesman.\n" +
                "8: Get some statistic.\n" +
                "q: Exit from terminal.");
        System.out.println("\nChoose what you want to do: ");
    }


    private void menuShowTopSellers(Scanner scanner, ITerminalController terminal) {

        System.out.println("***Top seller***");

        Seller topSeller = terminal.getTopOfSalesman();

        if (topSeller == null)
            System.out.println("No such info.");
        else
            System.out.println(topSeller.toString());


        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuFindSellerByLoginOrName(Scanner scanner, ITerminalController terminal) {

        System.out.println("Enter login or full name " +
                "and we will search for such salesman in our DB.");

        Seller searchingSeller = terminal.findSellerByLoginOrFullName(scanner.next());

        if (searchingSeller == null)
            System.out.println("We can't find salesman with such login\\full name.");
        else System.out.println(searchingSeller.toString());

        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuFindBillById(Scanner scanner, ITerminalController terminal) {
        System.out.println("Enter bill id and we will search for it in our DB.");

        int id = scanner.nextInt();

        Bill searchingBill = terminal.findBillById(id);

        if (searchingBill == null)
            System.out.println("We can't find such bill in our DB.");
        else
            System.out.println("Searching bill: " + searchingBill.toString());

        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuCloseAndSaveBill(Scanner scanner, ITerminalController terminal) {

        System.out.println("Enter bill id, which you want to close.");

        Bill bill = terminal.closeBill(scanner.nextInt());

        if (bill == null)
            System.out.println("Bill wasn't closed!");
        else
            System.out.println("Bill was closed!");


        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuAddProduct(Scanner scanner, ITerminalController terminal) {

        System.out.println("Enter id of bill where you want to add a product");
        int id = scanner.nextInt();

        System.out.println("Enter product name: ");
        String newProductName = scanner.next();

        System.out.println("Enter product price: ");
        double newProductPrice = scanner.nextDouble();

        Product newProduct = new Product(newProductName, newProductPrice);

        if (terminal.addProduct(id, newProduct) != null)
            System.out.println("Product was added to the bill with id " + id);
        else
            System.out.println("Product wasn't added.");

        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuCreateBill(Scanner scanner, ITerminalController terminal) {

        Bill bill = terminal.createBill();

        if (bill == null)
            System.out.println("New bill wasn't created!");
        else
            System.out.println("New bill was created!");
        scanner.next(); //dk how to stop for a little my console app except this way
    }

    private void menuSignIn(Scanner scanner, ITerminalController terminal) {

        do {
            System.out.println("Enter your login: ");
            String login = scanner.next();

            System.out.println("Enter your password: ");
            String password = scanner.next();

            System.out.println("\nTrying to sign in...\n");

            if (terminal.login(login, password))
                loggedIn = true;
            else System.out.println("Sorry, something went wrong! Try again!");
        } while (!loggedIn);

    }
}
