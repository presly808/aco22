package week1.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import week1.exeptions.InvalidLoginException;
import week1.interfaces.ITerminalController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {

    private final ITerminalController terminal;

    public LoginHandler(ITerminalController terminal) {
        this.terminal = terminal;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        InputStream inputStream = httpExchange.getRequestBody();

        StringBuilder data = new StringBuilder();
        int val;
        while ((val = inputStream.read()) != -1) {
            data.append((char) (val));
        }
        String jsonString = String.valueOf(data);
        Gson logdata = new Gson();

        LoginData loginData = logdata.fromJson(jsonString, LoginData.class);

        Gson json = new Gson();

        String response = null;
        try {
            response = json.toJson(terminal.login(loginData.login, loginData.pass));
        } catch (InvalidLoginException e) {
            e.printStackTrace();
        }


        assert response != null;
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
