package ua.artcode.market.proxy;

import ua.artcode.market.view.MiniMarket;

public class MarketProxy {

    private MiniMarket miniMarket;

    public MarketProxy(MiniMarket miniMarket){ this.miniMarket = miniMarket; }

    public MiniMarket getMiniMarket() { return miniMarket; }
}
