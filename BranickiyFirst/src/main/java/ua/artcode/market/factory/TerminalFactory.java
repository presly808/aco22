package src.main.java.ua.artcode.market.factory;

import src.main.java.ua.artcode.market.appDB.IAppDB;
import src.main.java.ua.artcode.market.controllers.ITerminalControllerImpl;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.proxy.TerminalControllerProxy;

import java.util.ArrayList;

public  class TerminalFactory {
    public static TerminalControllerProxy create() {
        return new TerminalControllerProxy(new ITerminalControllerImpl(new IAppDB(
                new ArrayList<>(), new Salesman(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), 0) ));
    }
}
