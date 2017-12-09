package ua.artcode.simplehttpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer implements Runnable {

    @Override
    public void run() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(9090), 0);
            server.createContext("/", new HandlerHolder());
            server.createContext("/bills", new HandlerHolder.HandlerGetBill());
        } catch (IOException e) {
            e.printStackTrace();
        }






        if (server != null) {
            server.setExecutor(null);
            server.start();
        }
    }
}
