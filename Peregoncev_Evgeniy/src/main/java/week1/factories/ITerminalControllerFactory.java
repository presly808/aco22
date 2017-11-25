package week1.factories;

import week1.controllers.IAppDbImpl;
import week1.controllers.ITerminalControllerImpl;
import week1.interfaces.ITerminalController;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ITerminalControllerFactory {

    public static ITerminalController create(){

        return new ITerminalControllerImpl(new IAppDbImpl());
    }




}
