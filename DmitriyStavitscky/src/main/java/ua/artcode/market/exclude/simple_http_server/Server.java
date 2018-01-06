package ua.artcode.market.exclude.simple_http_server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalException;
import ua.artcode.market.exclude.simple_http_server.exceptions.ServerException;
import ua.artcode.market.exclude.simple_http_server.utils.InitializationData;
import ua.artcode.market.exclude.simple_http_server.utils.ServerUtils;
import ua.artcode.market.factory.TerminalFactory;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {

        ITerminal terminal = InitializationData.init(TerminalFactory.create());
        Gson gson = new Gson();

        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);

        server.createContext("/showBill", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONAL");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");


            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                int idOfBill = Integer.parseInt(ServerUtils.getParams(httpExchange).get(0));
                String foundBill = gson.toJson(terminal.getAppDB().findBillById(idOfBill));

                httpExchange.sendResponseHeaders(200, foundBill.length());
                outputStream.write(foundBill.getBytes());

            } catch (AppDBException exc) {
                httpExchange.sendResponseHeaders(404, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } catch (ServerException exc) {
                httpExchange.sendResponseHeaders(400, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
            System.out.println("Received a request to show a check");

        });

        server.createContext("/signIn", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            OutputStream outputStream = httpExchange.getResponseBody();


            try {
                List<String> params = ServerUtils.getParams(httpExchange);

                String loginOrName = params.get(0);
                int pass = Integer.parseInt(params.get(1));
                terminal.signIn(loginOrName, pass);

                String outputMessage = terminal.getLoggedSalesman().getLogin() + " logged in";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (AppDBException exc) {
                httpExchange.sendResponseHeaders(404, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } catch (ServerException exc) {
                httpExchange.sendResponseHeaders(400, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext(   "/logOut", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");

            try (OutputStream outputStream = httpExchange.getResponseBody()) {
                terminal.logOut();
                String outputMessage = "you are logged out";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());
            }
        });

        server.createContext("/createBill", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");

            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                terminal.createBill();
                String outputMessage = "bill created";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (TerminalException exc) {
                httpExchange.sendResponseHeaders(401, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext("/addProductToBill", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");

            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                int id = Integer.parseInt(ServerUtils.getParams(httpExchange).get(0));
                terminal.addProductToBill(id);
                String outputMessage = "Added product: " + terminal.getAppDB().findProductById(id).toString();

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (AppDBException exc) {
                httpExchange.sendResponseHeaders(404, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } catch (ServerException exc) {
                httpExchange.sendResponseHeaders(400, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext("/closeAndSaveBill", httpExchange -> {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONAL");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                terminal.closeAndSaveBill();

                String gsonBill = gson.toJson(terminal.getAllBills().get(terminal.getAllBills().size() - 1));
                String outputMessage = gsonBill /*+ "\n Bill closed"*/;

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (SaveBillException exc) {
                httpExchange.sendResponseHeaders(422, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started. Connect to localhost:8009");
    }
}
/*
public class Server {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String requestUrl = httpExchange.getRequestURI().toString();
                System.out.println(requestUrl);
                System.out.println("HTTP method is " + httpExchange.getRequestMethod());

                String[] params = requestUrl.split("\\?")[1].split("&");
                String name = params[0].split("=")[1];

                try(OutputStream outputStream = httpExchange.getResponseBody()) {
                    String s = "Hello " + name;
                    httpExchange.sendResponseHeaders(200, s.length());

                    outputStream.write(s.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        });

        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                if(httpExchange.getRequestMethod().equals("POST")) {
                    Scanner sc = new Scanner(httpExchange.getRequestBody());
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = sc.nextLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    System.out.println(sb.toString());
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started. Connect to localhost:8000/test");
    }
}*/