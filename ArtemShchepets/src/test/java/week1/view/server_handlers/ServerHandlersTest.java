package week1.view.server_handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;

import java.net.InetSocketAddress;


public class ServerHandlersTest {

    private HttpServer server;

    @Test
    public void testCreate() throws Exception {
       server = HttpServer.create(new InetSocketAddress(8000), 0);
       server.createContext("/getBillById", new GetBillByIdHandler());

        HttpHandler handler = new GetBillByIdHandler();

    }
}
