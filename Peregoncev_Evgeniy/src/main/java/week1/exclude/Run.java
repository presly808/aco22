package week1.exclude;

import week1.AbstractFactory.ITerminalControllerFactory;
import week1.ProxyTerminalController.ProxyTerminalControllerImpl;
import week1.interfaces.ITerminalController;
import week1.view.View;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class Run {

    public static void main(String[] args) {

        ITerminalController terminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());

        View view = new View();

        view.run(terminal);

    }

}