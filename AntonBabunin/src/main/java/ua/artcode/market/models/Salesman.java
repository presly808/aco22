package ua.artcode.market.models;


public class Salesman{
    private String fullName;
    private String login;
    private String password;

    private Salesman() {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public Salesman(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }

//    public String getLogin() {
//        return login;
//    }

//    public void setLogin(String login) {
//        this.login = login;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    @Override
    public boolean equals (Object object) {
        return object != null && object instanceof Salesman &&
                this.getFullName().equals(((Salesman) object).getFullName());
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullName='" + this.getFullName() + '\'' +
                '}';
    }
}
// ----------------------------------------------------------------------------