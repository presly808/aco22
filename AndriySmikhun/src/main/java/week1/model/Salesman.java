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
        final int prime = 31;
        int result = fullname.hashCode();
        result = prime * result + login.hashCode();
        result = prime * result + password.hashCode();
        return super.hashCode();
    }
}
