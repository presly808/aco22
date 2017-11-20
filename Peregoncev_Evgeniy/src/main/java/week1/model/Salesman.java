package week1.model;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Salesman {

    private String fullname;
    private String login;
    private String pass;
    private int id;

    public Salesman() {

    }

    public Salesman(String fullname, String login, String pass) {
        this.fullname = fullname;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullname='" + fullname + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}

