package week1.controller;

import week1.database.IAppDBImpl;

public class ITerminalControllerFactory {

    public static ITerminalController create() {

        return new ITerminalControllerImpl( new IAppDBImpl());
    }
}
