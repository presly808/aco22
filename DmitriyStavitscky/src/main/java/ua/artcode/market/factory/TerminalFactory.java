package ua.artcode.market.factory;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.proxy.TerminalControllerProxyHistory;

import java.util.ArrayList;

public class TerminalFactory {
    public static TerminalControllerProxyHistory create() {

        return new TerminalControllerProxyHistory(new TerminalController(new AppDB(
                new ArrayList<>(), new Salesman(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), 0)));
    }
}
