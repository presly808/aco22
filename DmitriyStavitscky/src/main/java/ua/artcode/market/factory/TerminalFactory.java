package ua.artcode.market.factory;

import ua.artcode.market.appdb.AppDB;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.proxy.TerminalControllerProxy;

public class TerminalFactory {
    public static TerminalControllerProxy create() {
        return new TerminalControllerProxy(new TerminalController(new AppDB()));
    }
}
