package ua.artcode.market.controllers;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import ua.artcode.market.interfaces.ISalesman;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public class SalesmanController implements ISalesman{

    private TerminalController terminalController;

    public SalesmanController(){
        this.terminalController = new TerminalController();
    }


    @Override
    public Bill createBill(Terminal terminal, Salesman salesman) {
        if (terminal != null && salesman != null) {
            Bill bill = terminalController.createBill(terminal,salesman);

            return bill;
        }
        return null;
    }

    @Override
    public boolean addProduct(Terminal terminal, Bill bill, Product product) {
        if (terminal == null || bill == null || product == null) return false;

        return this.terminalController.addProduct(bill, product);
    }

    @Override
    public Salesman login(Terminal terminal, String login, String password) {
        if (terminal != null && login != null && password != null) {
            return connect(terminal, login, password);
        }
        return null;
    }

    private Salesman connect(Terminal terminal, String login, String password) {
/*        for (int i = 0; i < terminal.getSales().size(); i++) {
            if (login.equals(terminal.getSales().get(i).getLogin()) &&
                password.equals((terminal.getSales().get(i).getPassword()))){

                return terminal.getSales().get(i);
            }
        }
        return terminal.getSales().get(terminal.getSales().size());*/
        return null;
    }

    @Override
    public boolean closeAndSafeBill(Terminal terminal, Bill bill) {
        if (terminal == null || bill == null) return false;

        return terminalController.closeAndSafeBill(bill);
    }

//    public TerminalController getTerminalController() {
//        return terminalController;
//    }
}

