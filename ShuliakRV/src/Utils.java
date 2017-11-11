public class Utils {
    public static Product generateProduct() {

        String[] s = {"Мясо", "Крупа", "Рыба", "Фрукт", "Овощ", "Конфета", "Колбаса"};

        return new Product(s[(int) (Math.random() * s.length)].toString() + (int) (Math.random() * TestBill.DEFAULT_COUNT_PRODUCTS), (Math.random() * 100));
    }

    public static Salesman generateSalesman() {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya", "Yuliya"};

        String fullname = s[(int) Math.random() * s.length] + (int) (Math.random() * TestBill.DEFAULT_COUNT_SALESMEN);
        String login = s[(int) (Math.random() * s.length)];

        String password = "";

        for (int i = 1; (int) i < Math.random() * 5 + 7; i++) {
            password = password + (int) (Math.random() * 10);
        }

        System.out.println("Fullname: " + fullname);
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);


        return new Salesman(fullname, login, password);
    }

}
