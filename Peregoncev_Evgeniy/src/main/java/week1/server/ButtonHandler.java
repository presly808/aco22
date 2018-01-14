package week1.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import week1.interfaces.ITerminalController;

import java.io.IOException;

public class ButtonHandler implements HttpHandler {
    private final ITerminalController terminal;

    public ButtonHandler(ITerminalController terminal) {

        this.terminal = terminal;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {





    }
}
