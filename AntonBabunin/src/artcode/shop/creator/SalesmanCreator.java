package artcode.shop.creator;

import artcode.shop.salesman.Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesmanCreator {
    public static Salesman salesmanCreate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fullName = reader.readLine();
        String login = fullName;
        String password = reader.readLine();

        return new Salesman(fullName, login, password);
    }

    public static Salesman salesmanCreateAutomatic() {

        String fullName = "asd";
        String login = "asd";
        String password = "asd";

        return new Salesman(fullName, login, password);
    }
}
