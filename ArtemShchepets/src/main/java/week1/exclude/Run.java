package week1.exclude;

import week1.controller.ITerminalControllerImpl;
import week1.database.IAppDBImpl;
import week1.interfaces.IAppDB;
import week1.interfaces.ITerminalController;
import week1.model.Seller;
import week1.utils.TerminalUtils;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        IAppDB iAppDB = new IAppDBImpl();
        ITerminalController terminal = new ITerminalControllerImpl(iAppDB);

        terminal.turnOffLogger();
        TerminalUtils.turnOffLogger();
        iAppDB.turnOffLogger();

        Seller seller1 = new Seller("worker1", "password1", "NadyaHoroshun");
        Seller seller2 = new Seller("worker2", "password2", "AntonVorobey");
        Seller seller3 = new Seller("worker3", "password3", "VasyaPupkin");
        Seller seller4 = new Seller("worker4", "password4", "AnyaTupova");
        Seller seller5 = new Seller("worker5", "password5", "ArtemShchepets");

        iAppDB.saveSeller(seller1);
        iAppDB.saveSeller(seller2);
        iAppDB.saveSeller(seller3);
        iAppDB.saveSeller(seller4);
        iAppDB.saveSeller(seller5);

        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu(terminal);
    }
}


