package ua.artcode.market.exclude.simplehttpserver;

import java.io.*;
import java.net.InetSocketAddress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.market.exceptions.AppException;
import ua.artcode.market.factories.FactoryITerminal;
import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.models.Salesman;


public class SimpleHttpServer {

    private static ITerminal terminalController = FactoryITerminal.
            createITerminalController();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/product", new MyHandlerProduct());
        server.createContext("/login", new MyHandlerLogin());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandlerProduct implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = new Gson().toJson(
                    terminalController.getAppDB().findByProductId(
                            (Integer.parseInt(t.getRequestURI().toString().
                                    split("//?")[1].split("=")[1]))));
            //String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class MyHandlerLogin implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            InputStream io = t.getRequestBody();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(io));
            String jsonText = reader.readLine();
            System.out.println(jsonText);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Salesman salesman = gson.fromJson(jsonText, Salesman.class);

            System.out.println(salesman);

            terminalController.getAppDB().getAllSalesman().add(salesman);
            try {
                salesman = terminalController.logIn(salesman.getLogin(),
                        salesman.getPassword());
                System.out.println(salesman);
            } catch (AppException e) {
                e.printStackTrace();
            }

            class Token {

                private String name;
                private Long token;

                public Token(String name, Long token) {
                    this.name = name;
                    this.token = token;
                }
            }

            String response;

            if (salesman != null) {

                response = new Gson().toJson(new Token(
                        "token", (long) (Long.MAX_VALUE * Math.random())));


            } else {
                response = "Not found";
            }

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }

}

