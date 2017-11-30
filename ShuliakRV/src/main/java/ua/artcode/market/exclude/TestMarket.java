package ua.artcode.market.exclude;

import ua.artcode.market.DataBases.AppDB;
import ua.artcode.market.Factories.FactoryITerminal;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.*;
import ua.artcode.market.views.Terminal;

public class TestMarket {

    public static void main(String[] args) {

        ITerminal terminalController = FactoryITerminal.createITerminalController();

        Terminal terminal = new Terminal(terminalController);

        terminal.mainMenu();

    }
}
