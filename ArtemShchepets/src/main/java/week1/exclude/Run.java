package week1.exclude;

import week1.controller.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        ITerminalController terminal = ITerminalControllerFactory.create();

        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu(terminal);
    }
}


