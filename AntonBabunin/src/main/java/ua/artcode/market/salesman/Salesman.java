package ua.artcode.market.salesman;

import ua.artcode.market.Interfaces.ITerminal;
import ua.artcode.market.bill.Bill;
import ua.artcode.market.product.Product;
import ua.artcode.market.terminal.Terminal;

public class Salesman extends PersonAbstractClass implements ITerminal{

    private String login;
    private String password;

    public Salesman(String fullName, String login, String password) {
        super(fullName);
        this.login = login;
        this.password = password;
    }

    private String getLogin() {
        return login;
    }

    private String getPassword() {
        return password;
    }

    @Override
    public Bill createBill (Object object/*Terminal terminal*/) {
        return  (object != null && object instanceof Terminal &&
                ((Terminal)object).login(this)) ?
                ((Terminal)object).createBill(this) : null;
    }

    /*
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    // Salesman create a new bill at terminal


/*    public void login (Terminal terminal) {
        if (terminal != null) {
            Scanner scanner = new Scanner(System.in);
            String login = "";
            do {
                System.out.println("Enter your login:");
                login = scanner.nextLine();
            } while (login.isEmpty());
            String password = "";
            do {
                System.out.println("Enter your password:");
                password = scanner.nextLine();
            } while (password.isEmpty());

            if (this.getLogin().equals(login) &&
            this.getPassword().equals(password)) {
                terminal.login(this);
            }
        }
    }

*/
    public boolean loginAutomatic (Terminal terminal) {
        for (int i = 0; i < terminal.getSales().length; i++) {
            if ((this.login.equals(terminal.getSales()[i].getLogin()) &&
                this.password.equals((terminal.getSales()[i].getPassword())))) {
                return true;
            }
        }
        return false;
    }

    public boolean addProduct (Terminal terminal, Product product) {
        if (terminal != null && product != null) {
            Bill bill = terminal.addProduct();
            if (bill != null) {
                bill.addProduct(product);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean closeBill(Bill bill, Terminal terminal) {
        if (bill !=null && terminal != null) {
            for (Bill bill1 : terminal.getBills()) {
                if (bill.equals(bill1)) {
                    boolean bool = terminal.closeAndSaveBill(bill);
                    System.out.println(bool);

                    return  bool;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals (Object object) {
        return object != null && object instanceof Salesman &&
                this.getFullName().equals(((Salesman) object).getFullName());
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullName='" + super.getFullName() + '\'' +
                '}';
    }
}
// ----------------------------------------------------------------------------