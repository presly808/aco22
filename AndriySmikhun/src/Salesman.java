public class Salesman {

    String fullname;
    String login;
    String password;
    boolean status;

    public Salesman(String fullname, String login, String password, boolean status) {
        this.fullname = fullname;
        this.login = login;
        this.password = password;
        this.status = status;

    }

    public Salesman() {
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

    public boolean isStatus() {
        return status;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void printSalesman(){
        System.out.println("Full name " + fullname);
        System.out.println("Login" + login);
        System.out.println("Is working " + status);
    }
}
