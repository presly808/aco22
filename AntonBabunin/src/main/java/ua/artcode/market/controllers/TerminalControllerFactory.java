package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminalController;


public class TerminalControllerFactory {

    public static ITerminalController create(){
        return new ITerminalControllerImpl(new IAppDbProxy(new IAppDbImpl()));
    }

}
