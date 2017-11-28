/*
package ua.artcode.market.view;

import ua.artcode.market.Main;

import java.io.IOException;
import java.util.Scanner;

public class View {

    public static void fRun(Scanner scanner, Main main) throws IOException {
        System.out.println("First application launch");
        System.out.println("1 - create admin");
        System.out.println("2 - exit");
        System.out.println("3 - login");

        String str = "";
        do {
            str = scanner.nextLine();
            switch (str) {
                case "1":
                    System.out.println("Create Admin");
                    crAdmin(scanner, main);
                    break;
                case "2":
                    System.out.println("exit");
                    break;
                case "3":
                    login();
                    break;
            } break;
        }while (!str.equals("1") || !str.equals("2") || !str.equals("2"));
    }

    public static void login() {

    }

    public static void crAdmin(Scanner scanner, Main m) throws IOException {
        String name = scanner.nextLine();
        String log = scanner.nextLine();
        String pass = scanner.nextLine();
        m.getTerminalController().getiAppDb().createSalesman(name, log, pass);
    }

    public static void crSeler(Scanner scanner, Main m) throws IOException {
        String name = scanner.nextLine();
        String log = scanner.nextLine();
        String pass = scanner.nextLine();
        m.getTerminalController().getiAppDb().createSalesman(name, log, pass);
    }
}
*/
