package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ISalesman;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

import java.util.ArrayList;

public class SalesmanController implements ISalesman{

    private TerminalController terminalController;

    private ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    ArrayList<Terminal> terminals;

    public SalesmanController(){
        this.terminalController = new TerminalController();
        this.terminals = new ArrayList<Terminal>();
    }


    @Override
    public boolean createBill(Terminal terminal, Bill bill) {
        if (bill != null && terminal != null) {
            terminal.getBills().add(bill);
            terminalController.createBill(bill);
            return true;
        }
        return false;
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
        for (int i = 0; i < terminal.getSales().size(); i++) {
            if (login.equals(terminal.getSales().get(i).getLogin()) &&
                password.equals((terminal.getSales().get(i).getPassword()))){

                return terminal.getSales().get(i);
            }
        }
        terminals.add(terminal);
        return terminal.getSales().get(terminal.getSales().size());
    }

    @Override
    public boolean closeAndSafeBill(Terminal terminal, Bill bill) {
        if (terminal == null || bill == null) return false;

        return terminalController.closeAndSafeBill(bill);
    }
}

