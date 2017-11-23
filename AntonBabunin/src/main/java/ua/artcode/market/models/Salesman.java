package ua.artcode.market.models;

import java.util.Comparator;

public class Salesman implements Comparator<Salesman> {

    private String fullName;
    private String login;
    private String password;
    private boolean isConnected;

    public Salesman() {
    }

    public Salesman(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Salesman salesman = (Salesman) object;

        return login != null ? login.equals(salesman.login) :
                salesman.login == null;
    }

    @Override
    public int compare(Salesman o1, Salesman o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }

    @Override
    public Comparator<Salesman> thenComparing(Comparator<? super Salesman> other) {
        return new Comparator<Salesman>() {
            @Override
            public int compare(Salesman o1, Salesman o2) {
                return o1.getLogin().compareTo(o2.getLogin());
            }
        };
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
