package ua.artcode.market.models;

public class Salesman {

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
