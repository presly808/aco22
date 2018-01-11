package ua.artcode.market.exclude.simplehttpserver;

import java.io.*;
import java.net.InetSocketAddress;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
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

        String SERVER_PORT = System.getenv("PORT");
        if (SERVER_PORT == null) {
            SERVER_PORT = "8000";
        }
        HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(SERVER_PORT)), 0);
        server.createContext("/product", new HandlerProduct());
        server.createContext("/login", new HandlerLogin());
        server.createContext("/login.html", new HandlerLoginHtml());
        server.setExecutor(null); // creates a default executor
        server.start();

    }

    public static void sendResponse(HttpExchange t, String response, int code)
            throws IOException {
        Headers headers = t.getResponseHeaders();
        headers.add("Content-Type", "text/html");
        t.sendResponseHeaders(code, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    public static void sendResponseFromFile(HttpExchange t, String filename, int code)
            throws IOException {

        File f = new File(SimpleHttpServer.class.getClassLoader().getResource(filename).getFile());
        InputStream in = new FileInputStream(f);
        Headers headers = t.getResponseHeaders();
        headers.add("Content-Type", "text/html");
        t.sendResponseHeaders(code, f.length());
        OutputStream os = t.getResponseBody();

        while (in.available() > 0) {
            os.write(in.read());
        }
        in.close();
        os.flush();
        os.close();

    }

    static class HandlerLoginHtml implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            sendResponseFromFile(t,"login.html",200);

        }
    }

    static class HandlerProduct implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = gson.toJson(
                    terminalController.getAppDB().findByProductId(
                            (Integer.parseInt(t.getRequestURI().toString().
                                    split("//?")[1].split("=")[1]))));

            sendResponse(t, response, 200);
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

            try {
                salesman = terminalController.logIn(salesman.getLogin(),
                        salesman.getPassword());
                sendResponse(t, salesman.toString(), 200);
            } catch (AppException e) {
                e.printStackTrace();
                sendResponse(t, e.toString(), 200);
            }

        }
    }

}

