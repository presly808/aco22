package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminalController;


public class TerminalFactory {

    public static ITerminalController create(){
        return new ITerminalControllerImpl(new IAppDbProxy(new IAppDbImpl()));
    }

}
