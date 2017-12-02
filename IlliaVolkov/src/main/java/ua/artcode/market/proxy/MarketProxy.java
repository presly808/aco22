package ua.artcode.market.proxy;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.view.MiniMarket;

public class MarketProxy {

    public MiniMarket getMiniMarket() { return miniMarket; }

    private MiniMarket miniMarket;

    public MarketProxy(MiniMarket miniMarket){ this.miniMarket = miniMarket; }
}
