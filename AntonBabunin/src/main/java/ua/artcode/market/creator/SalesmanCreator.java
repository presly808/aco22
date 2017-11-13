package ua.artcode.market.creator;


import ua.artcode.market.salesman.Salesman;

public class SalesmanCreator {
    private static final int DEFAULT_SALESMAN_NAME = 20;
/*    public static Salesman salesmanCreate() throws IOException {
        BufferedReader reader =
        new BufferedReader(new InputStreamReader(System.in));
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
*/
    public static Salesman salesmanCreateAutomatic() {

        String fullName = "User"+generateFullName();
        String login = fullName;
        String password = fullName;

        return new Salesman(fullName, login, password);
    }

    private static String generateFullName() {

        return String.valueOf(((int)Math.random()*10));

    }
}
