package week1.exclude;

import week1.controller.Terminal;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Time;
import week1.view.ConsoleView;

public class Run {

    public static void main(String[] args) {

        Seller[] sellers = new Seller[5];

        sellers[0] = new Seller("NadyaHoroshun", 22, "worker1", "password1");
        sellers[1] = new Seller("AntonVorobey", 17, "worker2", "password2");
        sellers[2] = new Seller("VasyaPupkin", 59, "worker3", "password3");
        sellers[3] = new Seller("AnyaTupova", 14, "worker4", "password4");
        sellers[4] = new Seller(null, 20, "worker5", "password5");


        Product product1 = new Product("Milk", 11.20, "#03242341");
        Product product2 = new Product("Cheese", 2.05, "#0341");
        Product product3 = new Product("Water", 33.5, "#01");
        Product product4 = new Product(null, 7.55, "#222");


        Bill[] bills = new Bill[3];
        bills[0] = new Bill(sellers[4]);
        bills[1] = new Bill(sellers[0]);
        bills[2] = new Bill(sellers[3]);

        bills[0].addProduct(product4);
        bills[1].addProduct(product1);
        bills[1].addProduct(product2);
        bills[2].addProduct(product1);
        bills[2].addProduct(product2);
        bills[2].addProduct(product3);
        bills[2].addProduct(product4);

        bills[0].setTime(new Time(12, 12, 12));
        bills[1].setTime(new Time(10, 8, 40));
        bills[2].setTime(new Time(23, 10, 44));

        Terminal terminal = new Terminal(bills, sellers);

        ConsoleView consoleView = new ConsoleView();

        consoleView.runMenu(terminal);
    }
}


