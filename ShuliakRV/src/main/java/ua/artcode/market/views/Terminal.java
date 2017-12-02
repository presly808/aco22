package ua.artcode.market.views;

import ua.artcode.market.comparators.BillIdComparator;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.*;
import ua.artcode.market.singletons.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {

    private ITerminal terminalController;

    private Salesman loggedSalesman;

    public Terminal(ITerminal terminalController) {

        this.terminalController = terminalController;
    }

    public void mainMenu() {

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("*****************************");
            System.out.println("**********Main Menu**********");
            System.out.println("Make a choice:");
            System.out.println("1. Log In");
            System.out.println("2. Create bill");
            System.out.println("3. Add product");
            System.out.println("4. Close and save bill");
            System.out.println("5. Get top N of salesmen");
            System.out.println("6. Do some statistic");
            System.out.println("7. Filter some bills");
            System.out.println("8. Log Out");
            System.out.println("9. View operation logs");
            System.out.println("10. Exit");
            System.out.println();
            System.out.println("Your choice: ");

            int choice = scan.nextInt();

            if (choice == 10) return;

            int res = 0;

            if (choice == 1 || loggedSalesman != null) {

                res = runChoice(choice);

            } else {
                System.out.println("User isn't logged");
            }

            if (res == -1) return;

            scan.nextLine();

        }
    }

    public int runChoice(int choice) {
        Bill bill = null;
        int anyChoice = choice;
        switch (anyChoice) {
            case 1:
                Salesman salesman = terminalController.getAppDB().
                        getAllSalesman().get((int) (Math.random() *
                        terminalController.getAppDB().
                                getAllSalesman().size()));
                loggedSalesman = terminalController.logIn(salesman.getLogin(),
                        salesman.getPassword());
                if (loggedSalesman != null) {
                    System.out.println("User is logged");
                } else {
                    System.out.println("Login or password are not avalible");
                }
                break;
            case 2:
                bill = terminalController.createBill(loggedSalesman);
                if (bill == null) {
                    System.out.println("Bill isn't created");
                } else {
                    System.out.println("Bill is created");
                }
                break;
            case 3:
                if (bill == null) {
                    System.out.println("Bill isn't exists");
                    break;
                }
                bill = terminalController.addProduct(bill.getId(),
                        terminalController.getAppDB().getAllProducts().
                                get(((int) (Math.random() *
                                        terminalController.
                                                getAppDB().getAllProducts().
                                                size()))));
                if (bill == null) {
                    System.out.println("Bill isn't found");
                } else {
                    System.out.println("Product is added");
                }
                break;
            case 4:
                if (bill == null) {
                    System.out.println("Bill isn't exists");
                    break;
                }
                bill = terminalController.closeAndSaveBill(bill.getId());
                if (bill == null) {
                    System.out.println("Bill wasn't found");
                } else {
                    System.out.println("Bill was saved and closed");
                }
                bill = null;
                break;
            case 5:
                List<Salesman> salesmen = terminalController.
                        getTopNOfSalesMen((int) (Math.random() *
                                terminalController.getAppDB().
                                        getAllSalesman().size()) + 1);

                System.out.println(salesmen);
                break;
            case 6:
                Statistic statistic = terminalController.
                        doSomeStatisticStuff();
                System.out.println(statistic);
                break;
            case 7:
                ArrayList<Salesman> salesmenList = new ArrayList<>();
                salesmenList.add(loggedSalesman);
                List<Bill> bills = terminalController.
                        filter(salesmenList, null, null,
                                null, new BillIdComparator());
                System.out.println(bills);
                break;
            case 8:
                terminalController.logOut(loggedSalesman);
                loggedSalesman = null;
                System.out.println("User logged out");
                break;
            case 9:
                System.out.println(Logger.getInstance().getLogs());
                break;
            case 10:
                return -1;
        }
        return 0;
    }


}
