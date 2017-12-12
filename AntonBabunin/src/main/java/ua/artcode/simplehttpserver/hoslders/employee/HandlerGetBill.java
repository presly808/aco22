package ua.artcode.simplehttpserver.hoslders.employee;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import ua.artcode.market.exclude.exception.BillNotFoundException;
import ua.artcode.market.models.Bill;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;

import java.io.IOException;
import java.io.OutputStream;

public class HandlerGetBill extends HandlerHolder {

    public HandlerGetBill() throws IOException {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String request = httpExchange.getRequestURI().toString();
        System.out.println(httpExchange.getRequestMethod());



        if (httpExchange.getRequestMethod().equals("POST")) {
            postGetBill(httpExchange, request);
        }
        if (httpExchange.getRequestMethod().equals("GET")) {
            getGetBill(httpExchange, request);
        }
    }

    private void postGetBill(HttpExchange httpExchange, String request)
            throws IOException{

        System.out.println(request);

        int id = -1;
        if (request != null && request.contains("/employee/getbill?")) {
            id = Integer.parseInt(request.split("\\?")[1].
                    split("=")[1]);
        }

        Bill bill = null;
        String response = "";
        try {

            bill = getiTerminalController().getIAppDb().findBillById(id);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
            response = e.toString();

        }
        if (bill != null) {
            response = new Gson().toJson(bill);
        }
        try {
            httpExchange.sendResponseHeaders(200, response.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void getGetBill(HttpExchange httpExchange, String request) {
        if (request.equals("/employee/getbill")) {
            System.out.println(httpExchange.getRequestHeaders().entrySet());
        }
    }
}