package ua.artcode.market.models;

public class Salesman{

    private String fullname;
    private String login;
    private String password;
    private boolean isLogged;

    public Salesman(String fullname, String login, String password) {
        this.fullname = fullname;
        this.login = login;
        this.password = password;
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

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
