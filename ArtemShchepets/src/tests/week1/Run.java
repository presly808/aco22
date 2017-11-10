package week1;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {

        Seller[] sellers = new Seller[5];

        sellers[0] = new Seller("NadyaHoroshun", 22, "worker1", "password1");
        sellers[1] = new Seller("AntonVorobey", 17, "worker2", "password2");
        sellers[2] = new Seller("VasyaPupkin", 59, "worker3", "password3");
        sellers[3] = new Seller("AnyaTupova", 14, "worker4", "password4");
        sellers[4] = new Seller(null, 20, "worker5", "password5");


        Product product1 = new Product("Milk", 11.20, "#03242341");
        Product product2 = new Product("Cheese", 2.05, "#0341");
        Product product3 = new Product("Water", 33.5, "#01");
        Product product4 = new Product(null, 7.55, "#222");;

        Bill[] bills = new Bill[3];
        bills[0] = new Bill(sellers[4]);
        bills[1] =  new Bill(sellers[0]);
        bills[2] = new Bill(sellers[3]);

        bills[0].addProduct(product4);
        bills[1].addProduct(product1);
        bills[1].addProduct(product2);
        bills[2].addProduct(product1);
        bills[2].addProduct(product2);
        bills[2].addProduct(product3);
        bills[2].addProduct(product4);

        bills[0].setTime(new Time(12,12,12));
        bills[1].setTime(new Time(10,8,40));
        bills[2].setTime(new Time(23,10,44));

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

                    terminal.signIn(login,password);
                    scanner.next();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
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
