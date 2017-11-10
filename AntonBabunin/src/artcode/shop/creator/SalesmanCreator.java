package artcode.shop.creator;

import artcode.shop.salesman.Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesmanCreator {
    private static final int DEFAULT_PRODUCT_NAME = 3;
    public static Salesman salesmanCreate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fullName = reader.readLine();
        String login = fullName;
        String password = reader.readLine();

        return new Salesman(fullName, login, password);
    }

    public static Salesman salesmanCreateAutomatic() {

        String fullName = generateFullName();
        String login = "asd";
        String password = "asd";

        return new Salesman(fullName, login, password);
    }

    private static String generateFullName() {
        char[] nameChar = new char[DEFAULT_PRODUCT_NAME];
        for (int i = 0; i < DEFAULT_PRODUCT_NAME; i++) {
            nameChar[i] = (char) ((int)(65+(Math.random()*26)));
        }
        return String.valueOf(nameChar);

    }
}
