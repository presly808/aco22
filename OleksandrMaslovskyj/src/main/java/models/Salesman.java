package models;


public class Salesman implements Comparable<Salesman>{

    private String fullname;
    private String login;
    private String pass;

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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String toString() {
        return "Salesman{" +
                "fullname='" + fullname + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }


    @Override
    public int compareTo(Salesman o) {
        return this.getFullname().equals(o.getFullname()) && this.getLogin().equals(o.getLogin()) ? 1 : 0;
    }
}
