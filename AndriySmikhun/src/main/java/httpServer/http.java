package httpServer;

import com.sun.deploy.net.HttpRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;



public class http {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/index", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is runing");
        server.createContext("/hello", new myHello());
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            if(t.getRequestMethod().equals("GET")){
                String response = "Hello Get";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {


            }

        }
    }
    static class myHello implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.getRequestMethod().equals("GET");
            String requestURL = httpExchange.getRequestURI().toString();
            System.out.println(requestURL);
            String[] params = requestURL.split("//?")[1].split("&");
            String out = params[0].split("=")[1];
            httpExchange.sendResponseHeaders(200, out.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(out.getBytes());
            os.close();
        }
    }

}
