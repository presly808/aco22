package ua.artcode.market.model;

public class SalesMan {

    String fullName;
    String login;
    String pass;

    public SalesMan(String fullName){

        this.fullName = fullName;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
