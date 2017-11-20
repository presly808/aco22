package week1.exclude;

import week1.controller.ITerminalControllerControllerImpl;
import week1.model.Product;
import week1.model.Salesman;
import week1.view.View;

/**
 * Created by ENIAC on 19.11.2017.
 */
public class Run {

    public static void main(String[] args) {

        ITerminalControllerControllerImpl ITerminalControllerImpl = new ITerminalControllerControllerImpl();

        Product product1 = new Product("apricot", 16.5, 1);
        Product product2 = new Product("banana", 20.0, 2);
        Product product3 = new Product("watermelon", 50.5, 3);
        Product product4 = new Product("apple", 8.5, 4);


        Salesman salesman1 = new Salesman("Inna", "InnaLog", "InnaPass");
        Salesman salesman2 = new Salesman("Vanessa", "VanessaLog", "VanessaPass");
        Salesman salesman3 = new Salesman("Izolda", "IzoldaLog", "IzoldaPass");
        Salesman salesman4 = new Salesman("Dasha", "DashaLog", "DashaPass");




        ITerminalControllerImpl.setSalesCountSize(4);

        View view = new View();

        view.run(ITerminalControllerImpl);

    }

}
