package ua.artcode.market.exclude.simple_http_server.utils;

import ua.artcode.market.controller.ITerminal;

public class InitializationData {
    public static ITerminal init(ITerminal terminal) throws Exception {
        String name1 = "Dima Stavitscky";
        String name2 = "Ivan Raskolnikov";
        String name3 = "Kolia Ivanov";

        String login1 = "lDima";
        String login2 = "lIvan";
        String login3 = "lKolia";

        int pass1 = 123;
        int pass2 = 456;
        int pass3 = 789;

        terminal.getAppDB().addProductToDataBase("Milk", 100);
        terminal.getAppDB().addProductToDataBase("Apple", 70);
        terminal.getAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        terminal.signIn(login3, pass3);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.addProductToBill(3);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(name1, pass1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(login2, pass2);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.getAppDB().findSalesmanByLoginOrName(login1).setDirector(true);

        return terminal;
    }
}
