package week1.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import week1.exeptions.InvalidLoginException;
import week1.interfaces.ITerminalController;

import java.io.IOException;
import java.io.OutputStream;

import static week1.utils.TerminalUtils.fillListOfProductsAndSalesmans;

/**
 * Created by ENIAC on 09.12.2017.
 */

public class BillHandler implements HttpHandler {

    private final ITerminalController terminal;

    public BillHandler(ITerminalController terminal) {

        this.terminal = terminal;
        fillListOfProductsAndSalesmans(terminal.getDb());
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {




        String URL = String.valueOf(httpExchange.getRequestURI());
        String[] id = URL.split("=");
        int billId = Integer.parseInt(id[1]);

        try {
            terminal.login("2","3");
        } catch (InvalidLoginException e) {
            e.printStackTrace();
        }

        terminal.createBill();
        terminal.closeBill(0);
        terminal.findBillById(billId);
        String response = terminal.findBillById(billId).toString();
        httpExchange.sendResponseHeaders(200, response.length());
        System.out.println(terminal.findBillById(billId).toString());

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

