package ua.artcode.simplehttpserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.exclude.exception.BillNotFoundException;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.json.SalesmanToJson;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Salesman;

import java.io.*;

public class HandlerHolder implements HttpHandler {

    private ITerminalController iTerminalController;


    HandlerHolder() throws IOException {
        this.iTerminalController = TerminalControllerFactory.create();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String request = httpExchange.getRequestURI().toString();
        System.out.println(httpExchange.getRequestMethod());
        if (httpExchange.getRequestMethod().equals("GET") &&
                request.equals("/")) {
            File file = new File("\\Projects\\Java\\Gesserok\\aco22\\" +
                    "AntonBabunin\\html\\index.html");
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream os = httpExchange.getResponseBody();
            FileInputStream fis = new FileInputStream(file);

            while (fis.available() > 0) {
                os.write(fis.read());
            }

            fis.close();
            os.close();
        }
        if (httpExchange.getRequestMethod().equals("POST")) {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpExchange.getRequestBody()));
            String line = reader.readLine();


            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Salesman.class, new SalesmanToJson());
            Salesman seller = builder.create().fromJson(line, Salesman.class);
            System.out.println(seller.getLogin());

        }
    }

    static class HandlerGetBill extends HandlerHolder {

        HandlerGetBill() throws IOException {
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String request = httpExchange.getRequestURI().toString();
            System.out.println(request);

            int id = -1;
            if (request != null && request.contains("?")) {
                id = Integer.parseInt(request.split("\\?")[1].
                        split("=")[1]);
            }

            Bill bill = null;
            String response = "";
            try {
                bill = super.iTerminalController.getIAppDb().findBillById(id);
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
    }
}