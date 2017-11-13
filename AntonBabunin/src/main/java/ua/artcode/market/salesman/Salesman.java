package ua.artcode.market.salesman;



import ua.artcode.market.bill.Bill;
import ua.artcode.market.product.Product;
import ua.artcode.market.terminal.Terminal;

public class Salesman {
    private String fullName;
    private String login;
    private String password;

    public Salesman(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    private String getLogin() {
        return login;
    }

    private String getPassword() {
        return password;
    }

    @Override
    public boolean equals (Object object) {
        if (object != null && object instanceof Salesman) {
            return this.getFullName().equals(((Salesman) object).getFullName());
        } return false;
    }

    // Salesman create a new bill at terminal
    public Bill createBill (Terminal terminal) {
        return  (terminal != null && terminal.login(this)) ?
                terminal.createBill(this) : null;
    }

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
        String login = this.login;
        String password = this.password;

        return login.equals(this.getLogin()) &&
                password.equals(this.getPassword()) &&
                terminal.login(this);
    }

    public void addProduct (Terminal terminal, Product product) {
        if (terminal != null && product != null) {
            Bill bill = terminal.addProduct();
            bill.addProduct(product);
        }
    }
// ----------------------------------------------------------------------------

    public boolean closeBill(Bill bill, Terminal terminal) {
        if (bill !=null && terminal != null) {
            for (Bill bill1 : terminal.getBills()) {
                if (bill == bill1) {
                    boolean bool = terminal.closeAndSaveBill(bill);
                    System.out.println(bool);

                    return  bool;
                }
            }
        }
        return false;
    }
/*
    public void setLogin(String login) {
        this.login = login;
    }



    public void setPassword(String password) {
        this.password = password;
    }*/

    @Override
    public String toString() {
        return "Salesman{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
