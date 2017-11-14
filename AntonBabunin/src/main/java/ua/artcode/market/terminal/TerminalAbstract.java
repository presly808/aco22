package ua.artcode.market.terminal;


import ua.artcode.market.creator.TerminalCreator;

public abstract class TerminalAbstract {

    private int id;

    public TerminalAbstract(int countTerminal) {
        id = countTerminal;
    }

    public int getId() {
        return id;
    }



}
