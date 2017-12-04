package week1.exclude;

import week1.factories.ITerminalControllerFactory;
import week1.controllers.ProxyTerminalControllerImpl;
import week1.interfaces.ITerminalController;
import week1.view.View;

import static week1.utils.TerminalUtils.fillListOfProductsAndSalesmans;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class Run {

    public static void main(String[] args) {

        ITerminalController terminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());

        fillListOfProductsAndSalesmans(terminal.getDb());

        View view = new View();

        view.run(terminal);

    }
}