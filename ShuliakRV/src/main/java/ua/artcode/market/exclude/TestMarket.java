package ua.artcode.market.exclude;

import ua.artcode.market.controller.AppDB;
import ua.artcode.market.controller.TerminalController;
import ua.artcode.market.models.*;

public class TestMarket {

    public static void main(String[] args) {

        AppDB appDB = new AppDB();


        TerminalController t = new TerminalController(appDB);


        Salesman saler = t.login(appDB.getSales()[0].getLogin(),
                appDB.getSales()[0].getPassword());

        if (saler != null) {

            for (int i = 0; i < appDB.DEFAULT_COUNT_BILLS; i++) {

                t.createBill(saler);

                for (int j = 0; j < (int) (appDB.DEFAULT_COUNT_PRODUCTS *
                        Math.random()) + 1;
                     j++) {
                    t.addProduct(appDB.getProducts()[j]);
                }
                Bill b;
                b = t.closeAndSaveBill();
                System.out.println(b);

                System.out.println(t.findBillById(i + 1).getId());
            }

            System.out.println(t.doSomeStatisticStuff());

        } else System.out.println("Incorrect login or password!!!");


    }
}
