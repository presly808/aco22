package artcode.shop.salesman;

import artcode.shop.bill.Bill;
import artcode.shop.product.Product;
import artcode.shop.terminal.Terminal;

import java.util.Scanner;

public class Salesman {
    private String fullName;
    private String login;
    private String password;

        public Salesman() {

    }
//    public Salesman(String fullName) {
//        this.fullName = fullName;
//    }

    public Salesman(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public boolean equals (Salesman salesman) {
        if (salesman != null) {
            return this.fullName.equals(salesman.fullName);
        }
        return false;
    }



//    public Bill createBill (Terminal terminal) { // Salesman create a new bill at terminal
//        return  (terminal != null) ? terminal.createBill(this) : null;
//    }

public void addProduct (Terminal terminal, Product product) {
    Bill bill = terminal.addProduct();
    bill.addProduct(product);
}
    public void login (Terminal terminal) {
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

            if (this.getLogin().equals(login) && this.getPassword().equals(password)) {
                terminal.login(this);
            }
        }
    }



    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
