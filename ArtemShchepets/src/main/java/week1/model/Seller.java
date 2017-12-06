package week1.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Seller {

    private String login;
    private String password;

    private String fullName;

    private int soldProducts;

    private double salary;

    private List<Seller> subsellers;

    private List<Bill> bills;

    public Seller() {
        this.subsellers = new ArrayList<>();
    }

    public Seller(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }

    public Seller(String login, String password, String fullName, List<Bill> bills) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.bills = bills;
        subsellers = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    public List<Seller> getSubsellers() {
        return subsellers;
    }

    public void setSubsellers(List<Seller> subsellers) {
        this.subsellers = subsellers;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public void addSubSeller(Seller subSeller) {
        subsellers.add(subSeller);
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

    public class SellersSoldProductsComparator implements Comparator<Seller> {


        @Override
        public int compare(Seller o1, Seller o2) {
            return o1.getSoldProducts() - o2.getSoldProducts();
        }

    }
}
