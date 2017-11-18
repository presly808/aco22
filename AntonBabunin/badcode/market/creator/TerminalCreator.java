package ua.artcode.market.creator;

import ua.artcode.market.bill.Bill;
import ua.artcode.market.salesman.Salesman;
import ua.artcode.market.terminal.Terminal;

public class TerminalCreator {

    private static final int DEFAULT_SIZE = 20;
    private static int id = 0;


    public static Terminal terminalCreation () {
        id = generateId();
        Bill[] bills = new Bill[DEFAULT_SIZE];
        Salesman[] sales = new Salesman[DEFAULT_SIZE];

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            sales[i] = SalesmanCreator.createSalerman();
        }

        return new Terminal(id, bills, sales);
    }

    private static int generateId() {

        return ++id;
    }
}
