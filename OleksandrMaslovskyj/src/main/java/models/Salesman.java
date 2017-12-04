package models;

import java.util.List;

public class Salesman implements Comparable<Salesman>{

    private String fullname;
    private String login;
    private String pass;
    private double salary;

    private List<Salesman> salesmanList;
    private List<Bill> bills;

    public Salesman(String login, String pass) {
        this.fullname = login;
        this.login = login;
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLogin() {
        return login;
    }

    public String toString() {
        return "Salesman{" +
                "fullname='" + fullname + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public double getSalary() {
        return salary;
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSalesmanList(List<Salesman> salesmanList) {
        this.salesmanList = salesmanList;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public int compareTo(Salesman o) {
        return this.getFullname().equals(o.getFullname())
                && this.getLogin().equals(o.getLogin()) ? 1 : 0;
    }
}
