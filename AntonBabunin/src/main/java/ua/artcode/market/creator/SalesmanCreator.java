package ua.artcode.market.creator;


import ua.artcode.market.salesman.Salesman;

public class SalesmanCreator {
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
    public static Salesman createSalerman() {
        String fullName = generateFullName();
        String login = fullName;
        String password = fullName;

        return new Salesman(fullName, login, password);
    }

    private static String generateFullName() {
        char[] fullNameChar = new char[((int)(Math.random()*50)) + 1];
        for (int i = 0; i < fullNameChar.length; i++) {
            fullNameChar[i] = (char)((((int)(Math.random()*25) + 65)));
        }
        return String.valueOf(String.valueOf(fullNameChar).charAt(0)).
                concat(String.valueOf(fullNameChar).substring(1).toLowerCase());
    }
}
