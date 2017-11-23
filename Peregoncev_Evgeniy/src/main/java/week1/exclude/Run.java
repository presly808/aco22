package week1.exclude;

import week1.controller.IAppDb;
import week1.controller.IAppDbImpl;
import week1.controller.ITerminalControllerImpl;
import week1.model.Product;
import week1.view.View;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class Run {

    public static void main(String[] args) {

        ITerminalControllerImpl terminal = new ITerminalControllerImpl(new IAppDbImpl());

        View view = new View();

        view.run(terminal);

    }

}
