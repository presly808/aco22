package ua.artcode.market.factories;

import ua.artcode.market.databases.AppDB;
import ua.artcode.market.controllers.ProxyTerminalController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.interfaces.ITerminal;

public class FactoryITerminal {

    public static ITerminal createITerminalController() {
        return new
                ProxyTerminalController(new TerminalController(new AppDB()));
    }
}
