package ua.artcode.market.Model;

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
