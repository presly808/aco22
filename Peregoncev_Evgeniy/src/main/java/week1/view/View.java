package week1.view;

import week1.controller.ITerminalControllerImpl;

import java.util.Scanner;

/**
 * Created by ENIAC on 20.11.2017.
 */
public class View {

    public void run(ITerminalControllerImpl terminal) {

        System.out.println("\nHello. write login/pass to sign in");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        String pass = scanner.nextLine();

        terminal.login(login, pass);

        menu();

        if (terminal.getCurrentSallerIndex() != -1) {
            do {

                String choice = scanner.next();


                switch (choice) {
                    case "1":
                        menuCreateBill(terminal);
                        break;

                    case "2":
                        menuAddProduct(scanner, terminal);
                        break;

                    case "3":
                        menuCloseAndSaveBill(scanner, terminal);
                        break;

                    case "4":
                        menuFindBillById(scanner, terminal);
                        break;

                    case "5":
                        menuFindSellerByLogin(scanner, terminal);
                        break;

                    case "6":
                        menuGetTopSalesman(terminal);
                        break;

                    case "8":
                        menuLogOut(scanner, terminal);
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


    // Methods

    private void menuCreateBill(ITerminalControllerImpl terminal) {
        terminal.createBill();
        int index = terminal.getAllBills().size() - 1;
        System.out.println("bill was created with id " + index);
    }

    private void menuAddProduct(Scanner scanner, ITerminalControllerImpl terminal) {
        System.out.println("write bill id to add product");
        int id = scanner.nextInt();
        System.out.println("write index of product you want to add");
        int productId = scanner.nextInt();
        terminal.addProduct(id, terminal.getAllProducts().get(productId));
        System.out.println("product " + terminal.getAllProducts().get(productId).getName()
                + " was added to bill with id " + id);

    }

    private void menuCloseAndSaveBill(Scanner scanner, ITerminalControllerImpl terminal) {
        System.out.println("write bill id to close");
        int billId = scanner.nextInt();
        terminal.closeBill(billId);
        System.out.println("bill " + billId + " now is closed");
    }

    private void menuFindBillById(Scanner scanner, ITerminalControllerImpl terminal) {
        System.out.println("write id of search bill");
        int billId = scanner.nextInt();

        System.out.println(terminal.findBillById(billId).toString());
    }

    private void menuFindSellerByLogin(Scanner scanner, ITerminalControllerImpl terminal) {
        System.out.println("Write login of seller, which you want to find");
        String login = scanner.next();
        System.out.println(terminal.findSalesmanByLogin(login).toString());
    }

    private void menuGetTopSalesman(ITerminalControllerImpl terminal) {

    }

    private void menuLogOut(Scanner scanner, ITerminalControllerImpl terminal) {
        terminal.setCurrentSallerIndex(-1);
        System.out.println("write login");
        String login1 = scanner.next();
        System.out.println("write pass");
        String pass1 = scanner.next();

        terminal.login(login1, pass1);
        menu();
    }

    private void menu() {
        System.out.println(
                "1: Create bill.\n" +
                        "2: Add product. \n" +
                        "3: Close and Save bill. \n" +
                        "4: Find bill by id. \n" +
                        "5: Find salesman by login or full name.\n" +
                        "6: Get top of Salesman.\n" +
                        "7: Get some statistic.\n" +
                        "8: log out\n" +
                        "q: Exit from terminal.");
    }

}