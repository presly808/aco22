package artcode.shop;

import artcode.shop.terminal.Terminal;

import java.util.Scanner;

public class Salesman {
    private String fullName;
    private String login;
    private String password;




    public Bill createBill (Terminal terminal) { // Salesman create a new bill at terminal
        return  (terminal != null) ? terminal.createBill(this) : null;
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

    public Salesman(String fullName) {
        this.fullName = fullName;
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
