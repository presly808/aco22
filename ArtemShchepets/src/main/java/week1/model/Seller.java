package week1.model;

public class Seller {

    private String login;
    private String password;

    private String fullName;

    private int soldProducts;

    public Seller() {
    }

    public Seller(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", soldProducts=" + soldProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        if (login != null ? !login.equals(seller.login) : seller.login != null) return false;
        if (password != null ? !password.equals(seller.password) : seller.password != null) return false;
        return fullName != null ? fullName.equals(seller.fullName) : seller.fullName == null;
    }
}
