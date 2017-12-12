package ua.artcode.simplehttpserver.hoslders.employee;

import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.json.ProductJson;
import ua.artcode.market.models.AbstractProduct;
import ua.artcode.market.models.Product;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlerAddProduct implements HttpHandler {
    public HandlerAddProduct() throws IOException {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String request = httpExchange.getRequestURI().toString();
        System.out.println(request);

        if (httpExchange.getRequestMethod().equals("POST") &&
                request.startsWith("/employee/addproduct")) {
            postAddProduct(httpExchange, request);
        }
        if (httpExchange.getRequestMethod().equals("GET")) {
            getAddProduct(httpExchange, request);
        }
    }

    private void getAddProduct(HttpExchange httpExchange, String request) {

    }

    private void postAddProduct(HttpExchange httpExchange, String request)
            throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(httpExchange.getRequestBody()));
        String line = reader.readLine();

        System.out.println("line " + line);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AbstractProduct.class, new ProductJson());
        AbstractProduct product = builder.create().fromJson(line, Product.class);

    //ПРОБЛЕМА С ДЖЕСОНОМ, как точнее???

        AbstractProduct product1 = HandlerHolder.getiTerminalController().
                getIAppDb().saveProduct(product);
        System.out.println(product1.getId());

        /*
        String response = "";
        try {
            product = getiTerminalController().login(seller);

            Gson gson = new Gson();
            response = String.format("{\"token\":%s}",
            gson.toJson(seller.getToken()));
            System.out.println(response);
            httpExchange.sendResponseHeaders(200, response.length());

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
*/
    }
}
