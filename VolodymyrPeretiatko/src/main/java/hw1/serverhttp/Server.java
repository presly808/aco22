package hw1.serverhttp;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.sun.net.httpserver.HttpExchange;
import hw1.controller.ITerminal;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Server {

    private static ITerminal mainController;

    public static void main(String[] args) throws Exception {

        //mainController = new ;

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Hello my dear shitcoder!";
            //try {
            //    response = new String(Files.readAllBytes(Paths.get("/index.html")));
            //} catch (Exception e){
            //    System.out.println("Something go wrong");
            //    e.printStackTrace();
            //}

            try {
                Files.walk(Paths.get("/"))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .forEach(System.out::println);
            } catch (Exception e){
               System.out.println("Something go wrong");
                e.printStackTrace();
            }


            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }



}
