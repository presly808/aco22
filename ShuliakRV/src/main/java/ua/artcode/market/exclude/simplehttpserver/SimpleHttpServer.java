package ua.artcode.market.exclude.simplehttpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;


public class SimpleHttpServer {

    private static ITerminal terminalController = FactoryITerminal.
            createITerminalController();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = terminalController.getAppDB().findByProductId(
            (Integer.parseInt(t.getRequestURI().toString().
                    split("//?")[1].split("=")[1]))).getName();


            //String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

