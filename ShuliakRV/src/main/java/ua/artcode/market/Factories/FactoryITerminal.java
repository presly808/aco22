package ua.artcode.market.Factories;

import ua.artcode.market.controllers.AppDB;
import ua.artcode.market.controllers.ProxyTerminalController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.interfaces.ITerminal;

public class FactoryITerminal {

    public static ITerminal createITerminalController() {
        return new ProxyTerminalController(new TerminalController
                (new AppDB()));
    }
}
