package week1.model;

public class Salesman {

    private String fullname;
    private String login;
    private String password;
    private boolean status;

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


    public void printSalesman(){
        System.out.println("Full name " + fullname);
        System.out.println("Login" + login);
        System.out.println("Is working " + status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Salesman)) return false;
        Salesman o = (Salesman) obj;
        if (!(this.login.equals(o.login))) return false;
        if (!(this.fullname.equals(o.fullname)))return false;
        return this.password.equals(o.password);

    }

    @Override
    public int hashCode() {
        int result = fullname.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return result;
    }
}
