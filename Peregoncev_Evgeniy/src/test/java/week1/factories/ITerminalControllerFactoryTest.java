package week1.factories;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ITerminalControllerFactoryTest {

    @Test
    public void create() throws Exception {

        assertNotNull(ITerminalControllerFactory.create());
    }

}