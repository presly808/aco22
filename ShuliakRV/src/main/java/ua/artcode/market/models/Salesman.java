package ua.artcode.market.models;

import java.util.ArrayList;
import java.util.List;

public class Salesman {

    private String fullname;
    private String login;
    private String password;
    private int soldProducts;
    private double amountSales;
    private double salary;
    private boolean isLogged;

    List<Salesman> subSalesmen;

    public Salesman(String fullname, String login, String password) {
        this.fullname = fullname;
        this.login = login;
        this.password = password;
        this.subSalesmen = new ArrayList<>();
    }

    public String getFullname() {
        return fullname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public double getAmountSales() {
        return amountSales;
    }

    public List<Salesman> getSubSalesmen() {
        return subSalesmen;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    public void setAmountSales(double amountSales) {
        this.amountSales = amountSales;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salesman salesman = (Salesman) o;

        if (login != null ? !login.equals(salesman.login) :
                salesman.login != null)
            return false;
        return password != null ? password.equals(salesman.password) :
                salesman.password == null;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullname='" + fullname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", soldProducts=" + soldProducts +
                ", amountSales=" + amountSales +
                ", salary=" + salary +
                ", isLogged=" + isLogged +
                ", subSalesmen=" + subSalesmen +
                '}';
    }
}
