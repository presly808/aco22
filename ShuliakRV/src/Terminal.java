import java.util.Scanner;

public class Terminal {

    private static final int DEFAULT_AMOUNT_BILLS = 1000;

    private Bill[] bills;
    private Salesman[] salesMen;

    private int numBill;
    private int currentSalesman;

    public Terminal(Salesman[] s) {
        salesMen = s;
        bills = new Bill[DEFAULT_AMOUNT_BILLS];
    }

    public Terminal(Salesman[] s, int amountBills) {
        salesMen = s;
        bills = new Bill[amountBills];
    }

    public int login() {

        if (salesMen == null) return -1;
        else {

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

            if (i == salesMen.length) return -1;

            return currentSalesman;
        }
    }

    public void createBill(Salesman s) {

        bills[numBill] = new Bill(s);

    }

    public void addProduct(Product p) {

        if (p != null) bills[numBill].addProduct(p);

    }

    public void closeAndSaveBill() {

        bills[numBill].closeBill();
        if (bills[numBill].isOpen() == false) {
            numBill++;
        } else {
            bills[numBill] = null;
        }

    }

    public Bill findBillById(int id) {
        for (int i = 0; i < numBill; i++) {
            if (bills[i].getId() == id) return bills[i];
        }

        return null;
    }


}
