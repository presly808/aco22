public class Salesman {

    private int id;
    private String fullname;
    private String login;
    private String password;

    public Salesman(int id, String fullname, String login, String password) {
        this.fullname = fullname;
        this.login = login;
        this.password = password;
        this.id = id;
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

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
