package view;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;

import week3.appdb.IappDB;
import week3.appdb.IappDBimpl;
import week3.model.Bill;
import week3.model.Salesman;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.stream.Collectors;


public class Http {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8800), 0);
        server.createContext("/index", new Login());
        server.createContext("/getbill", new GetBill());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is runing");
    }

    static class Login implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            if (t.getRequestMethod().equals("POST")) {
                String result = new BufferedReader(new InputStreamReader(t.getRequestBody()))
                        .lines().collect(Collectors.joining("\n"));

                result = result.replace("&","=");
                String[] parameters = result.split("=");

                String login = parameters[1];
                String password = parameters[3];

                Gson gson = new Gson();
                Salesman user = new Salesman(1,login, password, "Andrii Smikhun");

                String json = gson.toJson(user);
                t.sendResponseHeaders(200, json.length());
                OutputStream os = t.getResponseBody();
                os.write(json.getBytes());
                os.close();
            }

        }
    }

    static class GetBill implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            if (t.getRequestMethod().equals("POST")) {
                String result = new BufferedReader(new InputStreamReader(t.getRequestBody()))
                        .lines().collect(Collectors.joining("\n"));


                String[] parameters = result.split("=");
                int index = Integer.parseInt(parameters[1]);
                IappDB db = new IappDBimpl();
                Bill bill = db.findeBillByID(index);
                Gson gson = new Gson();
                String json = gson.toJson(bill);
                t.sendResponseHeaders(200, json.length());
                OutputStream os = t.getResponseBody();
                os.write(json.getBytes());
                os.close();
            }

        }
    }

}
