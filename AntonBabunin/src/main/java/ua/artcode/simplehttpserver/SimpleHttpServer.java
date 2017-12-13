package ua.artcode.simplehttpserver;

import com.sun.net.httpserver.HttpServer;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;
import ua.artcode.simplehttpserver.hoslders.employee.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer implements Runnable {

    @Override
    public void run() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new HandlerHolder());
            server.createContext("/login", new HandlerLoginPost());
            server.createContext("/employee", new HandlerEmployee());
            server.createContext("/employee/bill", new HandlerBillCreateOrGet());
            server.createContext("/employee/createproduct", new HandlerCreateNewProduct());
//            server.createContext("/employee/getbill", new HandlerBill());
//            server.createContext("/employee/bills", new HandlerHolder.HandlerGetBills());


        } catch (IOException e) {
            e.printStackTrace();
        }






        if (server != null) {
            server.setExecutor(null);
            server.start();
        }
    }
}
