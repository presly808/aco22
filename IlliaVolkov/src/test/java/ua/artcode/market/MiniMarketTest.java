package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.market.view.MiniMarket;

public class MiniMarketTest {
    @Test
    public void startMarket() throws Exception {

        MiniMarket miniMarket = new MiniMarket();

        boolean rez = true;

        Assert.assertEquals(true, rez);
    }

}