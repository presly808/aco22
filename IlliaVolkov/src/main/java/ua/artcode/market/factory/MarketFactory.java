package ua.artcode.market.factory;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.proxy.MarketProxy;
import ua.artcode.market.view.MiniMarket;

import java.io.IOException;

public class MarketFactory {

    public static MarketProxy createProxy() throws IOException {
        return new MarketProxy(new MiniMarket(new AppDBImpl()));
    }


}
