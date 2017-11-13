package week1.view;

import week1.controller.Terminal;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Time;

import java.util.Scanner;

public class ConsoleView {

    public void runMenu() {
        Seller[] sellers = new Seller[5];

        sellers[0] = new Seller("NadyaHoroshun", 22, "worker1", "password1");
        sellers[1] = new Seller("AntonVorobey", 17, "worker2", "password2");
        sellers[2] = new Seller("VasyaPupkin", 59, "worker3", "password3");
        sellers[3] = new Seller("AnyaTupova", 14, "worker4", "password4");
        sellers[4] = new Seller(null, 20, "worker5", "password5");


        Product product1 = new Product("Milk", 11.20, "#03242341");
        Product product2 = new Product("Cheese", 2.05, "#0341");
        Product product3 = new Product("Water", 33.5, "#01");
        Product product4 = new Product(null, 7.55, "#222");


        Bill[] bills = new Bill[3];
        bills[0] = new Bill(sellers[4]);
        bills[1] = new Bill(sellers[0]);
        bills[2] = new Bill(sellers[3]);

        bills[0].addProduct(product4);
        bills[1].addProduct(product1);
        bills[1].addProduct(product2);
        bills[2].addProduct(product1);
        bills[2].addProduct(product2);
        bills[2].addProduct(product3);
        bills[2].addProduct(product4);

        bills[0].setTime(new Time(12, 12, 12));
        bills[1].setTime(new Time(10, 8, 40));
        bills[2].setTime(new Time(23, 10, 44));

        Terminal terminal = new Terminal(bills, sellers);

        do {

            System.out.println("\n***WELCOME TO THE TERMINAL!***");
            System.out.println("1: Login.\n" +
                    "2: Create bill.\n" +
                    "3: Add product. \n" +
                    "4: Close and Save bill. \n" +
                    "5: Find bill by id. \n" +
                    "6: Find salesman by login or full name.\n" +
                    "7: Get top of Salesman.\n" +
                    "8: Get some statistic.\n" +
                    "q: Exit from terminal.");
            System.out.println("\nChoose what you want to do: ");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter your login: ");

                    String login = scanner.next();

                    System.out.println("Enter your password: ");

                    String password = scanner.next();

                    System.out.println("\nTrying to sign in...\n");

                    terminal.signIn(login, password);
                    scanner.next();
                    break;
                case "2":
                    if (!terminal.isSignIn()) {
                        System.out.println("Firstly, you should sign in!");
                    } else {

                        if (terminal.getActualSizeOfBills() > 1) {

                            System.out.println("Firstly, to continue you should close all previous bills!");

                            System.out.println("Set close time. Like this --> 12:12:12");

                            String parsingTime = scanner.next();

                            String[] parsedTime = parsingTime.split(":");

                            Time closeTime = new Time(Integer.decode(parsedTime[0]),
                                    Integer.decode(parsedTime[1]), Integer.decode(parsedTime[2]));

                            terminal.closeAllPreviousBills(closeTime);
                        }

                        Bill newBill = new Bill(sellers[terminal.getCurrentSellerIndex()]);

                        // set actual id for new bill
                        newBill.setId(terminal.getActualSizeOfBills() + 1);

                        boolean toContinue;

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

                        newBill.setId(terminal.getActualSizeOfBills());
                        newBill.calculateBill();

                        if (terminal.createBill(newBill))
                            System.out.println("Bill is created and added to the terminal!");
                        else {
                            System.out.println("Bill wasn't created!");
                        }
                    }
                    break;
                case "3":

                    if (terminal.getActualSizeOfBills() > 1) {

                        System.out.println("Firstly, to continue you should close all previous bills!");

                        System.out.println("Set close time. Like this --> 12:12:12");

                        String parsingTime = scanner.next();

                        String[] parsedTime = parsingTime.split(":");

                        Time closeTime = new Time(Integer.decode(parsedTime[0]),
                                Integer.decode(parsedTime[1]), Integer.decode(parsedTime[2]));

                        terminal.closeAllPreviousBills(closeTime);
                    }

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
                    break;
                case "4":

                    System.out.println("Set close time. Like this --> 12:12:12");

                    String parsingTime = scanner.next();

                    String[] parsedTime = parsingTime.split(":");

                    Time closeTime = new Time(Integer.decode(parsedTime[0]),
                            Integer.decode(parsedTime[1]), Integer.decode(parsedTime[2]));

                    if (terminal.closeAndSaveBill(closeTime)) {
                        System.out.println("Bill was closed!");
                    } else {
                        System.out.println("Bill wasn't closed!");
                    }
                    break;
                case "5":

                    System.out.println("Enter bill id and we will search for it in our DB.");

                    int id = scanner.nextInt();

                    Bill searchingBill = terminal.findBillById(id);

                    if (searchingBill == null) {
                        System.out.println("We can't find such bill in our DB.");
                    } else {
                        System.out.println("Searching bill: " + searchingBill.toString());
                    }
                    break;
                case "6":

                    System.out.println("Enter login or full name " +
                            "and we will search for such salesman in our DB.");

                    Seller searchingSeller = terminal.findSalesmanByLoginOrFullname(scanner.next());
                    if (searchingSeller == null) {
                        System.out.println("We can't find salesman with suck login\\full name.");
                    } else System.out.println(searchingSeller.toString());
                    break;
                case "7":

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

                    break;
                case "8":
                    System.out.println("***STATISTIC***");
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

}
