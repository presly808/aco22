package ua.artcode.market.factory;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.proxy.TerminalControllerProxyHistory;

public class TerminalFactory {
    public static TerminalControllerProxyHistory create() {
        return new TerminalControllerProxyHistory(new TerminalController(new AppDB()));
    }
}
