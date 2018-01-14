package week1.server;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import week1.exeptions.InvalidLoginException;
import week1.factories.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;

import static week1.utils.TerminalUtils.fillListOfProductsAndSalesmans;

/**
 * Created by ENIAC on 09.12.2017.
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ITerminalController terminal = ITerminalControllerFactory.create();
        fillListOfProductsAndSalesmans(terminal.getDb());

        try {
            terminal.login("2","3");
        } catch (InvalidLoginException e) {
            e.printStackTrace();
        }

        terminal.createBill();
        terminal.closeBill(0);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new  LoginHandler(terminal));
        server.createContext("/bill", new  BillHandler(terminal));
        server.createContext("/button", new  ButtonHandler(terminal));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    }

