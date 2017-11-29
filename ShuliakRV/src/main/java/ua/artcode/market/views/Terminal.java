package ua.artcode.market.views;

import ua.artcode.market.interfaces.ITerminal;

import java.util.Scanner;

public class Terminal {

    private ITerminal terminalController;

    private boolean loggedSalesman;

    public Terminal(ITerminal terminalController) {

        this.terminalController = terminalController;
    }

    public void mainMenu() {

        Scanner scan = new Scanner(System.in);

        System.out.println("**********Main Menu**********");
        System.out.println("Make a choice:");

        while (true) {

            System.out.println("1. LogIn");
            System.out.println("2. Create bill");
            System.out.println("3. Add product");
            System.out.println("4. Close and save bill");
            System.out.println("5. Get top N of salesmen");
            System.out.println("6. Do some statistic");
            System.out.println("7. Filter some bills");
            System.out.println("8. Exit");

            int choice = scan.nextInt();

            int res = runChoice(choice);

            if (res == -1) return;

        }
    }

    public int runChoice(int choice) {

        switch (choice) {
            case 1:
                terminalController.logIn();
        }

    }


}
