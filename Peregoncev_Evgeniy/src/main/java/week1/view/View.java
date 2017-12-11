package week1.view;

import week1.comparators.BillComparatorForSorting;
import week1.exeptions.InvalidLoginException;
import week1.interfaces.ITerminalController;
import week1.models.Bill;
import week1.models.Salesman;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ENIAC on 20.11.2017.
 */
public class View {

    public void run(ITerminalController terminal) throws InvalidLoginException {

        System.out.println("\nHello. write login/pass to sign in");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        String pass = scanner.nextLine();
        try {
            terminal.login(login, pass);

        } catch (InvalidLoginException e) {
            e.getMessage();
        }


        if (terminal.getCurrentSalesmanIndex() != -1) {
            menu();
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

                    case "7":
                        menuFilterForBills(scanner, terminal);
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


    private void menuCreateBill(ITerminalController terminal) {
        terminal.createBill();
        int index = terminal.getAllBills().size() - 1;
        System.out.println("bill was created with id " + index);
    }

    private void menuAddProduct(Scanner scanner, ITerminalController terminal) {

        System.out.println("write bill id to add product");
        int billId;
        if (scanner.hasNextInt()) {
            billId = scanner.nextInt();
        } else {
            System.out.println("you wrote non integer number");
            return;
        }

        System.out.println("write index of product you want to add");

        int productId;
        if (scanner.hasNextInt()) {
            productId = scanner.nextInt();

        } else {
            System.out.println("you wrote non integer number");
            return;
        }


        if (billId > terminal.getAllBills().size() || (billId < 0)) {

            System.out.println("There no bill with this id");
        } else if (productId > terminal.getAllProducts().size() || (productId < 0)) {

            System.out.println("There no product with this id");
        } else {
            terminal.addProduct(billId, terminal.getAllProducts().get(productId));
        }

    }

    private void menuCloseAndSaveBill(Scanner scanner, ITerminalController terminal) {
        System.out.println("write bill id to close");
        int billId;
        if (scanner.hasNextInt()) {
            billId = scanner.nextInt();
        } else {
            System.out.println("you wrote non integer number");
            return;
        }
        terminal.closeBill(billId);

    }

    private void menuFindBillById(Scanner scanner, ITerminalController terminal) {
        System.out.println("write id of search bill");
        int billId;
        if (scanner.hasNextInt()) {

            billId = scanner.nextInt();
        } else {

            System.out.println("you wrote non integer number");
            return;

        }
        if (billId > terminal.getAllBills().size() || (billId < 0)) {

            System.out.println("There no bill with this id");
            return;
        }

        Bill bill = terminal.findBillById(billId);

        if (bill == null) {
            return;
        }

        System.out.println(bill.toString());
    }

    private void menuFindSellerByLogin(Scanner scanner, ITerminalController terminal) {
        System.out.println("Write login of salesman, which you want to find");
        String login = scanner.next();

        Salesman salesman = terminal.findSalesmanByLogin(login);
        if (salesman == null) {
            return;
        }
        System.out.println(salesman.toString());
    }

    private void menuGetTopSalesman(ITerminalController terminal) {

        Salesman salesman = terminal.getTopOfSalesmans();

        if (salesman == null) {
            return;
        }

        System.out.println("top saller is " + salesman.getName());
    }

    private void menuFilterForBills(Scanner scanner, ITerminalController terminal) {
        System.out.println("write left limit of time in case like *Time: 9:15:09 Date: 2017.11.24*");
        scanner.nextLine();  //this is needed to take empty string(enter from println)
        String start = scanner.nextLine();

        System.out.println("write right limit of time in case like *Time: 9:15:09 Date: 2017.11.24*");
        String end = scanner.nextLine();

        List<Bill> billList = terminal.filterForBills(start, end, new BillComparatorForSorting());

        if (billList == null || billList.isEmpty()) {
            System.out.println("something gone wrong. Maybe you wrote wrong input data");
            return;
        }

        System.out.println(billList.toString());
    }

    private void menuLogOut(Scanner scanner, ITerminalController terminal) throws InvalidLoginException {
        terminal.setCurrentSalesmanIndex(-1);
        System.out.println("write login");
        String login1 = scanner.next();
        System.out.println("write pass");
        String pass1 = scanner.next();

        terminal.login(login1, pass1);

        if (terminal.getCurrentSalesmanIndex() != -1) {
            menu();
        }
    }

    private void menu() {
        System.out.println(
                "1: Create bill.\n" +
                        "2: Add product. \n" +
                        "3: Close and Save bill. \n" +
                        "4: Find bill by id. \n" +
                        "5: Find salesman by login.\n" +
                        "6: Get top of Salesman.\n" +
                        "7: Filter Bills by time.\n" +
                        "8: log out.\n" +
                        "q: Exit from terminal.");
    }

}