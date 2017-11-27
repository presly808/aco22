package ua.artcode.market.view;

import ua.artcode.market.interfaces.IMiniMarket;
import ua.artcode.market.model.Terminal;

public class MiniMarket implements IMiniMarket {

    public static void main(String[] args) {

        MiniMarket miniMarket = new MiniMarket();

        miniMarket.startMarket();
    }

    @Override
    public void startMarket() {

        InterfaceServices.runTerminal();
    }

}