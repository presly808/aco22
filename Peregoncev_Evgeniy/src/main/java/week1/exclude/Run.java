package week1.exclude;

import week1.controller.Terminal;
import week1.model.Product;
import week1.model.Salesman;
import week1.view.View;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class Run {

    public static void main(String[] args) {

        Terminal terminal = new Terminal();

        Product product1 = new Product("apricot", 16.5, "#001");
        Product product2 = new Product("banana", 20.0, "#002");
        Product product3 = new Product("watermelon", 50.5, "#003");
        Product product4 = new Product("apple", 8.5, "#004");


        Salesman salesman1 = new Salesman("Inna", "InnaLog", "InnaPass");
        Salesman salesman2 = new Salesman("Vanessa", "VanessaLog", "VanessaPass");
        Salesman salesman3 = new Salesman("Izolda", "IzoldaLog", "IzoldaPass");
        Salesman salesman4 = new Salesman("Dasha", "DashaLog", "DashaPass");


        terminal.getSales()[0] = salesman1;
        terminal.getSales()[1] = salesman2;
        terminal.getSales()[2] = salesman3;
        terminal.getSales()[3] = salesman4;


        terminal.setSalesCountSize(4);

        View view = new View();

        view.run(terminal);

    }

}
