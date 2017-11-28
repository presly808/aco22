package week1.exclude;

import week1.controller.ProxyITerminalControllerImpl;
import week1.interfaces.ITerminalController;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        ITerminalController terminal = ProxyITerminalControllerImpl.getInstance();

        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu(terminal);
    }
}


