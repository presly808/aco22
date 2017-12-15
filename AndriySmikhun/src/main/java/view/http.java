package view;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import sun.misc.IOUtils;
import week3.model.Product;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;


public class http {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8800), 0);
        server.createContext("/index", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is runing");
        //server.createContext("/hello", new myHello());
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            if (t.getRequestMethod().equals("POST")) {

                System.out.println(t.getRequestURI());

                String result = new BufferedReader(new InputStreamReader(t.getRequestBody()))
                        .lines().collect(Collectors.joining("\n"));

                System.out.println(result);

                Gson gson = new Gson();
                Product pr = new Product(1, "JDK", 25.0);
                InputStream stream = t.getRequestBody();
                String istr = stream.toString();
                String json = gson.toJson(pr);
                t.sendResponseHeaders(200, json.length());
                OutputStream os = t.getResponseBody();
                os.write(json.getBytes());
                os.close();
            }

        }
    }
   
}
