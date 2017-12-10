package week1.server;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import week1.factories.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;

/**
 * Created by ENIAC on 09.12.2017.
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ITerminalController terminal = ITerminalControllerFactory.create();
        HttpServer server = HttpServer.create(new InetSocketAddress(8800), 0);
        server.createContext("/findBill", new  BillHandler(terminal));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    }

