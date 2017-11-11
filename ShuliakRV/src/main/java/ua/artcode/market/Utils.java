package ua.artcode.market;

public class Utils {

    public static Product generateProduct() {

        String[] s = {"Мясо", "Крупа", "Рыба", "Фрукт", "Овощ", "Конфета", "Колбаса"};

        return new Product(s[(int) (Math.random() * s.length)].toString() + (int) (Math.random() * TestMarket.DEFAULT_COUNT_PRODUCTS), (Math.random() * 100));
    }

    public static Salesman generateSalesman() {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya", "Yuliya"};

        int index = (int) (Math.random() * s.length);

        String fullname = s[index] + (int) (Math.random() * TestMarket.DEFAULT_COUNT_SALESMEN);
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
