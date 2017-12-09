package week1.exeptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.controllers.ProxyTerminalControllerImpl;
import week1.factories.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;

import static org.junit.Assert.*;
import static week1.utils.TerminalUtils.fillListOfProductsAndSalesmans;

/**
 * Created by ENIAC on 09.12.2017.
 */
public class InvalidLoginExceptionTest {
    private ITerminalController testTerminal;

    @Before
    public void setUp() throws Exception {
        testTerminal = new ProxyTerminalControllerImpl(ITerminalControllerFactory.create());
        fillListOfProductsAndSalesmans(testTerminal.getDb());
    }

    @After
    public void tearDown() throws Exception {
        testTerminal = null;
    }


    @Test(expected = InvalidLoginException.class)

    public void invalidLoginException() throws InvalidLoginException {
        testTerminal.login("", "dd");

    }

}