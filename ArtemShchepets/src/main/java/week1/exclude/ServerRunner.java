package week1.exclude;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import week1.view.server_handlers.GetBillByIdHandler;

public class ServerRunner {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/getBillById", new GetBillByIdHandler());
        server.createContext("/getBillById", new GetBillByIdHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }


}
