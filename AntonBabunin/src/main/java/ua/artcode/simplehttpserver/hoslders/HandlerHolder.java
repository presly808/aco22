package ua.artcode.simplehttpserver.hoslders;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.interfaces.ITerminalController;

import java.io.*;

public class HandlerHolder implements HttpHandler {

    private static ITerminalController iTerminalController;

    public static ITerminalController getiTerminalController() {
        return iTerminalController;
    }

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
}