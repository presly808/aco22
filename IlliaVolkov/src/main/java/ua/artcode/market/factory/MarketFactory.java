package ua.artcode.market.factory;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.proxy.MarketProxy;
import ua.artcode.market.view.MiniMarket;

public class MarketFactory {

    public static MarketProxy createProxy() {
        return new MarketProxy(new MiniMarket(new AppDBImpl()));
    }


}
