package week1.exclude;

import week1.controller.BillController;
import week1.controller.TerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        Seller[] sellers = new Seller[5];

        sellers[0] = new Seller("NadyaHoroshun", 22, "worker1", "password1");
        sellers[1] = new Seller("AntonVorobey", 17, "worker2", "password2");
        sellers[2] = new Seller("VasyaPupkin", 59, "worker3", "password3");
        sellers[3] = new Seller("AnyaTupova", 14, "worker4", "password4");
        sellers[4] = new Seller("ArtemShchepets", 20, "worker5", "password5");

        BillController billController = new BillController();

        TerminalController terminal = new TerminalController(billController, sellers);

        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu(terminal);
    }
}


