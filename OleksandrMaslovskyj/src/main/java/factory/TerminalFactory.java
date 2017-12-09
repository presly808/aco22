package factory;

import controllers.BillController;
import controllers.TerminalController;

public final class TerminalFactory {

    public static TerminalController create(BillController billController){
        return new TerminalController(billController);
    }
}
