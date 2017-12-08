package ua.artcode.market.model;

public class SalesMan {

    private String fullName;
    private String login;
    private String pass;

    public SalesMan(String fullName, String login, String pass){

        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPass() {
        return this.pass;
    }


}
