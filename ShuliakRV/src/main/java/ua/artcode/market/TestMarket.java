package ua.artcode.market;

public class TestMarket {

    public static final int DEFAULT_COUNT_PRODUCTS = 100;
    public static final int DEFAULT_COUNT_SALESMEN = 3;
    public static final int DEFAULT_COUNT_BILLS = 2;

    public static void main(String[] args) {


        Product[] p = new Product[DEFAULT_COUNT_PRODUCTS];

        for (int i = 0; i < p.length; i++) {
            p[i] = Utils.generateProduct();
        }

        Salesman[] s = new Salesman[DEFAULT_COUNT_SALESMEN];

        System.out.println("Список продавцов: ");
        System.out.println();

        for (int i = 0; i < s.length; i++) {
            s[i] = Utils.generateSalesman();
        }

        Terminal t = new Terminal(s);

        Salesman saler = t.login();

        if (saler != null) {

            for (int i = 0; i < DEFAULT_COUNT_BILLS; i++) {

                t.createBill(saler);

                for (int j = 0; j < (DEFAULT_COUNT_PRODUCTS * Math.random() + 1); j++) {
                    t.addProduct(p[j]);
                }
                Bill b;
                b = t.closeAndSaveBill();
                b.printBill();
            }

            t.doSomeStatisticStuff();

        } else System.out.println("Неверный логин или пароль");


    }
}
