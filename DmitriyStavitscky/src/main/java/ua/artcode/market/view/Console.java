package ua.artcode.market.view;

import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.factory.TerminalFactory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ITerminal terminal = TerminalFactory.create();

        boolean exit = false;

        String name1 = "Dima Stavitscky";
        String name2 = "Ivan Raskolnikov";
        String name3 = "Kolia Ivanov";

        String login1 = "lDima";
        String login2 = "lIvan";
        String login3 = "lKolia";

        int pass1 = 123;
        int pass2 = 456;
        int pass3 = 789;

        terminal.getAppDB().addProductToDataBase("Milk", 100);
        terminal.getAppDB().addProductToDataBase("Apple", 70);
        terminal.getAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        while (!exit) {
            showMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter login or name");
                    String loginOrName = sc.nextLine();

                    System.out.println("Enter password");
                    int pass = sc.nextInt();

                    terminal.signIn(loginOrName, pass);
                    break;

                case 2:
                    terminal.logOut();
                    break;

                case 3:
                    terminal.createBill();
                    boolean billOpen = true;
                    while (billOpen) {
                        System.out.println("1. Add product to bill \n" +
                                "2. Close and save bill");

                        switch (choice) {
                            case 1:
                                System.out.println("Enter id");
                                terminal.addProductToBill(sc.nextInt());
                                break;

                            case 2:
                                System.out.println("Enter hours");
                                int hours = sc.nextInt();
                                System.out.println("Enter minutes");
                                int minutes = sc.nextInt();
                                System.out.println("Enter seconds");
                                int seconds = sc.nextInt();

                                terminal.closeAndSaveBill(hours, minutes, seconds);
                                billOpen = false;
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println(terminal.getTopNofSalesMan().toString());
                    break;

                case 5:
                    System.out.println(terminal.makeStatistics().toString());
                    break;

                case 6:
                    System.out.println("Enter login or name");
                    terminal.getAppDB().findSalesmanByLoginOrName(sc.nextLine());
                    break;

                case 7:
                    exit = true;
                    break;

                default:
                    System.out.println("try again");
            }
        }
    }

    private static void showMenu() {
        System.out.println("TERMINAL " + LocalDateTime.now() + "\n" +
                "1. Sign in \n" +
                "2. Log out \n" +
                "3. Create bill \n" +
                "4. Show top salesman \n" +
                "5. Show statistics \n" +
                "6. Find salesman by login or name \n" +
                "7. Exit");
    }
}
