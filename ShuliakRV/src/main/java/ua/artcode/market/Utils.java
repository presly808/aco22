package ua.artcode.market;

public class Utils {

    private static final int DEFAULT_COUNT_SALESMEN = 10;

    private static final int DEFAULT_COUNT_PRODUCTS = 10;

    public static Product generateProduct() {

        String[] s =
                {"Meat", "Fish", "Fruit", "Vegetable", "Sausage"};

        return new Product(s[(int) (Math.random() * s.length)] +
                (int) (Math.random() * DEFAULT_COUNT_PRODUCTS),
                (Math.random() * 100));
    }

    public static Salesman generateSalesman() {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya"};

        int index = (int) (Math.random() * s.length);

        String fullname = s[index] +
                (int) (Math.random() * DEFAULT_COUNT_SALESMEN);
        String login = s[index];

        String password = "";

        for (int i = 1; (int) i < Math.random() * 5 + 7; i++) {
            password = password + (int) (Math.random() * 10);
        }

        System.out.println("Fullname: " + fullname);
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);

        System.out.println();


        return new Salesman(fullname, login, password);
    }

}
