package hw1.serverhttp;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.sun.net.httpserver.HttpExchange;
import hw1.controller.ITerminal;
import hw1.controller.ProxyLoggerTerminal;
import hw1.model.Bill;
import hw1.model.Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Server {

    private static ITerminal mainController;

    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {

        //Controller
        mainController = new ProxyLoggerTerminal();

        //Server
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/login",   new LoginHandler());
        server.createContext("/getBill", new GetBillHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static protected boolean sendResponse(HttpExchange http, int responseCode, String response) throws IOException {

        if (http == null){
            return false;
        }

        http.sendResponseHeaders(responseCode, response.length());

        OutputStream os = http.getResponseBody();
        os.write(response.getBytes());

        os.flush();
        os.close();

        return true;
    }

    static class GetBillHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange http) throws IOException {

            if (!"GET".equals(http.getRequestMethod())) {
                sendResponse(http, 400, "Boris: -fuck you!");
            }

            String request  = http.getRequestURI().getQuery();
            int itemId = Integer.parseInt(request.replaceAll("id=", ""));

            Bill bill = mainController.findBillById(itemId);
            sendResponse(http, 200, gson.toJson(bill));
        }
    }

    static class LoginHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange http) throws IOException {

            if (!"POST".equals(http.getRequestMethod())) {
                sendResponse(http, 400, "Boris: -fuck you!");
            }

            String result = new BufferedReader(new InputStreamReader(http.getRequestBody()))
                                                   .lines()
                                                   .collect(Collectors.joining("\n"));

            HashMap<String, String> logParam = gson.fromJson(result, HashMap.class);

            Boolean logged = mainController.login(logParam.get("name"),
                                                  logParam.get("password"));

            sendResponse(http, 200, logged.toString());

        }
    }



}
