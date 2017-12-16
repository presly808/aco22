package ua.artcode.market.io;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.exceptions.AppDBExceptions;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalExceptions;
import ua.artcode.market.factory.TerminalFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

/*
public class Http {
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
}
*/

public class Http {
    public static void main(String[] args) throws IOException, SaveBillException, AppDBExceptions, TerminalExceptions {

        ITerminal terminal = TerminalFactory.create();

        String name1 = "Dima Stavitscky";
        String name2 = "Ivan Raskolnikov";
        String name3 = "Kolia Ivanov";

        String login1 = "lDima";
        String login2 = "lIvan";
        String login3 = "lKolia";

        int pass1 = 123;
        int pass2 = 456;
        int pass3 = 789;

        terminal.getAppDB().addProductToDataBase("Milk", 100);
        terminal.getAppDB().addProductToDataBase("Apple", 70);
        terminal.getAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        terminal.signIn(login3, pass3);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.addProductToBill(3);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(name1, pass1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(login2, pass2);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.getAppDB().findSalesmanByLoginOrName(login1).setDirector(true);

        Gson gson = new Gson();

        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
        server.createContext("/showBill", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String requestUrl = httpExchange.getRequestURI().toString();

                String[] params = requestUrl.split("\\?")[1].split("&");
                int idOfBill = Integer.parseInt(params[0].split("=")[1]);

                try(OutputStream outputStream = httpExchange.getResponseBody()) {

                    String foundBill = gson.toJson(terminal.getAppDB().findBillById(idOfBill));
                    httpExchange.sendResponseHeaders(200, foundBill.length());

                    outputStream.write(foundBill.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (AppDBExceptions ignored) {
                }
            }
        });

        server.createContext("/signIn", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String requestUrl = httpExchange.getRequestURI().toString();

                String[] params = requestUrl.split("\\?")[1].split("&");
                String loginOrName = params[0].split("=")[1];
                int pass = Integer.parseInt(params[1].split("=")[1]);

                OutputStream outputStream = httpExchange.getResponseBody();

                try {
                    terminal.signIn(loginOrName, pass);

                    String outputMessage = terminal.getLoggedSalesman().getLogin() + " logged in";

                    httpExchange.sendResponseHeaders(200, outputMessage.length());
                    outputStream.write(outputMessage.getBytes());

                } catch (AppDBExceptions appDBExceptions) {
                    httpExchange.sendResponseHeaders(999, appDBExceptions.getMessage().length());
                    outputStream.write(appDBExceptions.getMessage().getBytes());

                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            }
        });

        server.createContext("/logOut", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {

                try (OutputStream outputStream = httpExchange.getResponseBody()) {
                    terminal.logOut();
                    String outputMessage = "you are logged out";

                    httpExchange.sendResponseHeaders(200, outputMessage.length());
                    outputStream.write(outputMessage.getBytes());
                }
            }
        });

        server.createContext("/createBill", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                OutputStream outputStream = httpExchange.getResponseBody();

                try {
                    terminal.createBill();
                    String outputMessage = "bill created";

                    httpExchange.sendResponseHeaders(200, outputMessage.length());
                    outputStream.write(outputMessage.getBytes());

                } catch (TerminalExceptions exc) {
                    httpExchange.sendResponseHeaders(998, exc.getMessage().length());
                    outputStream.write(exc.getMessage().getBytes());

                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            }
        });

        server.createContext("/addProductToBill", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String requestUrl = httpExchange.getRequestURI().toString();

                String[] params = requestUrl.split("\\?")[1].split("&");
                int id = Integer.parseInt(params[0].split("=")[1]);

                OutputStream outputStream = httpExchange.getResponseBody();

                try {
                    terminal.addProductToBill(id);
                    String outputMessage = "Added product: " + terminal.getAppDB().findProductById(id).toString();

                    httpExchange.sendResponseHeaders(200, outputMessage.length());
                    outputStream.write(outputMessage.getBytes());

                } catch (AppDBExceptions appDBExceptions) {
                    httpExchange.sendResponseHeaders(997, appDBExceptions.getMessage().length());
                    outputStream.write(appDBExceptions.getMessage().getBytes());

                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            }
        });

        server.createContext("/closeAndSaveBill", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                OutputStream outputStream = httpExchange.getResponseBody();

                try {
                    terminal.closeAndSaveBill();

                    String gsonBill = gson.toJson(terminal.getAllBills().get(terminal.getAllBills().size() - 1));
                    String outputMessage = gsonBill + "\n Bill closed";

                    httpExchange.sendResponseHeaders(200, outputMessage.length());
                    outputStream.write(outputMessage.getBytes());

                } catch (Exception exc) {
                    httpExchange.sendResponseHeaders(996, exc.getMessage().length());
                    outputStream.write(exc.getMessage().getBytes());

                } finally {
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
}

// TODO add lib lombok (getter, setter in annotation)