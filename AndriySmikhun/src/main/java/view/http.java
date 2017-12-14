package view;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import week3.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;




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

            if(t.getRequestMethod().equals("POST")){
                Gson gson = new Gson();
                Product pr = new Product(1,"JDK",25.0);
                InputStream stream = t.getRequestBody();
                String istr = stream.toString();
                String json = gson.toJson(pr);
                t.sendResponseHeaders(200, json.length());
                OutputStream os = t.getResponseBody();
                os.write(json.getBytes());
                os.close();
            } else {


            }

        }
    }
   /* static class myHello implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.getRequestMethod().equals("POST");
            String requestURL = httpExchange.getRequestURI().toString();
            System.out.println(requestURL);
            String[] params = requestURL.split("//?")[1].split("&");
            String out = params[0].split("=")[1];
            httpExchange.sendResponseHeaders(200, out.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(out.getBytes());
            os.close();
        }
    }*/

}
