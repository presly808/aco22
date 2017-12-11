package ua.artcode.simplehttpserver.hoslders.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.exclude.exception.BillNotFoundException;
import ua.artcode.market.exclude.exception.LoginOrPasswordArgumentExeption;
import ua.artcode.market.exclude.exception.LoginOrPasswordNotFoundException;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.json.SalesmanToJson;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.utils.Generator;

import java.io.*;

public class HandlerHolder implements HttpHandler {

    private ITerminalController iTerminalController;

    public HandlerHolder() throws IOException {
        this.iTerminalController = TerminalControllerFactory.create();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String request = httpExchange.getRequestURI().toString();
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

    }

    public static class HandlerGetBill extends HandlerHolder{

        public HandlerGetBill() throws IOException {
        }



        @Override
        public void handle(HttpExchange httpExchange) throws IOException {


            if (httpExchange.getRequestMethod().equals("POST")) {
                postGetBill(httpExchange);
            }
            if (httpExchange.getRequestMethod().equals("GET")) {
                getGetBill(httpExchange);
            }
        }

        private void postGetBill(HttpExchange httpExchange) throws IOException{
            String request = httpExchange.getRequestURI().toString();
            System.out.println(request);

            int id = -1;
            if (request != null && request.contains("/employee/getbill?")) {
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

        private void getGetBill(HttpExchange httpExchange) {
            //вернуть саму страницу
        }
    }

    public static class HandlerLoginPost extends HandlerHolder {

        public HandlerLoginPost() throws IOException {
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String request = httpExchange.getRequestURI().toString();

            if (httpExchange.getRequestMethod().equals("POST") &&
                    request.startsWith("/login")) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpExchange.getRequestBody()));
                String line = reader.readLine();

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Employee.class, new SalesmanToJson());
                Employee seller = builder.create().fromJson(line, Salesman.class);

                OutputStream outputStream = null;
                String response = "";
                try {
                    seller = super.iTerminalController.login(seller);
                    if (seller != null) {
                        response = Generator.generateSalesmanHashCode(seller.hashCode() * 3);
                        httpExchange.sendResponseHeaders(200, response.length());
                    }
                } catch (LoginOrPasswordArgumentExeption exception) {
                    exception.printStackTrace();
                    response = exception.toString();
                    httpExchange.sendResponseHeaders(404, response.length());
                } catch (LoginOrPasswordNotFoundException exception) {
                    exception.printStackTrace();
                    response = exception.toString();
                    httpExchange.sendResponseHeaders(403, response.length());
                }
                outputStream = httpExchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.flush();
                outputStream.close();

            }
        }
    }

    public static class HandlerEmployee implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

        }

    }

    public static class HandlerGetBills extends HandlerHolder {
        public HandlerGetBills() throws IOException {
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

        }
    }

    public static class HandlerAddProduct extends HandlerHolder {
        public HandlerAddProduct() throws IOException {
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

        }
    }

    public static class HandlerCreateBill extends HandlerHolder {
        public HandlerCreateBill() throws IOException {
        }
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

        }

    }
}