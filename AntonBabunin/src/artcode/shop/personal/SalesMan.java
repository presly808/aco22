package artcode.shop.personal;

import artcode.shop.product.Product;

public class SalesMan {
    private String fullName;
    private String login;
    private String password;

    public SalesMan(String fullName) {
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

    public void printFullInfo () {
        System.out.printf("Fullname is %s, login is %s, password is encrypted - *****, but at extra fee admin told it - %s", getFullName(), getLogin(), getPassword());
    }

    public void addProduct(Product product, Bill bill) {
        if (product != null && bill != null) {
            bill.addProduct(product);
        }
    }

}
