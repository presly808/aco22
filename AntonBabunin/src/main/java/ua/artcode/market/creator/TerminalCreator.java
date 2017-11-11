package artcode.shop.creator;

import artcode.shop.bill.Bill;
import artcode.shop.salesman.Salesman;
import artcode.shop.terminal.Terminal;

public class TerminalCreator {

    private static final int DEFAULT_SIZE = 20;

    public static Terminal terminalCreation () {
        Bill[] bills = new Bill[DEFAULT_SIZE];
        Salesman[] sales = new Salesman[DEFAULT_SIZE];

        return new Terminal(bills, sales);
    }
}
