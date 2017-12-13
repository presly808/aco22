/*
package ua.artcode.simplehttpserver.hoslders.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.exclude.exception.BillNotFoundException;
import ua.artcode.market.exclude.exception.LoginOrPasswordArgumentExeption;
import ua.artcode.market.exclude.exception.LoginOrPasswordNotFoundException;
import ua.artcode.market.json.BillJson;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class HandlerBill implements HttpHandler {

    public HandlerBill() throws IOException {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String request = httpExchange.getRequestURI().toString();
//        System.out.println(httpExchange.getRequestMethod());
        String response = "";
*/
/*        if (httpExchange.getRequestMethod().equals("POST")) {
            postGetBill(httpExchange, request);
        }*//*


        if (httpExchange.getRequestMethod().equals("GET") &&
                request.contains("/employee/getbill?")) {
            try {
                response = getGetBill(httpExchange, request);
                System.out.println(response);
                httpExchange.sendResponseHeaders(200, response.length());
            } catch (LoginOrPasswordArgumentExeption e) {
                e.printStackTrace();
                response = e.toString();
                httpExchange.sendResponseHeaders(403, response.length());
            } catch (LoginOrPasswordNotFoundException e) {
                e.printStackTrace();
                response = e.toString();
                httpExchange.sendResponseHeaders(404, response.length());
            } finally {

                OutputStream outputStream = httpExchange.getResponseBody();

                outputStream.write(response.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private String getGetBill(HttpExchange httpExchange, String request)
            throws IOException, LoginOrPasswordArgumentExeption, LoginOrPasswordNotFoundException {

        System.out.println(httpExchange.getRequestHeaders().entrySet());
        if (!httpExchange.getRequestHeaders().containsKey("Token")) throw new LoginOrPasswordNotFoundException();
        List<String> tokenList = httpExchange.getRequestHeaders().get("Token");
        String userToken = tokenList.get(tokenList.size()-1);


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Bill.class, new BillJson());
        Employee salesman = HandlerHolder.getiTerminalController().findSalesmanByToken(userToken);

        System.out.println("asd");

          int  id = Integer.parseInt(request.split("\\?")[1].
                    split("=")[1]);


        Bill bill = null;
        String response = "";
//        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Bill.class, new BillJson());
        try {

            bill = HandlerHolder.getiTerminalController().getIAppDb().findBillById(id);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
            response = e.toString();

        }
        if (bill != null) {
            response = builder.create().toJson(bill);
        }
        try {
            httpExchange.sendResponseHeaders(200, response.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

*/
/*    private void getGetBill(HttpExchange httpExchange, String request) {
        if (request.equals("/employee/getbill")) {
            System.out.println(httpExchange.getRequestHeaders().entrySet());
        }
    }*//*

}*/
