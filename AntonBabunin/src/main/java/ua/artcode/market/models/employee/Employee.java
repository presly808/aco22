package ua.artcode.market.models.employee;

import ua.artcode.market.models.money.Money;

import java.util.List;

public abstract class Employee implements Comparable<Salesman> {
    private String fullName;
    private String login;
    private String password;
    private boolean isConnected;
    private Money salary;
    private int percent;


    public Employee() {
    }

    public Employee(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public Money getSalary() {
        return salary;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setSalary(Money salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
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

    public boolean isConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public abstract void setSubordinateList(List<Employee> subordinateList);
    public abstract List getSubordinateList();

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int compareTo(Salesman o) {
        return this.getLogin().compareTo(o.getLogin());
    }
}
