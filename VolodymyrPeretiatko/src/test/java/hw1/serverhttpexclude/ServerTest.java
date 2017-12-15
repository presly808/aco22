package hw1.serverhttpexclude;

import org.junit.Assert;
import org.junit.Test;

public class ServerTest {

    @Test
    public void LoginHandlerTest(){

        Server.LoginHandler lh = new Server.LoginHandler();

        Assert.assertTrue(lh != null);

    }

    @Test
    public void GetBillHandlerTest(){

        Server.GetBillHandler bh = new Server.GetBillHandler();

        Assert.assertTrue(bh != null);

    }

    //@Test
    //public void sendResponseTest() throws Exception {
    //   Assert.assertFalse(Server.sendResponse(null, 0 , "1"));
    //    Assert.assertTrue(false == Server.sendResponse(null, 200 , "3"));
    //}

}
