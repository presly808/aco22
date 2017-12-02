package ua.artcode.market.model;

import java.util.ArrayList;
import java.util.List;

public class SalesMan {

    String fullName;
    String login;
    String pass;

    public SalesMan(String fullName, String login, String pass){

        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }


}
