package ua.artcode.market.view;

import ua.artcode.simplehttpserver.SimpleHttpServer;

public class ServerStart {

    public static void main(String[] args) {
        new SimpleHttpServer().run();
    }
}
