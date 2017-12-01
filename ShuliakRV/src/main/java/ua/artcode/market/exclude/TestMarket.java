package ua.artcode.market.exclude;

import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.views.Terminal;

public class TestMarket {

    public static void main(String[] args) {

        ITerminal terminalController = FactoryITerminal.createITerminalController();

        Terminal terminal = new Terminal(terminalController);

     //   terminal.mainMenu();

        for (Salesman salesman : terminalController.getAppDB().getAllSalesman()) {
            System.out.println(salesman.getSubSalesmen());
        }

    }

}
