package week1.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import week1.interfaces.ITerminalController;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by ENIAC on 09.12.2017.
 */

public class BillHandler implements HttpHandler {

    private final ITerminalController terminal;

    public BillHandler(ITerminalController terminal) {

        this.terminal = terminal;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String URL = String.valueOf(httpExchange.getRequestURI());
        String[] id = URL.split("=");
        int billId = Integer.parseInt(id[1]);

        Gson gson = new Gson();
        String response = gson.toJson(terminal.findBillById(billId));

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

