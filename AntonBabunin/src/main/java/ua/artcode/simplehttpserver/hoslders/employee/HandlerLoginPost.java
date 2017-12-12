package ua.artcode.simplehttpserver.hoslders.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import ua.artcode.market.exclude.exception.LoginOrPasswordArgumentExeption;
import ua.artcode.market.exclude.exception.LoginOrPasswordNotFoundException;
import ua.artcode.market.json.SalesmanToJson;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.utils.Generator;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HandlerLoginPost extends HandlerHolder {

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

            String response = "";
            try {
                seller = getiTerminalController().login(seller);
                if (seller != null) {
                    seller.setToken(Generator.generateToken(15));

                    Gson gson = new Gson();
                    response = String.format("{\"token\":%s}", gson.toJson(seller.getToken()));
                    System.out.println(response);
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
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();

        }
    }
}
