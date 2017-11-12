package main.java;

import java.util.Scanner;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Terminal {
    public static void main(String[] args) {
        int select;
        do {
            System.out.println("please select menu item");
            System.out.println("1-createBill, 2-addProduct, 3 - findBillById, 4 - exit");
            Scanner scanner = new Scanner(System.in);
            select = scanner.nextInt();

            switch (select) {
                case 1:
                    System.out.println("pressed 1");

                    break;
                case 2:
                    System.out.println("pressed 2");

                    break;
                case 3:
                    System.out.println("pressed 3");

                    break;
                default:
                    System.out.println("pressed wrong menu item");
            }

        } while (select != 4);


    }


}
