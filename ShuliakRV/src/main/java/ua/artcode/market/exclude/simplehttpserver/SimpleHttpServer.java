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

    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/product", new HandlerProduct());
        server.createContext("/login", new HandlerLogin());
        server.setExecutor(null); // creates a default executor
        server.start();

    }

    public static void sendResponse(HttpExchange t, String response)
            throws IOException {

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    static class HandlerProduct implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = gson.toJson(
                    terminalController.getAppDB().findByProductId(
                            (Integer.parseInt(t.getRequestURI().toString().
                                    split("//?")[1].split("=")[1]))));

            sendResponse(t,response);
        }
    }

    static class HandlerLogin implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            InputStream io = t.getRequestBody();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(io));
            String jsonText = reader.readLine();

            Salesman salesman = gson.fromJson(jsonText, Salesman.class);

            terminalController.getAppDB().getAllSalesman().add(salesman);
            try {
                salesman = terminalController.logIn(salesman.getLogin(),
                        salesman.getPassword());
            } catch (AppException e) {
                e.printStackTrace();
            }

            String response;

            if (salesman != null) {

                Token token = new Token("TOKEN",
                        (long) (Long.MAX_VALUE * Math.random()));

                response = gson.toJson(token);

            } else {
                response = "Not found";
            }

            sendResponse(t,response);

        }

        class Token {

            private String name;
            private Long token;

            public Token(String name, Long token) {
                this.name = name;
                this.token = token;
            }

            @Override
            public String toString() {
                return "Token{" +
                        "name='" + name + '\'' +
                        ", token=" + token +
                        '}';
            }
        }
    }

}

