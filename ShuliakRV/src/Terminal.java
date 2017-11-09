import java.util.Scanner;

public class Terminal {
    private Bill[] bills;
    private Salesman[] salesMen;
    private int numSalesman;

    public Terminal(Salesman[] s, int countBills) {
        salesMen = s;
        bills = new Bill[countBills];
    }

    public Salesman login() {

        if (salesMen != null) {

            Scanner scan = new Scanner(System.in);

            System.out.println("Введите логин: ");
            String login = scan.nextLine();
            System.out.println("Введите пароль: ");
            String password = scan.nextLine();

            int i;

            for (i = 0; i < salesMen.length; i++) {
                if ((salesMen[i].getLogin() == login) && (salesMen[i].getPassword() == password))
                    break;
            }

            if (i == salesMen.length) return null;

            return salesMen[i];
        }
    }

    public void createBill() {
        Bill b = new Bill(salesMen[numSalesman], );
    }
}
}
