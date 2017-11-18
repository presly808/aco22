package ua.artcode.market;

import ua.artcode.market.Interface.IMiniMarket;
import ua.artcode.market.Model.Terminal;

public class MiniMarket implements IMiniMarket {

    public static void main(String[] args) {

        MiniMarket miniMarket = new MiniMarket();

        miniMarket.startMarket();
    }

    @Override
    public boolean startMarket() {

        Terminal.runTerminal();

        return true;
    }
}