package ua.artcode.market.utils;

import ua.artcode.market.controller.AppDB;
import ua.artcode.market.models.*;

public class Utils {


    public static Product generateProduct() {

        String[] s = {"Meat", "Fish", "Fruit", "Vegetable", "Sausage"};

        return new Product(s[(int) (Math.random() * s.length)] +
                (int) (Math.random() * AppDB.DEFAULT_COUNT_PRODUCTS),
                (Math.random() * 1000));
    }

    public static Salesman generateSalesman() {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya"};

        int index = (int) (Math.random() * s.length);

        String fullname = s[index] +
                (int) (Math.random() * AppDB.DEFAULT_COUNT_SALESMEN);

        String login = s[index];

        String password = "";

        for (int i = 1; (int) i < Math.random() * 10 + 7; i++) {
            password = password + (int) (Math.random() * 10);
        }

        return new Salesman(fullname, login, password);
    }

}
