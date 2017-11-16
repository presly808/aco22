package tests;

import org.junit.Assert;
import ua.artcode.market.creator.TerminalCreator;
import ua.artcode.market.terminal.Terminal;

public class RunTestTerminalCreator {
    public void testTerminalCreator() {
        TerminalCreator tc = new TerminalCreator();
        Assert.assertNotNull(tc);
    }
}
