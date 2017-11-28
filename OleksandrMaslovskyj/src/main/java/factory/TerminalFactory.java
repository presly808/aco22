package factory;


import controllers.BillController;
import controllers.TerminalController;

public class TerminalFactory {

    private TerminalFactory() {
    }

    public static TerminalController create(BillController billController){
        return new TerminalController(billController);
    }
}
