package week1.view.server_handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import week1.controller.ITerminalController;
import week1.controller.ITerminalControllerFactory;
import week1.exceptions.InvalidBillIdException;

import java.io.IOException;
import java.io.OutputStream;

public class GetBillByIdHandler implements HttpHandler {

    private ITerminalController terminalController = ITerminalControllerFactory.create();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String requestUrl = httpExchange.getRequestURI().toString();

        String[] params = requestUrl.split("\\?");
        String id = params[1].split("=")[1];

        try(OutputStream outputStream = httpExchange.getResponseBody()) {

            try {
                Gson gson = new Gson();

                String bill = gson.toJson(terminalController.findBillById(Integer.parseInt(id)));

                httpExchange.sendResponseHeaders(200, bill.length());

                outputStream.write(bill.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (InvalidBillIdException e) {
                e.printStackTrace();
            }

        }
    }
}
