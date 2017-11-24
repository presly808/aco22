package week1.AbstractFactory;

import week1.controller.IAppDbImpl;
import week1.controller.ITerminalControllerImpl;
import week1.interfaces.ITerminalController;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ITerminalControllerFactory {

    public static ITerminalController create(){
        return new ITerminalControllerImpl(new IAppDbImpl());
    }


}
