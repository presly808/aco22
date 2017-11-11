package ua.artcode.market.creator;


import ua.artcode.market.salesman.Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesmanCreator {
    private static final int DEFAULT_SALESMAN_NAME = 5;
    public static Salesman salesmanCreate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fullName = "";
        do {
            fullName = reader.readLine();
        } while (fullName.isEmpty());
        String login = fullName;
        String password = "";
        do {
            password = reader.readLine();
        } while (password.isEmpty());

        return new Salesman(fullName, login, password);
    }

    public static Salesman salesmanCreateAutomatic() {

        String fullName = generateFullName();
        String login = "asd";
        String password = "asd";

        return new Salesman(fullName, login, password);
    }

    private static String generateFullName() {
        char[] nameChar = new char[DEFAULT_SALESMAN_NAME];
        for (int i = 0; i < DEFAULT_SALESMAN_NAME; i++) {
            nameChar[i] = (char) ((int)(65+(Math.random()*26)));
        }
        return String.valueOf(nameChar);

    }
}
