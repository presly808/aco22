package ua.artcode.simplehttpserver.hoslders.employee;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.models.AbstractProduct;
import ua.artcode.simplehttpserver.hoslders.HandlerHolder;

import java.io.IOException;

public class HandlerAddProductToBill implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String request = httpExchange.getRequestURI().toString();
        String response = "";

        if (httpExchange.getRequestMethod().equals("POST") &&
                request.equals("/employee/bill/addproduct")) {
            response = postAddProductToBill(httpExchange);
        }


    }

    private AbstractProduct addProductToBill(AbstractProduct productJson) {
//        HandlerHolder.getiTerminalController().addProduct(BILL ID)
        return null;
    }

    private String postAddProductToBill(HttpExchange httpExchange) {
//        HandlerHolder.token(httpExchange);
        AbstractProduct productJson =
                HandlerHolder.productFromJson(httpExchange);

        AbstractProduct addedProduct = addProductToBill(productJson);
        return null;
    }

}
