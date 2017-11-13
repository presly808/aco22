package ua.artcode.market.terminal;

public abstract class TerminalAbstract {

    private int id;

    public TerminalAbstract(int countTerminal) {
        id = countTerminal;
    }

    public int getId() {
        return id;
    }



}
