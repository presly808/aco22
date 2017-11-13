package week1.exclude;

import week1.controller.Terminal;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        Terminal terminal = new Terminal();
        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu();
    }
}


