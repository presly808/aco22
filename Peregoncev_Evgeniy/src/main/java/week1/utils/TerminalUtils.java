package week1.utils;

import week1.interfaces.IAppDb;
import week1.models.Product;
import week1.models.Salesman;

/**
 * Created by ENIAC on 25.11.2017.
 */
public class TerminalUtils {

    public static void fillListOfProductsAndSalesmans(IAppDb iAppDb) {

        iAppDb.getAllSalesMans().add(new Salesman("Inna", "login", "pass", 0));
        iAppDb.getAllSalesMans().add(new Salesman("Zina", "login1", "pass", 1));
        iAppDb.getAllSalesMans().add(new Salesman("1", "2", "3", 2));

        iAppDb.getAllProducts().add(new Product("apricot", 16.5, 1));
        iAppDb.getAllProducts().add(new Product("banana", 20.0, 2));
        iAppDb.getAllProducts().add(new Product("watermelon", 50.5, 3));
        iAppDb.getAllProducts().add(new Product("apple", 8.5, 4));

    }

    public static void makeSalesmansAndSubSalesmans(IAppDb iAppDb) {

        Salesman main = new Salesman(0, 0.00, 1300);

        main.addSubSalesman(new Salesman(1, 0.00, 1200));
        main.addSubSalesman(new Salesman(2, 0.00, 1100));

        main.getSubsalesmans().get(0).addSubSalesman((new Salesman(3, 0.00, 1000)));
        main.getSubsalesmans().get(0).addSubSalesman((new Salesman(4, 0.00, 900)));

        main.getSubsalesmans().get(1).addSubSalesman((new Salesman(5, 0.00, 800)));
        main.getSubsalesmans().get(1).addSubSalesman((new Salesman(6, 0.00, 700)));

        iAppDb.getAllSalesMans().add(main);
    }
}
