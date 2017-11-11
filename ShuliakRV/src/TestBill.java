import javax.sound.midi.Soundbank;

public class TestBill {

    public static final int DEFAULT_COUNT_PRODUCTS = 10;
    public static final int DEFAULT_COUNT_SALESMEN = 3;
    public static final int DEFAULT_COUNT_BILLS = 2;

    public static void main(String[] args) {


        Product[] p = new Product[DEFAULT_COUNT_PRODUCTS];

        for (int i = 0; i < p.length; i++) {
            p[i] = Utils.generateProduct();
        }

        Salesman[] s = new Salesman[DEFAULT_COUNT_SALESMEN];

        for (int i = 0; i < s.length; i++) {
            s[i] = Utils.generateSalesman();
        }

        Terminal t = new Terminal(s);

        Salesman saler = t.login();

        if (saler !=null) {

            for (int i = 0; i < DEFAULT_COUNT_BILLS; i++) {
                t.createBill(saler);
                for (int j = 0; j < (int) (DEFAULT_COUNT_PRODUCTS * Math.random()); j++) {
                    t.addProduct(p[j]);
                }
                Bill b;
                b = t.closeAndSaveBill();
                b.printBill();
            }

            System.out.println("Минимальная сумма чека: " + t.getMin());
            System.out.println("Максимальная сумма чека: " + t.getMax());
            System.out.println("Средняя сумма чека: " + t.getAverage());
        } else System.out.println("Неверный логин или пароль");


    }
}
