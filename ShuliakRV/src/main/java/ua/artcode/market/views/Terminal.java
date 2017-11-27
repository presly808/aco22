package ua.artcode.market.views;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Salesman;

public class Terminal {

    private ITerminal terminalController;

    private Salesman loggedSalesman;

    public Terminal(ITerminal terminalController) {

        this.terminalController = terminalController;
    }

    public void logIn(String login, String pass) {

        loggedSalesman = terminalController.logIn(login, pass);

    }


}
